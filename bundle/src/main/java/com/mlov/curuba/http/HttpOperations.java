package com.mlov.curuba.http;

import java.io.IOException;
import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public interface HttpOperations {

    String post(URI uri, Header[] headers, HttpEntity body) throws
            IOException;
    String get(URI uri, Header[] headers) throws
            IOException;
}
