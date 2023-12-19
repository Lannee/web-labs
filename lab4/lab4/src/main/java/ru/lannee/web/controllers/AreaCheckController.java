package ru.lannee.web.controllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lannee.web.data.Shot;
import ru.lannee.web.data.ShotResult;
import ru.lannee.web.data.Token;
import ru.lannee.web.entity.Result;
import ru.lannee.web.exceptions.InvalidJWTTokenException;
import ru.lannee.web.json.LocalDateTimeDeserializer;
import ru.lannee.web.managers.BoundManager;
import ru.lannee.web.managers.exeptions.OutOfCoordinatesBoundsException;
import ru.lannee.web.repository.ShotRepository;
import ru.lannee.web.repository.UserRepository;
import ru.lannee.web.security.jwt.AuthTokenFilter;
import ru.lannee.web.security.jwt.JwtUtils;
import ru.lannee.web.sevices.ShotService;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/shots")
@CrossOrigin("*")
public class AreaCheckController {

    private final ShotService shotService;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    @Autowired
    public AreaCheckController(ShotService shotService, JwtUtils jwtUtils, UserRepository userRepository) {
        this.shotService = shotService;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    @PostMapping("add")
    public ResponseEntity<?> checkArea(@RequestBody Shot shot) {
        try {
            if(jwtUtils.validateJwtToken(shot.getToken())) {
                BoundManager.checkBounds(shot);
                Result result = shotService.save(shot);
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            }
            return ResponseEntity.badRequest().body("Invalid token!");
        } catch (OutOfCoordinatesBoundsException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (InvalidJWTTokenException e) {
            return ResponseEntity.badRequest().body("Invalid token!");
        }
    }

    @PostMapping ("all")
    public ResponseEntity<?> allShots(@RequestBody Token token) {
        String login = jwtUtils.getUserNameFromJwtToken(token.getToken());

        if(login != null) {
            List<Result> results = shotService.findAllByUserLogin(login);
            System.out.println("results: " + results);
            if(results != null) {
                return ResponseEntity.ok().body(results);
            }
        }
        return ResponseEntity.badRequest().body("Invalid token");
    }

    @PostMapping(path = "/clear")
    public ResponseEntity<?> deleteAllPoints(@RequestBody Token token) {
        String login = jwtUtils.getUserNameFromJwtToken(token.getToken());

        if(login != null) {
            if(shotService.clear(login)) {
                return ResponseEntity.ok().body("Clear successful");
            }
            List<Result> results = shotService.findAllByUserLogin(login);
            if(results != null) {
                return ResponseEntity.ok().body(results);
            }
        }
        return ResponseEntity.badRequest().body("Invalid token");
    }
}
