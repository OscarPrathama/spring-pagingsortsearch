package com.masgan.pagination.services;

import java.util.List;

import com.masgan.pagination.entities.Employee;

import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    Page<Employee> search(String keyword, int pageNo, int pageSize, String sortField, String sortDirection);

}
