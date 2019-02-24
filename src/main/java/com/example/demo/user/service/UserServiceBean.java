package com.example.demo.user.service;

import com.example.demo.user.model.User;
import com.example.demo.user.view.UserRequest;
import java.util.List;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.user.repository.UserRepository;

@Setter
@Log
@Service
public class UserServiceBean implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean login(UserRequest request) throws Exception {
        List<User> user = userRepository.findAll();
        if (user != null) {
            return true;
        }
        return false;
    }
}
