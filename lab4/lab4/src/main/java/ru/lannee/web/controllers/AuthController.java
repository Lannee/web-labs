package ru.lannee.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lannee.web.data.UserForm;
import ru.lannee.web.managers.auth.AuthValidationResult;
import ru.lannee.web.managers.auth.UserValidator;
import ru.lannee.web.sevices.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserForm user) {
        AuthValidationResult validationResult = UserValidator.validateUser(user);

        if(validationResult != AuthValidationResult.OK)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(validationResult.getErrorMessage());

        validationResult = authService.createUser(user);
        if(validationResult != AuthValidationResult.OK){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(validationResult.getErrorMessage());
        }
        return ResponseEntity.ok().body("Success");
    }

//    @PostMapping("/register")
//    public String register(@RequestBody UserForm user) {
//        return user.toString();
//    }
}
