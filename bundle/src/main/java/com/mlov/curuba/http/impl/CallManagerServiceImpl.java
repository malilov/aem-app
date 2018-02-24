package com.mlov.curuba.http.impl;

import com.mlov.curuba.http.CallManagerService;
import com.mlov.curuba.http.HttpOperations;
import com.mlov.curuba.http.RequestManager;
import com.mlov.curuba.http.SpotifyRequest;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.http.Header;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


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

    private String accessToken;

    public void createToken() throws IOException {
        requestManager.setSpotifyRequestBuilder(new SpotifyAuthenticationBuilderImpl());
        requestManager.buildRequest();
        SpotifyRequest spotifyRequest = requestManager.getSpotifyRequest();
        accessToken = httpOperations.post(spotifyRequest.getUri(), spotifyRequest.getHeaders().toArray(new Header[spotifyRequest.getHeaders().size()]), spotifyRequest.getBody());
        int g = 8;
    }
    public String getJson(String id, CallType callType){
        try {
            createToken();
        } catch (IOException e) {
            e.printStackTrace();
            int h = 2;
        }
        SpotifyRequest spotifyRequest = requestManager.getSpotifyRequest();
      //  result = httpOperationsImpl.post(spotifyRequest.getUri(), spotifyRequest.getHeaders().toArray(new Header[spotifyRequest.getHeaders().size()]), spotifyRequest.getBody());

        return null;
    }

}
