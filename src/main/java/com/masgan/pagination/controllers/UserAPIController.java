package com.masgan.pagination.controllers;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;
import com.masgan.pagination.entities.User;
import com.masgan.pagination.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserAPIController {
    
    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @PostMapping("/seedUser")
    public void seedUser(){
        for (int i = 0; i < 5000; i++) {
            Faker faker = new Faker();
            User user = new User();
            Random random = new Random();
            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date dob = faker.date().birthday();

            user.setFirstName(faker.name().firstName());
            // user.setEmail(faker.internet().emailAddress());
            user.setEmail(faker.name().username() + i + "@gmail.com");
            user.setUsername(faker.name().username());
            user.setPassword("123qweasd");
            user.setStatus(random.nextInt(3) + 1);
            user.setBirth(dob);

            userService.save(user);   
        }
    }

}
