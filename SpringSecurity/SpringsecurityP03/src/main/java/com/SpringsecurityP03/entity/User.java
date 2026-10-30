package com.SpringsecurityP03.entity;

import jakarta.persistence.*;
import com.SpringsecurityP03.entity.Role;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(unique = true, nullable = false, length = 20)
    private String password;

    @Column(unique = true, nullable = false, length = 20)
    private String email;

    @Column(unique = true, nullable = false, length = 20)
    private String fullname;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean accountNonLocked = true;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // ✅ FIXED: Added @PrePersist annotation
    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    public User(){

    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder{
        private String username;
        private String email;
        private String password;
        private String fullname;
        private boolean enabled;
        private boolean accountNonLocked;

        public UserBuilder username(String username){
            this.username = username;
            return this;
        }

        // ✅ FIXED: Changed parameter from 'username' to 'email'
        public UserBuilder email(String email){
            this.email = email;
            return this;
        }

        // ✅ FIXED: Changed parameter from 'username' to 'password'
        public UserBuilder password(String password){
            this.password = password;
            return this;
        }

        // ✅ FIXED: Changed parameter from 'username' to 'fullname'
        public UserBuilder fullname(String fullname){
            this.fullname = fullname;
            return this;
        }

        // ✅ FIXED: Changed parameter from 'username' to 'enabled'
        public UserBuilder enabled(boolean enabled){
            this.enabled = enabled;
            return this;
        }

        // ✅ FIXED: Changed parameter from 'username' to 'accountNonLocked'
        public UserBuilder accountNonLocked(boolean accountNonLocked){
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public User build(){
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setFullname(fullname);
            user.setPassword(password);
            user.setAccountNonLocked(accountNonLocked);
            user.setEnabled(enabled);

            return user;
        }
    }
}