package com.masgan.pagination;

import java.util.Random;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.Test;

public class CommentTest {
    
    private Faker faker = new Faker();

    private Random random = new Random();

    @Test
    public void commentFactory() {
        
        String comments = faker.lorem().paragraph();
        int postId = random.nextInt(5) + 1;

        System.out.println(comments);
        System.out.println(postId);

    }

}
