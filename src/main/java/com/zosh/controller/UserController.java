package com.zosh.controller;

import com.zosh.model.User;
import com.zosh.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/users")
    public User createUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/api/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable Long id) throws Exception{
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new Exception("User not found with id: " + id);
    }

    @PutMapping("api/users/{id}")
    public User updateUser(@RequestBody User user,
                           @PathVariable Long id) throws Exception{
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            User existingUser = opt.get();
            existingUser.setFullName(user.getFullName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setRole(user.getRole());
            existingUser.setCreatedAt(user.getCreatedAt());
            existingUser.setUpdatedAt(user.getUpdatedAt());

            return userRepository.save(existingUser);
        }
        throw new Exception("User not found with id: " + id);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUserById(@PathVariable Long id) throws Exception{
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            User existingUser = opt.get();
            userRepository.delete(existingUser);
        }
        else{
            throw new Exception("User not found with id " + id);
        }
    }

}
