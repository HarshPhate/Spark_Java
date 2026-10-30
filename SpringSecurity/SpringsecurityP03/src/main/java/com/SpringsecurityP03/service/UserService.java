package com.SpringsecurityP03.service;

import com.SpringsecurityP03.dto.RegisterRquest;
import com.SpringsecurityP03.entity.Role;
import com.SpringsecurityP03.entity.User;
import com.SpringsecurityP03.repo.roleRepo;
import com.SpringsecurityP03.repo.userRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final userRepo userrepo;
    private final roleRepo rolerepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(userRepo userrepo, roleRepo rolerepo, PasswordEncoder passwordEncoder) {
        this.userrepo = userrepo;
        this.rolerepo = rolerepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(RegisterRquest rquest){

        if(userrepo.existsByUsername(rquest.getUsername())){

            throw new RuntimeException("username already exists :" + rquest.getUsername());
        }

        if(userrepo.existsByEmail(rquest.getEmail())){

            throw new RuntimeException("Email  already exists :" + rquest.getEmail());
        }

      Role userRole = rolerepo.findByName("USER")
        .orElseGet(()->{
            return rolerepo.save(Role.builder()
                    .name("USER")
                    .description("Default role")
                    .build());
        });

        User user = User.builder()
                .username(rquest.getUsername())
                .password(passwordEncoder.encode(rquest.getPassword()))
                .email(rquest.getEmail())
                .fullname(rquest.getFullname())
                .enabled(true)
                .accountNonLocked(true)
                .build();

        user.addRole(userRole);

        return userrepo.save(user);

    }

   @Transactional
    public User registerAdmin(RegisterRquest request){
        if(userrepo.existsByUsername(request.getUsername())){

            throw new RuntimeException("username already exists :" + request.getUsername());
        }

        if(userrepo.existsByEmail(request.getEmail())){

            throw new RuntimeException("Email  already exists :" + request.getEmail());
        }

        Role userRole = rolerepo.findByName("USER")
                .orElseGet(()->{
                    return rolerepo.save(Role.builder()
                            .name("USER")
                            .description("USER role")
                            .build());
                });

        Role adminRole = rolerepo.findByName("ADMIN")
                .orElseGet(()->{
                    return rolerepo.save(Role.builder()
                            .name("ADMIN")
                            .description("ADMIN role")
                            .build());
                });


        User admin = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .fullname(request.getFullname())
                .enabled(true)
                .accountNonLocked(true)
                .build();

        admin.addRole(userRole);
        admin.addRole(adminRole);

        User saveadmin = userrepo.save(admin);
        return saveadmin;
    }

    public User findByUsername(String username) {

        return userrepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    public List<User> findAllUsers() {

        return userrepo.findAll();
    }
}
