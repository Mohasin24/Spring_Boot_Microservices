package org.auth_service.serviceImpl;


import org.auth_service.constants.Roles;
import org.auth_service.dao.UserDao;
import org.auth_service.entity.User;
import org.auth_service.jwt.JwtService;
import org.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService
{
    private UserDao userDao;
    private BCryptPasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtService jwtService;
    @Autowired
    public AuthServiceImpl(
            UserDao userDao,
            BCryptPasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    )
    {
        this.userDao=userDao;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
        this.authenticationManager=authenticationManager;
    }

    @Override
    public User newUserRegistration(User user){
        user.setUserRole(Roles.USER.name());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }


    @Override
    public String verifyUser(User user) {

//        check if the user is authenticated or not
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

//        check if authentication is successful

        if(authentication.isAuthenticated()){
            String token = jwtService.generateToken(user.getUsername());
            return token;
        }

        return "fail";
    }
}
