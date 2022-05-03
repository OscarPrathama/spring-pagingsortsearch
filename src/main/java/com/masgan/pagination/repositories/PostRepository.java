package com.masgan.pagination.repositories;

import com.masgan.pagination.entities.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    @Query(
        value = "SELECT p FROM Post p WHERE p.postTitle LIKE %?1%"
    )
    public Page<Post> search(@Param("keyword") String keyword, Pageable pageable);

}
