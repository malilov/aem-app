package com.mlov.curuba.http.impl;

import com.mlov.curuba.config.SpotifyApiConfig;
import com.mlov.curuba.http.RequestManager;
import com.mlov.curuba.models.SpotifyRequest;
import com.mlov.curuba.http.SpotifyRequestBuilder;
import com.mlov.curuba.models.Token;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

@Component(metatype = true, immediate = true, label = "Request Manager Impl", description = "Call to Request Builders")
@Service(RequestManager.class)
public class RequestManagerImpl implements RequestManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static Token accessToken;

    private SpotifyRequestBuilder spotifyRequestBuilder;

    public void buildRequest() throws UnsupportedEncodingException {

        if (spotifyRequestBuilder != null) {
            spotifyRequestBuilder.buildSpotifyRequest();
            spotifyRequestBuilder.withAuthorization();
            spotifyRequestBuilder.withContentType();
            spotifyRequestBuilder.withURI();
            spotifyRequestBuilder.withBody();
        }
    }

    public void setSpotifyRequestBuilder(SpotifyRequestBuilder spotifyRequestBuilder) {
        this.spotifyRequestBuilder = spotifyRequestBuilder;
    }

    public SpotifyRequest getSpotifyRequest() {
        return spotifyRequestBuilder.getSpotifyRequest();
    }

    public static Token getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(Token accessToken) {
        RequestManagerImpl.accessToken = accessToken;
    }
}
