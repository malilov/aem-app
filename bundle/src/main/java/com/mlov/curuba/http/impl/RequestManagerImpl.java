package com.mlov.curuba.http.impl;

import com.mlov.curuba.config.SpotifyApiConfig;
import com.mlov.curuba.http.RequestManager;
import com.mlov.curuba.http.SpotifyRequest;
import com.mlov.curuba.http.SpotifyRequestBuilder;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import java.io.UnsupportedEncodingException;

@Component(metatype = true, immediate = true, label = "Request Manager Impl", description = "Call to Request Builders")
@Service(RequestManager.class)
public class RequestManagerImpl implements RequestManager{

    @Reference
    SpotifyApiConfig spotifyApiConfig;

    private SpotifyRequestBuilder spotifyRequestBuilder;
    private String token;

    public void buildRequest() throws UnsupportedEncodingException {

        if(token == null) {
            spotifyRequestBuilder = new SpotifyAuthenticationBuilderImpl();
            spotifyRequestBuilder.buildSpotifyRequest();
            spotifyRequestBuilder.withHeaders(getConfigClientId(), getConfigClientKey());
            spotifyRequestBuilder.withContentType();
            spotifyRequestBuilder.withURI(getUrl());
            spotifyRequestBuilder.withBody();
        } /*else {
            spotifyRequestBuilder = new SpotifyRequestSongBuilder();
            spotifyRequestBuilder.buildSpotifyRequest();
            spotifyRequestBuilder.withHeaders();
            spotifyRequestBuilder.withContentType();
            spotifyRequestBuilder.withURI("https://accounts.spotify.com/api/token");
            spotifyRequestBuilder.withBody();
        }*/
    }

    public void setSpotifyRequestBuilder(SpotifyRequestBuilder spotifyRequestBuilder) {
        this.spotifyRequestBuilder = spotifyRequestBuilder;
    }

    public SpotifyRequest getSpotifyRequest() {
        return spotifyRequestBuilder.getSpotifyRequest();
      //  return null;
    }

    private String getConfigClientId(){
        return spotifyApiConfig.getClientId();
    }

    private String getConfigClientKey(){
        return spotifyApiConfig.getClientKey();
    }

    private String getUrl(){
        return spotifyApiConfig.getAuthorizationEndPoint();
    }
}
