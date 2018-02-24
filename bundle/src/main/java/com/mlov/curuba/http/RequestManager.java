package com.mlov.curuba.http;

import java.io.UnsupportedEncodingException;

public interface RequestManager {
     void buildRequest() throws UnsupportedEncodingException;
     SpotifyRequest getSpotifyRequest();
     void setSpotifyRequestBuilder(SpotifyRequestBuilder spotifyRequestBuilder);
}
