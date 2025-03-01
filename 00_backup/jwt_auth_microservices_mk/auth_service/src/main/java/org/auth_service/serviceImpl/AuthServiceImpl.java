package org.auth_service.serviceImpl;


import org.auth_service.dao.UserDao;
import org.auth_service.entity.User;
import org.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private UserDao userDao;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(
            UserDao userDao,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User newUserRegistration(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }
}
