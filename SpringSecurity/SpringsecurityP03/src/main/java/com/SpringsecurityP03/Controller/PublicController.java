package com.SpringsecurityP03.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/hello")
    public String hello(){
        return "hello from cfs";
    }

    @GetMapping("/info")
    public String info(){
        return "New course start from apr";
    }


}
