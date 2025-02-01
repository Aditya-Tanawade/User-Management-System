package com.example.User.Management.System.services;

import com.example.User.Management.System.entities.Task;
import com.example.User.Management.System.entities.User;
import com.example.User.Management.System.exceptions.ResourceNotFoundException;
import com.example.User.Management.System.repositories.TaskRepository;
import com.example.User.Management.System.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Task assignTaskToUser(Long userId, String taskName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        Task task = new Task();
        task.setTaskName(taskName);
        task.setUser(user);

        return taskRepository.save(task);
    }


}
