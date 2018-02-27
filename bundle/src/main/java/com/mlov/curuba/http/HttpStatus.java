package com.mlov.curuba.http;

public enum HttpStatus {

    OK("ok", 200), NOT_VALID_TOKEN("Token expired or not valid", 401),
    SERVER_ERROR("Server error", 500),
    BAD_REQUEST("Request error - Bad request", 400),
    NOT_FOUND("Resource not found", 404),
    FORBIDDEN("Not allowed access to that resource", 403);

    HttpStatus(String description, int code) {

        this.description = description;
        this.code = code;
    }

    private String description;
    private int code;

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
