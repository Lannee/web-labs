package ru.lannee.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lannee.web.data.Shot;
import ru.lannee.web.data.ShotResult;
import ru.lannee.web.entity.Result;
import ru.lannee.web.managers.BoundManager;
import ru.lannee.web.managers.exeptions.OutOfCoordinatesBoundsException;
import ru.lannee.web.repository.ShotRepository;
import ru.lannee.web.repository.UserRepository;
import ru.lannee.web.security.jwt.AuthTokenFilter;
import ru.lannee.web.security.jwt.JwtUtils;
import ru.lannee.web.sevices.ShotService;

import java.util.List;

@RestController
@RequestMapping("/api/controller")
//@CrossOrigin("*")
public class AreaCheckController {

    private final ShotService shotService;
    private final JwtUtils jwtUtils;
    private final AuthTokenFilter authTokenFilter;
    private final UserRepository userRepository;

    @Autowired
    public AreaCheckController(ShotService shotService, JwtUtils jwtUtils, AuthTokenFilter authTokenFilter, UserRepository userRepository) {
        this.shotService = shotService;
        this.jwtUtils = jwtUtils;
        this.authTokenFilter = authTokenFilter;
        this.userRepository = userRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> checkArea(@RequestBody Shot shot) {
        try {
            System.out.println(shot.getX()+" "+shot.getY()+" "+shot.getR()+" \ntoken is: "+jwtUtils.validateJwtToken(shot.getToken()));
            BoundManager.checkBounds(shot);
            ShotResult hitResult = shotService.save(shot);
            return new ResponseEntity<>(hitResult, HttpStatus.CREATED);
        } catch (OutOfCoordinatesBoundsException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping ("")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "World") String name) {
        return "Hello, " + name;
    }

//    @GetMapping ("/")
//    public List<Result> allShots() {
//        return shotRepository.findAll();
//    }
}
