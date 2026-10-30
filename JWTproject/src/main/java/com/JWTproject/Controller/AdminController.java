package com.JWTproject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome Admin Dashboard";
    }

    @GetMapping("/settings")
    public String settings() {
        return "Admin Settings Accessed";
    }
}