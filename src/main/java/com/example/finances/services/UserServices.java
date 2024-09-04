package com.example.finances.services;

import com.example.finances.domain.userDomain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {
    // injetar repositorio

    //mock
    private List<User> listUser = new ArrayList<>();

    //metodo mocado para user
    public User createUser(User user) {
        listUser.add(user);
        return user;
    }
}
