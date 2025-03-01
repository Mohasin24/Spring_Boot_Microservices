package org.auth_service.dao;


import org.auth_service.entity.User;
import org.auth_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDao {
    private final UserRepo userRepo;

    @Autowired
    public UserDao(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public void delete(long id) {
        Optional<User> userOptional = userRepo.findById(id);
        userRepo.delete(userOptional.get());
    }

    public List<User> retrieveAllUsers() {
        return userRepo.findAll();
    }

    public User retrieveUserById(long id) {
        Optional<User> userOptional = userRepo.findById(id);
        return userOptional.get();
    }

    public User retrieveUserByUsername(String username) {
        Optional<User> userOptional = userRepo.findByUsername(username);
        return userOptional.get();
    }
}
