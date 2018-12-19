package com.interswitch.url.api.model;

public class Response {
    private final String message;

    public Response(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
