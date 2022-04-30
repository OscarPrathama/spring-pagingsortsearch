package com.masgan.pagination.services;

import java.util.List;
import java.util.Optional;

import com.masgan.pagination.entities.Post;
import com.masgan.pagination.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    
    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public Optional<Post> getPost(Long id){
        return postRepository.findById(id);
    }

    public void save(Post post){
        postRepository.save(post);
    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }

}
