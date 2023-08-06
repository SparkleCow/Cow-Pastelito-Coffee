package com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users;

import lombok.Data;

@Data
public class AuthResponse {
    public String token;
    public String tokenType = "Bearer ";
    public AuthResponse(String token) {
        this.token = token;
    }
}
