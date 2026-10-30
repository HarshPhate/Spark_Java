package com.SpringsecurityP03.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/course")
    public String course(){
        return "you are enrol";
    }

    @GetMapping("/profile")
    public String profile(){
        return "you are student";
    }
}
