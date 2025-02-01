package com.example.User.Management.System.services;

import com.example.User.Management.System.dtos.TaskDTO;
import com.example.User.Management.System.entities.Task;
import com.example.User.Management.System.entities.User;
import com.example.User.Management.System.exceptions.ResourceNotFoundException;

import com.example.User.Management.System.repositories.TaskRepository;
import com.example.User.Management.System.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper; // For DTO conversion

    public TaskDTO assignTaskToUser(Long userId, TaskDTO taskDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        Task task =mapper.map(taskDTO,Task.class);

        return mapper.map(taskRepository.save(task),TaskDTO.class);
    }
}
