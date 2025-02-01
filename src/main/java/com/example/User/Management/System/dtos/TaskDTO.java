package com.example.User.Management.System.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;


    @NotBlank(message = "Task Name cannot be empty")
    private String taskName;
    private Long userId;
}
