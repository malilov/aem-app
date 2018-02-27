package com.mlov.curuba.http.impl;

import com.mlov.curuba.exceptions.UnauthorizedException;
import com.mlov.curuba.http.Utils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HttpOperationsImplTest {

    @Mock
    private HttpClient httpClient;
    @Mock
    private Utils utils;

    @InjectMocks
    private HttpOperationsImpl httpOperationsImpl;

    private static final String UTF_8 = "UTF-8";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String CONTENT_TYPE_URL_ENCODED = "application/x-www-form-urlencoded";

    @Test
    public void shouldGetBodyTest() throws IOException, UnauthorizedException {

        String expectedResult = "{\"access_token\":\"BQBY\",\"token_type\":\"Bearer\",\"expires_in\":3600,\"scope\":\"\"}";
        HttpResponse httpResponse = mock(HttpResponse.class);
        HttpEntity httpEntity = mock(HttpEntity.class);
        StatusLine statusLine = mock(StatusLine.class);
        HttpEntity body = mock(HttpEntity.class);

        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);
        when(utils.getBody(httpResponse)).thenReturn(expectedResult);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);

        Header[] headers = new Header[]{new BasicHeader("Authorization", "Basic ZhOWY5NDM5MTg=")
                , new BasicHeader("Content-Type", "application/x-www-form-urlencoded")};
        URI netUri = URI.create("https://accounts.spotify.com/api/token");
        String result = httpOperationsImpl.post(netUri, headers, body);
        assertEquals(expectedResult, result);
    }

    @Test(expected = UnauthorizedException.class)
    public void shouldThrowUnauthorizedExceptionTest() throws IOException,UnauthorizedException{

        String expectedResult = "{\"access_token\":\"BQBY\",\"token_type\":\"Bearer\",\"expires_in\":3600,\"scope\":\"\"}";
        HttpResponse httpResponse = mock(HttpResponse.class);
        HttpEntity httpEntity = mock(HttpEntity.class);
        StatusLine statusLine = mock(StatusLine.class);
        HttpEntity body = mock(HttpEntity.class);

        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);
        when(utils.getBody(httpResponse)).thenReturn(expectedResult);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(401);

        Header[] headers = new Header[]{new BasicHeader("Authorization", "Basic ZhOWY5NDM5MTg=")
                , new BasicHeader("Content-Type", "application/x-www-form-urlencoded")};
        URI netUri = URI.create("https://accounts.spotify.com/api/token");
        httpOperationsImpl.post(netUri, headers, body);
    }

    public UrlEncodedFormEntity createBody() throws UnsupportedEncodingException {
        List<NameValuePair> body = new ArrayList<NameValuePair>();
        body.add(new BasicNameValuePair(GRANT_TYPE, CLIENT_CREDENTIALS));

        UrlEncodedFormEntity entityBody = new UrlEncodedFormEntity(body);
        entityBody.setContentType(CONTENT_TYPE_URL_ENCODED);
        return entityBody;
    }


}
