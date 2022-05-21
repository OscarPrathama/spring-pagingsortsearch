package com.masgan.pagination.GlobalTest;


import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import com.masgan.pagination.repositories.PostRepository;
import com.masgan.pagination.services.PostService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PostTest {
    
    private Faker faker = new Faker();
    Slugify slug = new Slugify();
    String result = slug.slugify("Hello, world!");

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepo;

    @Test
    public void generatePost() {
        String postTitle = faker.book().title();
        // String postContent = faker.lorem().paragraph(80);
        String postSlug = slug.slugify(postTitle);
        System.out.println(postSlug);
    }

}
