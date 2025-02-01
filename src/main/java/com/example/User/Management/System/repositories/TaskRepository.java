package com.example.User.Management.System.repositories;

import com.example.User.Management.System.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {


}
