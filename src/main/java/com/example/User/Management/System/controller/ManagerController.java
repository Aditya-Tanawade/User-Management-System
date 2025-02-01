package com.example.User.Management.System.controller;


import com.example.User.Management.System.entities.Task;
import com.example.User.Management.System.entities.User;
import com.example.User.Management.System.services.TaskService;
import com.example.User.Management.System.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.findUser(id);
    }

    @PostMapping("/users/{userId}/assign-task")
    public Task assignTaskToUser(@PathVariable Long userId, @RequestBody String taskName) {
        return taskService.assignTaskToUser(userId, taskName);
    }
}
