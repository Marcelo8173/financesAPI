package com.example.finances.controllers;

import com.example.finances.domain.userDomain.User;
import com.example.finances.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/user")
@Component
public class UserController {

    @Autowired
    private UserServices userServices;
    private final PasswordEncoder encoder;

    public UserController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody @Valid User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        User userSaved = userServices.createUser(user);

//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId()).toUri();
        return ResponseEntity.ok().body(userSaved);
    }
}
