package com.mlov.curuba.http;

import com.mlov.curuba.models.SpotifyRequest;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public abstract class SpotifyRequestBuilder {
    protected SpotifyRequest spotifyRequest;

    public void buildSpotifyRequest() {
        spotifyRequest = new SpotifyRequest();
    }

    public abstract void withAuthorization();

    public abstract void withContentType();

    public abstract void withURI();

    public abstract void withBody() throws UnsupportedEncodingException;

    public SpotifyRequest getSpotifyRequest() {
        return spotifyRequest;
    }

    protected void addHeader(String name, String value) {
        List<Header> headers = spotifyRequest.getHeaders();
        if (headers == null) {
            headers = new ArrayList<Header>();
            this.spotifyRequest.setHeaders(headers);
        }
        headers.add(new BasicHeader(name, value));
    }
}
