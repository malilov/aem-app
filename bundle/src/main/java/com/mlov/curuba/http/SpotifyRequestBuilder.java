package com.mlov.curuba.http;

import java.io.UnsupportedEncodingException;

public abstract class SpotifyRequestBuilder {
    protected SpotifyRequest spotifyRequest;

    public void buildSpotifyRequest() {
        spotifyRequest = new SpotifyRequest();
    }

    public abstract void withHeaders(String clientId, String clientKey);

    public abstract void withContentType();

    public abstract void withURI(String uri);

    public abstract void withBody() throws UnsupportedEncodingException;

    public SpotifyRequest getSpotifyRequest() {
        return spotifyRequest;
    }
}
