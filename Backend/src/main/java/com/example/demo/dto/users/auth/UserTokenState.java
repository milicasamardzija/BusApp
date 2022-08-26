package com.example.demo.dto.users.auth;

public class UserTokenState {
    public String accessToken;
    public Long expiresIn;
    public String role;
    public Boolean enabled;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public UserTokenState(String accessToken, long expiresIn, String role, boolean enabled) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.role = role;
        this.enabled = enabled;
    }
}
