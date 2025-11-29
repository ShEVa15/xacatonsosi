package com.psb.src.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "main_fr";
    }

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @GetMapping("/courses")
    public String welcome() {
        return "index3"; // → main_fr.html
    }

    @GetMapping("/welcome1")
    public String welcome1() {
        return "welcome1"; // → main_fr.html
    }

    @GetMapping("/welcome2")
    public String welcome2() {
        return "welcome2"; // → main_fr.html
    }

    @GetMapping("/welcome3")
    public String welcome3() {
        return "welcome3"; // → main_fr.html
    }



}