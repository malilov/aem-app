package com.mlov.curuba.http.impl;

import com.mlov.curuba.models.SpotifyRequest;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SpotifyDataRequestBuilderTest {

    private SpotifyRequest spotifyRequest;

    private SpotifyDataRequestBuilder spotifyDataRequestBuilder;

    private static final String URI = "https://test.com";
    private static final String CLIENT_ID = "090909";
    private static final String CLIENT_KEY = "#$rt%&(&";

    @Before
    public void setUp() {
        spotifyRequest = new SpotifyRequest();
        spotifyDataRequestBuilder = new SpotifyDataRequestBuilder(URI, CLIENT_ID, CLIENT_KEY);
    }

    @Test
    public void shouldAddAuthorization() {
        spotifyDataRequestBuilder.buildSpotifyRequest();
        spotifyDataRequestBuilder.withAuthorization();
        List headers = spotifyDataRequestBuilder.getSpotifyRequest().getHeaders();
        assertThat(headers.size(), is(1));
        assertThat(((Header) headers.get(0)).getName(), is("Authorization"));
    }

    @Test
    public void shouldAddURI() {
        java.net.URI expectedUri = java.net.URI.create(URI);
        spotifyDataRequestBuilder.buildSpotifyRequest();
        spotifyDataRequestBuilder.withURI();
        assertEquals(spotifyDataRequestBuilder.getSpotifyRequest().getUri(), expectedUri);
    }

    @Test
    public void shouldAddBody() throws UnsupportedEncodingException {
        spotifyDataRequestBuilder.buildSpotifyRequest();
        spotifyDataRequestBuilder.withBody();
        HttpEntity body = spotifyDataRequestBuilder.getSpotifyRequest().getBody();
        assertNull(body);
    }

}
