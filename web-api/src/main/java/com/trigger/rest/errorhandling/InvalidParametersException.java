package com.trigger.rest.errorhandling;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidParametersException extends Exception {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
