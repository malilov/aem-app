package com.mlov.curuba.http.impl;

import com.mlov.curuba.http.SpotifyRequestBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;

public class SpotifyDataRequestBuilder extends SpotifyRequestBuilder {

    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";
    private String token;
    private String requestId;
    private String uri;

    public SpotifyDataRequestBuilder(String uri, String token, String requestId) {
        this.token = token;
        this.requestId = requestId;
        this.uri = uri;
    }

    public void withAuthorization() {
        addHeader(AUTHORIZATION, BEARER + token);
    }

    public void withURI() {
        if (requestId != null) {
            uri = String.format(uri, requestId);
        }
        URI netUri = URI.create(uri);
        spotifyRequest.setUri(netUri);
    }

    public void withContentType() {
    }

    public void withBody() throws UnsupportedEncodingException {
    }
}
