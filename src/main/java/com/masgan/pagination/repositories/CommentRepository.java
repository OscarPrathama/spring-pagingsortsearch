package com.masgan.pagination.repositories;

import com.masgan.pagination.entities.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    @Query(
        value = "SELECT c FROM Comment c WHERE c.comments LIKE %?1%"
    )
    public Page<Comment> search(@Param("keyword") String keyword, Pageable pageable);

}
