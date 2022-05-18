package com.masgan.pagination.GlobalTest;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;

import org.junit.jupiter.api.Test;

public class PostTest {
    
    private Faker faker = new Faker();
    Slugify slug = new Slugify();
    String result = slug.slugify("Hello, world!");

    @Test
    public void generatePost() {
        String postTitle = faker.book().title();
        // String postContent = faker.lorem().paragraph(80);
        String postSlug = slug.slugify(postTitle);
        System.out.println(postSlug);
    }

}
