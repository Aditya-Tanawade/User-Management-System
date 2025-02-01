package com.example.User.Management.System.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithTasksDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private List<TaskDTO> tasks;
}
