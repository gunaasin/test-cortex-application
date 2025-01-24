package com.clone.amazon.web_controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LandingPage {
    @RequestMapping
    public String greet(HttpServletRequest request){
        // + request.getSession().getId()
        return "welcome to amazon" ;
    }


}
