package com.mlov.curuba.http.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mlov.curuba.exceptions.UnauthorizedException;
import com.mlov.curuba.http.HttpOperations;
import com.mlov.curuba.http.HttpStatus;
import com.mlov.curuba.http.Utils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

@Component(metatype = true, immediate = true, label = "Http call operations", description = "Contains the methods to call Http API")
@Service(HttpOperations.class)
public class HttpOperationsImpl implements HttpOperations {

    private static final String UTF_8 = "UTF-8";
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private HttpClient client;
    private Utils utils = new Utils();

    public HttpOperationsImpl() {
        client = getHttpClient();
    }

    public String post(URI uri, Header[] headers, HttpEntity body) throws
            IOException, UnauthorizedException {
        assert (uri != null);
        assert (!uri.toString().equals(""));

        HttpPost httpPost = new HttpPost();

        httpPost.setURI(uri);
        httpPost.setHeaders(headers);
        httpPost.setEntity(body);

        HttpResponse response = execute(httpPost);

        httpPost.releaseConnection();
        return getBody(response);
    }

    public String get(URI uri, Header[] headers) throws
            IOException, UnauthorizedException {
        assert (uri != null);
        assert (!uri.toString().equals(""));

        final HttpGet httpGet = new HttpGet();
        httpGet.setURI(uri);
        httpGet.setHeaders(headers);

        HttpResponse response = execute(httpGet);
        httpGet.releaseConnection();

        return getBody(response);
    }

    private String getBody(HttpResponse response) throws IOException, UnauthorizedException {
        String bodyResponse = utils.getBody(response);
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() == HttpStatus.NOT_VALID_TOKEN.getCode()) {
            throw new UnauthorizedException(utils.getErrorMessage(bodyResponse));
        }
        return bodyResponse;
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
