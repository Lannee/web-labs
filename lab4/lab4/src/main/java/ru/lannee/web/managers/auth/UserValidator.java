package ru.lannee.web.managers.auth;

import ru.lannee.web.data.UserForm;

public class UserValidator {

    public static AuthValidationResult validateUser(String login, String password, String email) {
        if (!isValidLogin(login)) return AuthValidationResult.INVALID_LOGIN;

        if (!isValidPassword(password)) return AuthValidationResult.INVALID_PASSWORD;

        if (!isValidEmail(email)) return AuthValidationResult.INVALID_EMAIL;

        return AuthValidationResult.OK;
    }

    public static AuthValidationResult validateUser(String login, String password) {
        if (!isValidLogin(login)) return AuthValidationResult.INVALID_LOGIN;

        if (!isValidPassword(password)) return AuthValidationResult.INVALID_PASSWORD;

        return AuthValidationResult.OK;
    }

    private static boolean isValidLogin(String username) {
        return username != null && username.matches("[a-zA-Z0-9]+") ;
    }

    private static boolean isValidPassword(String password) {
        return password != null && password.length() >= 1 && password.matches("[a-zA-Z0-9]+");
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }
}
