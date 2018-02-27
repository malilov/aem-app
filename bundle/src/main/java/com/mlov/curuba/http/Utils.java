package com.mlov.curuba.http;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mlov.curuba.exceptions.UnauthorizedException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Utils {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String UTF_8 = "UTF-8";
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";

    public String getBody(HttpResponse response) throws IOException, UnauthorizedException {
        final String bodyResponse = response.getEntity() != null
                ? EntityUtils.toString(response.getEntity(), UTF_8)
                : null;
        return bodyResponse;
    }


    public String getErrorMessage(String bodyResponse) {
        String errorMessage = HttpStatus.NOT_VALID_TOKEN.getDescription();
        if (bodyResponse != null) {
            try {
                final JsonObject jsonObject = new JsonParser().parse(bodyResponse).getAsJsonObject();
                if (jsonObject.has(ERROR) && jsonObject.has(MESSAGE)) {
                    {
                        errorMessage = jsonObject.get(MESSAGE).getAsString();
                    }
                }
            } catch (JsonSyntaxException e) {
                logger.error("Getting error message from body response.", e);
            }
        }
        return errorMessage;
    }
}
