package org.auth_service.service;


import org.auth_service.entity.User;

public interface AuthService {
    User newUserRegistration(User user);

}
