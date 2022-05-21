package com.masgan.pagination.services;

import java.util.List;

import com.masgan.pagination.entities.Comment;
import com.masgan.pagination.repositories.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public void save(Comment comment){
        commentRepository.save(comment);
    }

}
