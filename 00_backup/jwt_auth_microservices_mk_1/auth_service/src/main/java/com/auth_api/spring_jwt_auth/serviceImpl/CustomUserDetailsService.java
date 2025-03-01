package com.auth_api.spring_jwt_auth.serviceImpl;

import com.auth_api.spring_jwt_auth.dao.UserDao;
import com.auth_api.spring_jwt_auth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserDao userDao;

    public CustomUserDetailsService(){}
    @Autowired
    public CustomUserDetailsService(UserDao userDao){
        this.userDao=userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.retrieveUserByUsername(username);
        return new CustomUserDetails(user);
    }
}
