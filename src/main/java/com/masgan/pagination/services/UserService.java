package com.masgan.pagination.services;

import java.util.List;
import java.util.Optional;

import com.masgan.pagination.entities.User;
import com.masgan.pagination.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepo;

    public List<User> findAll(){
        return userRepo.findAll(Sort.by(Direction.DESC, "createdAt"));
    }

    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String order) {
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                    Sort.by(sortField).ascending() : 
                    Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return userRepo.findAll(pageable);
    }

    public Page<User> findPaginatedSearching(
        String keyword,
        int pageNo,
        int pageSize,
        String sortField,
        String order
    ){
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() : 
                    Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return userRepo.search(keyword, pageable);
    }

    public void save(User user){
        userRepo.save(user);
    }

    public Optional<User> getUser(long id) {
        return userRepo.findById(id);
    }
    
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }


}
