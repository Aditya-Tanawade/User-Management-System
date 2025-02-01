package com.example.User.Management.System.controller;


import com.example.User.Management.System.dtos.TaskDTO;
import com.example.User.Management.System.dtos.UserDTO;
import com.example.User.Management.System.entities.Task;
import com.example.User.Management.System.entities.User;
import com.example.User.Management.System.services.TaskService;
import com.example.User.Management.System.services.UserService;
import jakarta.validation.Valid;
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
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return userService.findUser(id);
    }

    @PostMapping("/users/{userId}/assign-task")
    public TaskDTO assignTaskToUser(@PathVariable Long userId, @RequestBody @Valid TaskDTO taskDTO) {
        return taskService.assignTaskToUser(userId, taskDTO);
    }
}
