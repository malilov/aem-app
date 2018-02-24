package com.mlov.curuba.http;

import com.mlov.curuba.models.SpotifyRequest;

import java.io.UnsupportedEncodingException;

public interface RequestManager {
     void buildRequest() throws UnsupportedEncodingException;
     SpotifyRequest getSpotifyRequest();
     void setSpotifyRequestBuilder(SpotifyRequestBuilder spotifyRequestBuilder);
}
