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

    @GetMapping("/course")
    public String welcome() {return "course";}

    @GetMapping("/courses")
    public String welcome1() {
        return "courses";
    }





}