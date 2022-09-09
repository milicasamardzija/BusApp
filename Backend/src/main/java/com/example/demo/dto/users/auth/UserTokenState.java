package com.example.demo.dto.users.auth;

public class UserTokenState {
    public String accessToken;
    public Long expiresIn;
    public String role;
    public Boolean enabled;
    public int id;


    public UserTokenState(String accessToken, Long expiresIn, String role, Boolean enabled, int id) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.role = role;
        this.enabled = enabled;
        this.id = id;
    }
}
