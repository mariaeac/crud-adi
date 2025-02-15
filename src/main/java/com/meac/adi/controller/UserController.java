package com.meac.adi.controller;


import com.meac.adi.entities.User;
import com.meac.adi.entities.dtos.CreateUserDTO;
import com.meac.adi.entities.dtos.ResponseUserDTO;
import com.meac.adi.repositories.UserRepository;
import com.meac.adi.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserServices userServices;


    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping
   public ResponseEntity<User> createUser(@RequestBody CreateUserDTO createUserDTO) {
       User user = userServices.createUser(createUserDTO);
       return ResponseEntity.created(URI.create("/api/v1/user/" + user.getUserId())).build();
   }

   @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getUser(@PathVariable("id") UUID userId) {
        ResponseUserDTO user = userServices.getUser(userId);
        return ResponseEntity.ok(user);
   }

   @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userServices.getAllUsers();
        return ResponseEntity.ok(users);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID userId) {
        userServices.deleteUser(userId);
        return ResponseEntity.noContent().build();
   }

   @PutMapping
    public ResponseEntity<User> updateById(@RequestBody UpdateUserDTO updateUserDTO) {
        User user = userServices.updateUser(updateUserDTO);
        return ResponseEntity.ok().body(user);
   }



}
