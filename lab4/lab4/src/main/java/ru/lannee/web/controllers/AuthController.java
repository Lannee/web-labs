package ru.lannee.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lannee.web.data.UserForm;
import ru.lannee.web.security.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody UserForm user) {
//        System.out.println(user);
//        ResponseEntity<String> response = new ResponseEntity<>("hello", HttpStatusCode.valueOf(200));
////        if(userService.saveUser(user)) {
////
////        }
//        return response;
//    }

    @PostMapping("/register")
    public String register(@RequestBody UserForm user) {
        return user.toString();
    }
}
