package com.example.ecommerceapi.service;

import com.example.ecommerceapi.customException.userAlreadyExistException;
import com.example.ecommerceapi.modal.User;
import com.example.ecommerceapi.repository.userRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    userRepo repo;

    @Autowired
    jwtService jwt;

    @Autowired
    AuthenticationManager auth;

    public User reg(User user) {

        if(repo.findByUsername(user.getUsername()) != null){
            throw new userAlreadyExistException("UsernameAlreadyExist");
        }
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        String Pazz = b.encode(user.getPazzword());
        user.setPazzword(Pazz);
        return repo.save(user);
    }

    public String log(User user) {
        UsernamePasswordAuthenticationToken use = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPazzword());
        Authentication token = auth.authenticate(use);
        if(token.isAuthenticated()){
            return jwt.generateToken(user);
        }
        else{
            return "not a correct token";
        }
    }


}
