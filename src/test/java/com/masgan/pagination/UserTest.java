package com.masgan.pagination;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void storeUser(){
        Faker faker = new Faker();

        System.out.println(faker.name().firstName());
        System.out.println(faker.internet().emailAddress());
        System.out.println(faker.name().username());


    }

}
