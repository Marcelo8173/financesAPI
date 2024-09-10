package com.example.finances.controllers;

import com.example.finances.domain.userDomain.User;
import com.example.finances.domain.userDomain.userDTO.UserDTO;
import com.example.finances.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
@Component
public class UserController {

    private final PasswordEncoder encoder;
    @Autowired
    private UserServices userServices;

    public UserController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        User userSaved = userServices.createUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(userSaved.getName(), userSaved.getEmail()));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user, @PathVariable UUID id) {
        User userSaved = userServices.updateUser(user, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(userSaved.getName(), userSaved.getEmail()));
    }
}
