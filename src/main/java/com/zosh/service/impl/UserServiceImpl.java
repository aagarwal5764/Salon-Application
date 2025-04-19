package com.zosh.service.impl;

import com.zosh.exception.UserException;
import com.zosh.model.User;
import com.zosh.repository.UserRepository;
import com.zosh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new UserException("User not found with id: " + id);
    }

    @Override
    public User updateUser(User user, Long id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            User existingUser = opt.get();
            existingUser.setFullName(user.getFullName());
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setRole(user.getRole());
            existingUser.setCreatedAt(user.getCreatedAt());
            existingUser.setUpdatedAt(user.getUpdatedAt());

            return userRepository.save(existingUser);
        }
        throw new UserException("User not found with id: " + id);
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            User existingUser = opt.get();
            userRepository.delete(existingUser);
        }
        else{
            throw new UserException("User not found with id " + id);
        }
    }
}
