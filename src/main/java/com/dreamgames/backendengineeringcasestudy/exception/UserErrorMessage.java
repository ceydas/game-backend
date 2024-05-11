package com.dreamgames.backendengineeringcasestudy.exception;

public enum UserErrorMessage {

    USER_NOT_FOUND("User not found!"),
    PARAMETER_CANNOT_BE_NULL("Parameter cannot be null");

    private String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
