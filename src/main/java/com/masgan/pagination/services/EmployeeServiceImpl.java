package com.masgan.pagination.services;

import java.util.List;
import java.util.Optional;

import com.masgan.pagination.entities.Employee;
import com.masgan.pagination.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent()){
            employee = optional.get();
        }else{
            throw new RuntimeException("Employee not found for id :: "+id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        // for sorting
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
        Sort.by(sortField).ascending() :
        Sort.by(sortField).descending();
        
        // for pagination
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        System.out.println("coba " + pageable.toString());

        return employeeRepository.findAll(pageable);
    }

    public Page<Employee> search(String keyword, int pageNo, int pageSize, String sortField, String sortDir){
        // for sorting
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
        Sort.by(sortField).ascending() : 
        Sort.by(sortField).descending();

        // for pagination
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return employeeRepository.search(keyword, pageable);
    }

}
