package ru.lannee.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.lannee.web.data.UserCredentials;
import ru.lannee.web.data.UserForm;
import ru.lannee.web.managers.auth.AuthValidationResult;
import ru.lannee.web.managers.auth.UserValidator;
import ru.lannee.web.security.jwt.JwtUtils;
import ru.lannee.web.sevices.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtils jwtUtils;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserForm user) {
        System.out.println(user);
        AuthValidationResult validationResult = UserValidator.validateUser(user.getLogin(), user.getPassword(), user.getEmail());
        System.out.println(validationResult.getErrorMessage());

        if(validationResult != AuthValidationResult.OK)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResult.getErrorMessage());

        validationResult = authService.register(user);
        if(validationResult != AuthValidationResult.OK){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResult.getErrorMessage());
        }
        return ResponseEntity.ok().body("Success");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserForm user) {
        System.out.println(user);
        AuthValidationResult validationResult = UserValidator.validateUser(user.getLogin(), user.getPassword());

        if(validationResult != AuthValidationResult.OK)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResult.getErrorMessage());

        try {
            UserCredentials userCredentials = authService.login(user);
            System.out.println(userCredentials);
            return ResponseEntity.ok().body(userCredentials);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//    @PostMapping("/register")
//    public String register(@RequestBody UserForm user) {
//        return user.toString();
//    }
}
