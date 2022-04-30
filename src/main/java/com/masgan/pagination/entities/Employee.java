package com.masgan.pagination.entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Override
    public String toString() {
        // return "Employee [email=" + email + ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName + "]";

        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("id", Long.toString(id));
        myMap.put("firstName", firstName);
        myMap.put("lastName", lastName);
        myMap.put("email", email);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(myMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

}
