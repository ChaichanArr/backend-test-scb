package com.example.demo.user.service;


import com.example.demo.user.model.User;
import com.example.demo.user.view.UserRequest;

public interface UserService {

    Boolean login(UserRequest request) throws Exception;

    User getUserById(Long id)  throws Exception;

    void deleteUser(Long id) throws Exception;

    void createUser(User user) throws Exception;
}
