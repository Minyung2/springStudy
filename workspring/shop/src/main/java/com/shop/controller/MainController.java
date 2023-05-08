package com.shop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = "/")
    public String main(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        System.out.println(">>>>>>"+auth);
        return "main";
    }
}