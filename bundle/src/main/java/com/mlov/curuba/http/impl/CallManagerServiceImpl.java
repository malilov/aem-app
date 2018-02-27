package com.mlov.curuba.http.impl;

import com.google.gson.Gson;
import com.mlov.curuba.config.SpotifyApiConfig;
import com.mlov.curuba.exceptions.UnauthorizedException;
import com.mlov.curuba.http.CallManagerService;
import com.mlov.curuba.http.HttpOperations;
import com.mlov.curuba.http.RequestManager;
import com.mlov.curuba.models.SpotifyRequest;
import com.mlov.curuba.models.Token;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.http.Header;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(metatype = true, immediate = true, label = "Call Manager Service", description = "Create request and perform calls to Spotify")
@Service(CallManagerService.class)
public class CallManagerServiceImpl implements CallManagerService {

    public enum CallType {
        ARTIST_ALBUMS
    }

    @Reference
    private RequestManager requestManager;

    @Reference
    private HttpOperations httpOperations;

    @Reference
    SpotifyApiConfig spotifyApiConfig;

    private Gson gson = new Gson();

    private Token accessToken;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private void createToken() throws IOException {
        requestManager.setSpotifyRequestBuilder(createAuthenticationRequestBuilder());
        requestManager.buildRequest();
        SpotifyRequest spotifyRequest = requestManager.getSpotifyRequest();
        String tokenJson = callForToken(spotifyRequest);
        accessToken = gson.fromJson(tokenJson, Token.class);
    }

    private void refreshToken(){

    }
    public String getJson(String id, CallType callType) throws IOException, UnauthorizedException {
        validateAccessToken();
        requestManager.setSpotifyRequestBuilder(createSpotifyDataRequestBuilder(id));
        requestManager.buildRequest();
        SpotifyRequest spotifyRequest = requestManager.getSpotifyRequest();
        return callForSpotifyObject(spotifyRequest);
    }


    private String callForToken(SpotifyRequest spotifyRequest) {
        String tokenJson = null;
        try {
            tokenJson = httpOperations.post(spotifyRequest.getUri(), convertListToArray(spotifyRequest.getHeaders()), spotifyRequest.getBody());
        } catch (UnauthorizedException e) {
            logger.warn("Unauthorized call. Wrong client id or client key.", e);
        } catch (IOException e) {
            logger.warn("Net working issues when asking for token.", e);
        }
        return tokenJson;
    }

    private String callForSpotifyObject(SpotifyRequest spotifyRequest) throws IOException, UnauthorizedException {

        String result = null;
        try {
            result = httpOperations.get(spotifyRequest.getUri(), convertListToArray(spotifyRequest.getHeaders()));
        } catch (UnauthorizedException e) {
            createToken();
            result = httpOperations.get(spotifyRequest.getUri(), convertListToArray(spotifyRequest.getHeaders()));
        } catch (IOException e) {
            logger.warn("Net working issues when asking for token.", e);
        }
        return result;
    }

    private void validateAccessToken() throws IOException {
        if (accessToken == null) {
            createToken();
        }
    }

    private SpotifyAuthenticationRequestBuilder createAuthenticationRequestBuilder() {
        return new SpotifyAuthenticationRequestBuilder(spotifyApiConfig.getAuthorizationEndPoint()
                , spotifyApiConfig.getClientId(), spotifyApiConfig.getClientKey());
    }

    private SpotifyDataRequestBuilder createSpotifyDataRequestBuilder(String id) {
        return new SpotifyDataRequestBuilder(spotifyApiConfig.getArtistAlbumsEndPoint()
                , accessToken.getAccessToken(), id);
    }

    private <T> Header[] convertListToArray(List<Header> list) {
        return list.toArray(new Header[list.size()]);
    }
}
