package com.masgan.pagination.GlobalTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.Test;

public class GlobalTest {
    
    @Test
    public void getRandomNumberRange(){
        for (int i = 0; i < 12; i++) {
            Random random = new Random();   
            int my_number = random.nextInt(5)+1;
            System.out.println(my_number);
        }
    }

    @Test
    public void getRandomDateFormat(){
        Faker faker = new Faker();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 5; i++) {
            // 1
            // String dob = sdf.format(faker.date().birthday());
            // System.out.println(dobToDate);

            // 2 convert string "1979-05-18 06:20:14" to date
            Date dob = faker.date().birthday();
            String string_dob = sdf.format(dob);
            try {
                Date date = sdf.parse(string_dob);
                System.out.println(sdf.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // System.out.println(string_dob);
        }
    }

}
