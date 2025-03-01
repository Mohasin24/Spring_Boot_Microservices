package org.auth_service.service;

import org.auth_service.entity.User;

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
