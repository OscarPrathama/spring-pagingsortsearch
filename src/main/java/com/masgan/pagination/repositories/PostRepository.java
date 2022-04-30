package com.masgan.pagination.repositories;

import com.masgan.pagination.entities.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
