package com.mlov.curuba.http.impl;

import com.mlov.curuba.config.SpotifyApiConfig;
import com.mlov.curuba.models.SpotifyRequest;
import com.mlov.curuba.http.SpotifyRequestBuilder;
import org.apache.felix.scr.annotations.Reference;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SpotifyAuthenticationRequestBuilder extends SpotifyRequestBuilder {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    SpotifyApiConfig spotifyApiConfig;

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_URL_ENCODED = "application/x-www-form-urlencoded";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BASIC = "Basic ";
    private static final String COLON_MARK = ":";

    protected SpotifyRequest spotifyRequest;
    private String uri;
    private String clientId;
    private String clientKey;


    public SpotifyAuthenticationRequestBuilder(String uri, String clientId, String clientKey) {
        this.uri = uri;
        this.clientId = clientId;
        this.clientKey = clientKey;
    }

    public void withAuthorization() {
        super.spotifyRequest = spotifyRequest;
        addHeader(AUTHORIZATION, getBasicToken());
    }

    public void withContentType() {
        if (spotifyRequest.getHeaders() != null) {
            ContentType.create(CONTENT_TYPE_URL_ENCODED);
            addHeader(CONTENT_TYPE, ContentType.create(CONTENT_TYPE_URL_ENCODED).getMimeType());
        } else {
            logger.warn("Authorization request, headers are null");
        }
    }

    public void withURI() {
        URI netUri = URI.create(uri);
        spotifyRequest.setUri(netUri);
    }

    public void withBody() throws UnsupportedEncodingException {
        List<NameValuePair> body = new ArrayList<NameValuePair>();
        body.add(new BasicNameValuePair(GRANT_TYPE, CLIENT_CREDENTIALS));
        spotifyRequest.setBody(new UrlEncodedFormEntity(body));
    }


    public void buildSpotifyRequest() {
        spotifyRequest = new SpotifyRequest();
    }

    public SpotifyRequest getSpotifyRequest() {
        return spotifyRequest;
    }

    private String getBasicToken() {
        return BASIC + Base64.getEncoder().encodeToString((clientId
                + COLON_MARK
                + clientKey).getBytes());
    }
}
