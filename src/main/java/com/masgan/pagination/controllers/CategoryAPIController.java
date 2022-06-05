package com.masgan.pagination.controllers;

import java.util.List;
import java.util.Set;

import com.masgan.pagination.entities.Category;
import com.masgan.pagination.entities.Post;
import com.masgan.pagination.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryAPIController {

    @Autowired
    CategoryService categoryService;

    /**
     * Get all categories
     * 
     * @return  List    Categories
    */
    @GetMapping()
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    /**
     * Save new category
     * 
     * @param   Entity  Category
     * @return  String
    */
    @PostMapping()
    public String save(@RequestBody Category category){
        categoryService.save(category);
        return "Saved...";
    }

    /**
     * Get specific category
     * 
     * @param   Long        id
     * @return  Object    category
    */
    @GetMapping("/view/{id}")
    public Category getCategory(@PathVariable("id") Long id) {
        return categoryService.findCategory(id);
    }

    /**
     * Get posts category
     * 
     * @param   Long    id
     * @return  Object  Post
    */
    @GetMapping("/view/{id}/posts")
    public Set<Post> getPostsCategory(@PathVariable("id") Long id) {
        Category getCategory = categoryService.findCategory(id);
        Set<Post> posts = getCategory.getPost();
        return posts;
    }
    

}
