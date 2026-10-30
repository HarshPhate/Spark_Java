package com.example.SecurityP01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BankController {

    @GetMapping("/info")
    public String infor(){
        return "get loan @07";
    }
}
