package com.mlov.curuba.http.impl;

import com.mlov.curuba.config.SpotifyApiConfig;
import com.mlov.curuba.http.RequestManager;
import com.mlov.curuba.http.SpotifyRequestBuilder;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SpotifyAuthenticationBuilderImpl extends SpotifyRequestBuilder {

    @Reference
    SpotifyApiConfig spotifyApiConfig;

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_URL_ENCODED = "application/x-www-form-urlencoded";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String AUTHORIZATION = "Authorization";


    public void withHeaders(String clientId, String clientKey) {
        String value = Base64.getEncoder().encodeToString((clientId + ":" + clientKey).getBytes());
        List<Header> headers = spotifyRequest.getHeaders();
        if (headers == null) {
            headers = new ArrayList<Header>();
            spotifyRequest.setHeaders(headers);
        }
        headers.add(new BasicHeader(AUTHORIZATION, String.valueOf(value)));
    }

    public void withContentType() {
        if (spotifyRequest.getHeaders() != null) {
            ContentType.create(CONTENT_TYPE_URL_ENCODED);
            spotifyRequest.getHeaders().add(new BasicHeader(CONTENT_TYPE, ContentType.create(CONTENT_TYPE_URL_ENCODED).getMimeType()));
        }
    }

    public void withURI(String uri) {
        URI netUri = URI.create(uri);
        spotifyRequest.setUri(netUri);
    }

    public void withBody() throws UnsupportedEncodingException {
        List<NameValuePair> body = new ArrayList<NameValuePair>();
        body.add(new BasicNameValuePair(GRANT_TYPE, CLIENT_CREDENTIALS));
        spotifyRequest.setBody(new UrlEncodedFormEntity(body));
    }


}
