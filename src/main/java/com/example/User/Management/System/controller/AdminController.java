package com.example.User.Management.System.controller;


import com.example.User.Management.System.dtos.UserDTO;
import com.example.User.Management.System.dtos.UserWithTasksDTO;
import com.example.User.Management.System.entities.User;
import com.example.User.Management.System.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @GetMapping("/users-with-tasks")
    public List<UserWithTasksDTO> getAllUsersWithTasks() {
        return userService.getUsersWithTasks();
    }

    @PostMapping("/users")
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/users/{userId}/tasks")
    public UserWithTasksDTO getUserWithTasks(@PathVariable Long userId) {
        return userService.getUserWithTasksById(userId);
    }
}
