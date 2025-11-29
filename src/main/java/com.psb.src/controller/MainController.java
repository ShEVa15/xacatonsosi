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

    @GetMapping("/task")
    public String welcome() {return "index3";}

    @GetMapping("/courses")
    public String welcome1() {
        return "index4";
    }





}