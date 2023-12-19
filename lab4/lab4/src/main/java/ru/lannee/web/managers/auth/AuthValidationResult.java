package ru.lannee.web.managers.auth;

public enum AuthValidationResult {
    OK("Successful authentication"),
    INCORRECT_USERNAME_OR_PASSWORD("Invalid login or password"),
    INVALID_PASSWORD("Invalid password"),
    INVALID_LOGIN("Invalid login"),
    INVALID_EMAIL("Invalid email"),
    USER_ALREADY_EXIST("User already exists"),
    SESSION_EXPIRED("Token is expired");

    private final String errorMessage;

    AuthValidationResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
