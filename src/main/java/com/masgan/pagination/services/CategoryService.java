package com.masgan.pagination.services;

import java.util.List;
import java.util.Optional;

import com.masgan.pagination.entities.Category;
import com.masgan.pagination.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository categoryRepo;

    public List<Category> findAll(){
        return categoryRepo.findAll();
    }

    public List<Category> findPaginated(int pageNo, int pageSize, String sortField, String order){
        return null;
    }

    public List<Category> findSearchedPaginated(
        String keyword,
        int pageNo,
        int pageSize,
        String sortField,
        String order
    ){
        return null;
    }

    public String save(Category category){
        categoryRepo.save(category);
        return "Saved...";
    }

    public Category findCategory(long id){
        Optional<Category> findCategory = categoryRepo.findById(id);
        if(!findCategory.isPresent()){
            throw new RuntimeException("Category with id "+id+" not found !");
        }else{
            return findCategory.get();
        }
    }

    public String delete(long id){
        Optional<Category> findCategory = categoryRepo.findById(id);
        if(!findCategory.isPresent()){
            throw new RuntimeException("Category with id "+id+" not found !");
        }else{
            categoryRepo.deleteById(id);
            return "Deleted...";
        }
    }

}
