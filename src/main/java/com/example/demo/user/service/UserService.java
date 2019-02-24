package com.example.demo.user.service;


import com.example.demo.user.view.UserRequest;

public interface UserService {

    Boolean login(UserRequest request) throws Exception;
}
