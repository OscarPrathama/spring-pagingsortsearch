package com.masgan.pagination.GlobalTest;

import com.masgan.pagination.entities.Category;
import com.masgan.pagination.services.CategoryService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryTest {
    
    @Autowired
    CategoryService categoryService;

    // belum, error ketika dijalankan
    // coba cari cara store unit test store entity
    @Test
    public void store() {
        Category category = new Category();
        category.setName("News");
        category.setSlug("news");
        category.setDescription("First Category");

        System.out.println(category.toString());

        categoryService.save(category);
    }

}
