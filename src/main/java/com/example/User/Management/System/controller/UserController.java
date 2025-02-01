package com.example.User.Management.System.controller;

import com.example.User.Management.System.dtos.TaskDTO;
import com.example.User.Management.System.entities.User;
import com.example.User.Management.System.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public User getProfile(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/tasks")
    public List<TaskDTO> getUserTasks(@RequestParam String email) {
        return userService.getUserTasks(email);
    }
}
