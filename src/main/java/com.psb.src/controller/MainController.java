package com.psb.src.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "welcome";
    }

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome"; // → welcome.html
    }

    @GetMapping("/course")
    public String course() {
        return "course";
    }

    @GetMapping("/courses")
    public String courses() {
        return "courses";
    }
}