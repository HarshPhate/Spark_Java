package com.SpringsecurityP03.Controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard(){
        return "admin accessing dashboard";
    }


    //already protected by url (api/admin/**)
    // adding pre @preauethorize as a extra layer of prtection
    @GetMapping("users")
    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("ROLE_ADMIN") //MUST HAVE  :ROLE RREFIX
    public List<String> getallUsers(){

        List<String> users = new ArrayList<>();
        users.add(new String("Raj"));
        users.add(new String("priya"));
        users.add(new String("abu"));
        users.add(new String("roni"));
        users.add(new String("dhon"));

        return users;
    }
}
