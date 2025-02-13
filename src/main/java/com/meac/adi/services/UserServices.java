package com.meac.adi.services;

import com.meac.adi.entities.User;
import com.meac.adi.entities.dtos.CreateUserDTO;
import com.meac.adi.exceptions.CustomizedExceptions;
import com.meac.adi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(CreateUserDTO user) {
        userValidations(user);
        User newUser = new User();
        newUser.setUsername(user.username());
        newUser.setEmail(user.email());
        newUser.setPassword(user.password());

       return userRepository.save(newUser);

    }
    public User getUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();
        return users;
    }

    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }
        userRepository.deleteById(userId);
    }

    public void userValidations(CreateUserDTO user) {
        if (userRepository.findByEmail(user.email()).isPresent()) {
            throw new CustomizedExceptions.DuplicateUserInfoException("Email already exists!");
        }

        if (userRepository.findByUsername(user.username()).isPresent()) {
            throw new CustomizedExceptions.DuplicateUserInfoException("Username already exists!");
        }
    }

}
