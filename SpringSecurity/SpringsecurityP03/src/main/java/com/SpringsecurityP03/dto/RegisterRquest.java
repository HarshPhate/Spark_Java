package com.SpringsecurityP03.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRquest {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 7)
    private String username;

    @NotBlank(message = "email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 5, max = 50, message = "Invalid email format")
    private String password;

    @NotBlank(message = "fullname is required")
    @Size(min = 5, max = 50, message = "Invalid email format")
    private String fullname;

   public RegisterRquest(){};

    public RegisterRquest(String username, String email, String password, String fullname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
