package com.flooringmastery.service;

public class ValidationResponse {

    public boolean valid;
    public String message;

    public ValidationResponse() {
        valid = true;
    }

    public ValidationResponse(String error) {
        this.message = error;
        valid = false;
    }
}
