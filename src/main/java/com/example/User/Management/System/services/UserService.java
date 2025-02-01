package com.example.User.Management.System.services;

import com.example.User.Management.System.dtos.TaskDTO;
import com.example.User.Management.System.dtos.UserDTO;
import com.example.User.Management.System.entities.Role;
import com.example.User.Management.System.entities.User;
import com.example.User.Management.System.exceptions.ResourceNotFoundException;
import com.example.User.Management.System.repositories.UserRepository;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }




    public List<UserDTO> getAllUsers() {
        List<User>allUsers=userRepository.findAll();
        return allUsers.stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .toList();
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user =mapper.map(userDTO,User.class);

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return mapper.map(userRepository.save(user),UserDTO.class);
    }


    public UserDTO updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.map(userRepository.save(existingUser),UserDTO.class);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    public List<TaskDTO> getUserTasks(String email) {
        User user = getUserByEmail(email);

        return user.getTasks().stream()
                .map(task -> mapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }


    public String loadAdmins() {
        User user1= new User(1L,"ADMIN","admin@gmail.com",passwordEncoder.encode("123456"),Role.ADMIN);

        userRepository.save(user1);
        return "Admin Loaded Successfully";
    }



    public UserDTO findUser(Long id) {

        User user= userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User NOt FOund With This Id :- " + id));

        return mapper.map(user,UserDTO.class);
    }
}
