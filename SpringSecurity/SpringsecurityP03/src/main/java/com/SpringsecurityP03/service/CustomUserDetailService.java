package com.SpringsecurityP03.service;

import com.SpringsecurityP03.entity.User;
import com.SpringsecurityP03.repo.userRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

 private final userRepo UserRepo;

    public CustomUserDetailService(userRepo userRepo) {
        this.UserRepo = userRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user =  UserRepo.findByUsername(username)
                .orElseThrow(()-> new RuntimeException(" User not found"));

      return buildUserDetails(user);

  }

  private UserDetails buildUserDetails(User user){
       Collection<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> {
                    String roleName = role.getName().startsWith("Role_") ? role.getName() : "ROLE_" + role.getName();

                    return  new SimpleGrantedAuthority(roleName);
                })
                .collect(Collectors.toList());

       return org.springframework.security.core.userdetails.User.builder()
               .username(user.getUsername())
               .password(user.getPassword())
               .authorities(authorities)
               .accountExpired(false)
               .accountLocked(!user.isAccountNonLocked())
               .credentialsExpired(false)
               .build();
  }
}
