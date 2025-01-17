package com.clone.amazon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LandingPage {
    @RequestMapping
    public String greet(){
        return "welcome to amazon";
    }


}
