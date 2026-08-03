package com.example.ecommerceapi.controller;

import com.example.ecommerceapi.modal.User;
import com.example.ecommerceapi.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @Autowired
    userService Service;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return Service.reg(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return Service.log(user);
    }
}
