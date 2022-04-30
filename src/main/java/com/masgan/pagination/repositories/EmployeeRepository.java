package com.masgan.pagination.repositories;

// import java.util.List;

import com.masgan.pagination.entities.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    @Query(
        value = "SELECT e FROM Employee e WHERE e.firstName LIKE %?1%"
        // ,countQuery = "SELECT count(*) FROM employees WHERE first_name LIKE %?1%"
        // ,nativeQuery = true
    )
    public Page<Employee> search(@Param("keyword") String keyword, Pageable pageable);

}
