package com.example.User.Management.System.dtos;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;

}
