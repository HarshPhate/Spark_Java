package com.example.dockerp01;

import org.springframework.web.bind.annotation.GetMapping; import org.springframework.web.bind.annotation.RequestMapping; import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping 
public class testController {

    @GetMapping("/test")
    public String Test(){ return "Hello Bhai"; }

    @GetMapping("/info")
    public String info(){ return "Hello info Bhai"; }

}