package com.auth_api.spring_jwt_auth.service;

import com.auth_api.spring_jwt_auth.entity.User;

public interface AuthService
{
    User newUserRegistration(User user);

//    User userLogin(UserLogin userLogin);

    String verifyUser(User user);
}
