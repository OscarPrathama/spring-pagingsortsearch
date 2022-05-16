package com.masgan.pagination.GlobalTest;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.Test;

public class PostTest {
    
    private Faker faker = new Faker();

    @Test
    public void generatePost() {
        String postTitle = faker.book().title();
        // String postContent = faker.lorem().paragraph(80);
        String postSlug = postTitle;
        System.out.println(postSlug);
    }

}
