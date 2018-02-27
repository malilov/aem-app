package com.mlov.curuba.http.impl;

import com.mlov.curuba.models.SpotifyRequest;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpotifyAuthenticationRequestBuilderTest {

    private SpotifyRequest spotifyRequest;

    private SpotifyAuthenticationRequestBuilder spotifyAuthenticationRequestBuilder;

    private static final String URI = "https://test.com";
    private static final String CLIENT_ID = "090909";
    private static final String CLIENT_KEY = "#$rt%&(&";

    @Before
    public void setUp() {
        spotifyRequest = new SpotifyRequest();
        spotifyAuthenticationRequestBuilder = new SpotifyAuthenticationRequestBuilder(URI, CLIENT_ID, CLIENT_KEY);
    }

    @Test
    public void shouldAddAuthorization() {
        spotifyAuthenticationRequestBuilder.buildSpotifyRequest();
        spotifyAuthenticationRequestBuilder.withAuthorization();
        List headers = spotifyAuthenticationRequestBuilder.getSpotifyRequest().getHeaders();
        assertThat(headers.size(), is(1));
        assertThat(((Header) headers.get(0)).getName(), is("Authorization"));
    }

    @Test
    public void shouldAddURI() {
        java.net.URI expectedUri = java.net.URI.create(URI);
        spotifyAuthenticationRequestBuilder.buildSpotifyRequest();
        spotifyAuthenticationRequestBuilder.withURI();
        assertEquals(spotifyAuthenticationRequestBuilder.getSpotifyRequest().getUri(), expectedUri);
    }

    @Test
    public void shouldAddBody() throws UnsupportedEncodingException {
        spotifyAuthenticationRequestBuilder.buildSpotifyRequest();
        spotifyAuthenticationRequestBuilder.withBody();
        HttpEntity body = spotifyAuthenticationRequestBuilder.getSpotifyRequest().getBody();
        assertEquals(body.getContentType().getValue(), "application/x-www-form-urlencoded");
    }

}
