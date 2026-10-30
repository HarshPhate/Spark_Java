package com.example.SpringsecurityP02.controller;


import org.springframework.security.core.Authentication;  // ✅ CORRECT
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class Apicontroller {

    @GetMapping("/user")
    public Map<String, Object> userEndPonit(){
        //Authentication
        //Type of object.
        //Stores:  username ,password,roles, authenticated status
        //SecurityContextHolder:-  which contains authentication information. or Class that stores security data for current request/user.
        //getContext() :- //Returns current: SecurityContext
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  //getAuthentication() Returns:Authentication object of current user.
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to user EndPoint");
        response.put("endpoint","/api/user");
        response.put("accessevel", "Authenticated user");
        response.put("timestamp", LocalDate.now().toString());
        response.put("user",getUserInnfo(auth));

        return response;

    }

    @GetMapping("/admin")
    public Map<String, Object> adminEndPonit(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to admin EndPoint");
        response.put("endpoint","/api/admin");
        response.put("accessevel", "Authenticated user");
        response.put("timestamp", LocalDate.now().toString());
        response.put("user",getUserInnfo(auth));
        response.put("adminSecret", "you have admin access");

        return response;

    }

    private Map<String, Object> getUserInnfo(Authentication authentication){

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username",
                authentication.getName());
        userInfo.put("roles", authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        userInfo.put("authentiecate", authentication.isAuthenticated());

        return userInfo;
    }
}
