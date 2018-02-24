package com.mlov.curuba.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import java.net.URI;
import java.util.List;

public class SpotifyRequest {

    private List<Header> headers;
    private ContentType contentType;
    private URI uri;
    private HttpEntity body;

    public List<Header> getHeaders() {
        return headers;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public URI getUri() {
        return uri;
    }

    public HttpEntity getBody() {
        return body;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public void setBody(HttpEntity body) {
        this.body = body;
    }
}
