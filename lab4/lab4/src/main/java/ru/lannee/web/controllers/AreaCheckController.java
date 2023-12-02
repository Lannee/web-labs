package ru.lannee.web.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controller")
public class AreaCheckController {

    @PostMapping("/")
    public String checkArea(@RequestParam(name = "x") double x,
                          @RequestParam(name = "y") double y,
                          @RequestParam(name = "r") double r) {
        return "Shot x = " + x + " y = " + y + " r = " + r;
    }

    @GetMapping ("/")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "World") String name) {
        return "Hello, " + name;
    }
}
