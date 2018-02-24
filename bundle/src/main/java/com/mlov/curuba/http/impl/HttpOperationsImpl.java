package com.mlov.curuba.http.impl;

import com.mlov.curuba.http.HttpOperations;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

@Component(metatype = true, immediate = true, label = "Call Manager Service", description = "Create request and perform calls to Spotify")
@Service(HttpOperations.class)
public class HttpOperationsImpl implements HttpOperations {

    private HttpClient client;

    public HttpOperationsImpl() {
        client = getHttpClient();
    }

    public String post(URI uri, Header[] headers, HttpEntity body) throws
            IOException {
        assert (uri != null);
        assert (!uri.toString().equals(""));

        final HttpPost httpPost = new HttpPost();

        httpPost.setURI(uri);
        httpPost.setHeaders(headers);
        httpPost.setEntity(body);
        ((StringEntity) body).setContentType("application/x-www-form-urlencoded");
        HttpResponse response = execute(httpPost);

        httpPost.releaseConnection();
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    public String get(URI uri, Header[] headers) throws
            IOException {
        assert (uri != null);
        assert (!uri.toString().equals(""));

        final HttpGet httpGet = new HttpGet();

        httpGet.setURI(uri);
        httpGet.setHeaders(headers);

        HttpResponse response = execute(httpGet);

        httpGet.releaseConnection();
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    private HttpResponse execute(HttpRequestBase request) throws
            IOException {
        HttpResponse response = client.execute(request);
        return response;
    }

    public final static HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}
