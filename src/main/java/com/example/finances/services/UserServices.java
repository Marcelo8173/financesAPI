package com.example.finances.services;

import com.example.finances.domain.userDomain.User;
import com.example.finances.repositories.UserRepository;
import com.example.finances.utils.exceptions.AlreadyExists;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserServices {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) throws AlreadyExists {
        var userSaved = userRepository.findByEmail(user.getEmail());
        if (userSaved.isPresent()) throw new AlreadyExists("This email already exist");
        return userRepository.save(user);

    }

    public User findById(UUID id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(User.class, "User not found"));
    }

    public User updateUser(User user, UUID id) {
        User userUpdate = findById(id);
        if (user.getName() != null && !user.getName().isEmpty()) {
            userUpdate.setName(user.getName());
        }

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            userUpdate.setEmail(user.getEmail());
        }
        return userRepository.save(userUpdate);
    }
}
