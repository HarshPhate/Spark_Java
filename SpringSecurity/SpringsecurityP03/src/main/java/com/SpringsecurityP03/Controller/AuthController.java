package com.SpringsecurityP03.Controller;

import com.SpringsecurityP03.dto.RegisterRquest;
import com.SpringsecurityP03.entity.User;
import com.SpringsecurityP03.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;  // ✅ FIXED: Changed from GetMapping
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ✅ FIXED: Changed @GetMapping to @PostMapping
    @PostMapping("/register")
    public ResponseEntity<Map<String , Object>> register(@Valid @RequestBody RegisterRquest rquest){

        try {
            User user = userService.registerUser(rquest);
            Map<String, Object> response = new HashMap<>();

            response.put("success", true);
            response.put("message", "user register successfully");
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());
            response.put("roles", user.getRoles().stream().map(r -> r.getName()).toList());

            return ResponseEntity.ok(response);

        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();

            response.put("success", false);
            response.put("message", "user registration failed: " + e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }


    // ✅ FIXED: Changed @GetMapping to @PostMapping, changed path, fixed service call
    @PostMapping("/register/admin")
    public ResponseEntity<Map<String , Object>> admin(@Valid @RequestBody RegisterRquest rquest){

        try {User user = userService.registerAdmin(rquest);
             // ✅ FIXED: Now calls registerAdmin()
            Map<String, Object> response = new HashMap<>();

            response.put("success", true);
            response.put("message", "admin register successfully");
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());
            response.put("roles", user.getRoles().stream().map(r -> r.getName()).toList());

            return ResponseEntity.ok(response);

        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();

            response.put("success", false);
            response.put("message", "admin registration failed: " + e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
}