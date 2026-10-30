package com.JWTproject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello User!";
    }

    @GetMapping("/buddy")
    public String buddy() {
        return "Hello Buddy!";
    }
}