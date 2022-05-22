package com.masgan.pagination.repositories;

import com.masgan.pagination.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query(
        value = "SELECT u FROM User u WHERE u.firstName LIKE %?1% OR u.email LIKE %?1%"
    )
    public Page<User> search(@Param("keyword") String keyword, Pageable pageable);

}
