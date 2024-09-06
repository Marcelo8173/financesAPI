package com.example.finances.services;

import com.example.finances.domain.userDomain.User;
import com.example.finances.repositories.UserRepository;
import com.example.finances.utils.exceptions.AlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) throws AlreadyExists {

        var userSaved = userRepository.findByEmail(user.getEmail());

        if (userSaved.isPresent()) throw new AlreadyExists("This email already exist");

        return userRepository.save(user);

    }
}
