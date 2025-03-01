package com.auth_api.spring_jwt_auth.serviceImpl;

import com.auth_api.spring_jwt_auth.dao.UserDao;
import com.auth_api.spring_jwt_auth.entity.User;
import com.auth_api.spring_jwt_auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private User dbUser;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao =userDao;
    }

    public User saveUser(User user){
        return userDao.save(user);
    }

    public User updateUser(long id,User user){
        dbUser = userDao.retrieveUserById(id);

        if(user.getFirstName()!=null){
            dbUser.setFirstName(user.getFirstName());
        }

        if(user.getLastName()!=null){
            dbUser.setLastName(user.getLastName());
        }

        if(user.getUsername()!=null){
            dbUser.setUsername(user.getUsername());
        }

        if(user.getPassword()!=null){
            dbUser.setPassword(user.getPassword());
        }

        if(user.getUserRole()!=null){
            dbUser.setUserRole(user.getUserRole());
        }

        return userDao.save(dbUser);
    }

    public void deleteUser(long id){
        userDao.delete(id);    }

    public List<User> getAllUsers(){
        return userDao.retrieveAllUsers();
    }

    public User getUserByUserId(long id){
        return  userDao.retrieveUserById(id);
    }

    public User getUserByUsername(String username){
        return userDao.retrieveUserByUsername(username);
    }
}

