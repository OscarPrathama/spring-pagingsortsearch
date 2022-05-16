package com.masgan.pagination.services;

import java.util.List;
import java.util.Optional;

import com.masgan.pagination.entities.User;
import com.masgan.pagination.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepo;

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public void save(User user){
        userRepo.save(user);
    }

    public Optional<User> getUser(long id) {
        return userRepo.findById(id);
    }


}
