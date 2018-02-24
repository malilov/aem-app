package com.mlov.curuba.http.impl;

import com.google.gson.Gson;
import com.mlov.curuba.config.SpotifyApiConfig;
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

    private void createToken() throws IOException {
        requestManager.setSpotifyRequestBuilder(createAuthenticationRequestBuilder());
        requestManager.buildRequest();
        SpotifyRequest spotifyRequest = requestManager.getSpotifyRequest();
        String tokenJson = httpOperations.post(spotifyRequest.getUri(), spotifyRequest.getHeaders().toArray(new Header[spotifyRequest.getHeaders().size()]), spotifyRequest.getBody());
        accessToken = gson.fromJson(tokenJson, Token.class);

    }

    public String getJson(String id, CallType callType) throws IOException {
        validateAccessToken();
        requestManager.setSpotifyRequestBuilder(createSpotifyDataRequestBuilder(id));
        requestManager.buildRequest();
        SpotifyRequest spotifyRequest = requestManager.getSpotifyRequest();
        String result = httpOperations.get(spotifyRequest.getUri(), spotifyRequest.getHeaders().toArray(new Header[spotifyRequest.getHeaders().size()]));
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
}
