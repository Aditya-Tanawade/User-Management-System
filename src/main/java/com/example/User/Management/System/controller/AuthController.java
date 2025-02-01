package com.example.User.Management.System.controller;

import com.example.User.Management.System.dtos.AuthRequest;
import com.example.User.Management.System.dtos.AuthResponse;
import com.example.User.Management.System.dtos.UserDTO;
import com.example.User.Management.System.entities.User;
import com.example.User.Management.System.services.JwtService;
import com.example.User.Management.System.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);
            return new AuthResponse(token);
        }
        throw new RuntimeException("Invalid credentials");
    }



    @GetMapping("/load")
    private String adminLoad(){
        return userService.loadAdmins();
    }

    @PostMapping("/register")
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
}
