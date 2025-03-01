package com.auth_api.spring_jwt_auth.service;

import com.auth_api.spring_jwt_auth.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService
{
    User saveUser(User user);
    User updateUser(long id,User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    User getUserByUserId(long id);

    User getUserByUsername(String username);
}
