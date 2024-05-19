package com.dreamgames.backendengineeringcasestudy.user.exception;

public enum UserErrorMessage {

    USER_NOT_FOUND("User not found!"),

    USER_NULL("User is null"),

    NO_USERS("No user found in the database!"),
    PARAMETER_CANNOT_BE_NULL("Parameter cannot be null"),

    CONCURRENT_WRITE("Cannot update user level concurrently."),

    INSUFFICIENT_COINS("User has insufficient coins");

    private String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
