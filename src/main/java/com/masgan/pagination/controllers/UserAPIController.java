package com.masgan.pagination.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.github.javafaker.Faker;
import com.masgan.pagination.entities.Post;
import com.masgan.pagination.entities.User;
import com.masgan.pagination.services.PostService;
import com.masgan.pagination.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/users")
public class UserAPIController {
    
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    /**
     * Get Users without pagination
     * 
     * @return  Object  Users
    */
    @GetMapping("/all")
    public List<User> getUsersWithoutPaging(){
        return userService.findAll();
    }

    /**
     * Get Users with pagination, sorting, ordering
     * 
     * @return  Object  Users
    */
    @GetMapping("/")
    public List<User> getUsers(){
        return findPaginated(1, "createdAt", "asc");
    }

    /**
     * Function for get Users with pagination, sorting, ordering
     * 
     * @param   int     page number
     * @return  object  Users
    */
    @GetMapping("/page/{pageNo}")
    public List<User> findPaginated(
        @PathVariable(value = "pageNo") int pageNo,
        @RequestParam(value = "sortField") String sortField,
        @RequestParam(value = "order") String order
    ) {
        int pageSize = 10;
        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, order);
        List<User> usersList = page.getContent();
        // int totalPages = page.getTotalPages();
        // long totalElements = page.getTotalElements();
        // int numberOfElements = page.getNumberOfElements();
        // int size = page.getSize();
        
        return usersList;
    }

    /**
     * Get specific User
     * 
     * @param   Long     id
     * @return  Object  user
    */
    @GetMapping("/view/{id}")
    public User getUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUser(id);
        if(!user.isPresent()){
            throw new RuntimeException("User with id : "+id+" not found");
        }
        return user.get();
    }

    /**
     * Get User Posts
     * 
     * @param   Long    id
     * @return  Object  posts
     * 
    */
    @GetMapping("/view/{id}/posts")
    public List<Post> getUserPost(@PathVariable("id") Long id){
        Optional<User> user = userService.getUser(id);
        if(!user.isPresent()){
            throw new RuntimeException("User with id : "+id+" not found !");
        }
        return user.get().getPosts();
    }

    /**
     * Store/Update User
     * 
     * @param   Long    id
     * @return  String
    */
    @PostMapping("")
    public String store(@RequestBody User user) {
        userService.save(user);
        
        return "Saved...";
    }

    /**
     * Update specific User
     * 
     * @param   Long    id
     * @return  String  
    */
    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> find_user = userService.getUser(id);
        User getUser = null;
        if (!find_user.isPresent()) {
            throw new RuntimeException("User with id "+id+" not found");
        }else{
            getUser = find_user.get();
            getUser.setFirstName(user.getFirstName());
            getUser.setUsername(user.getUsername());
            getUser.setEmail(user.getEmail());
            getUser.setPassword(user.getPassword());
            getUser.setStatus(user.getStatus());
            getUser.setBirth(user.getBirth());

            userService.save(getUser);
        }
        
        return "Updated...";
    }

    /**
     * Delete specific User
     * 
     * @param   Long    id
     * @return  String
    */
    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Optional<User> find_user = userService.getUser(id);
        if(!find_user.isPresent()){
            throw new RuntimeException("User with id "+id+" not found");
        }else{
            userService.deleteUser(id);
        }
        
        return "User deleted";
    }

    
    /**
     * Bulk action to insert 20000 users = 37 minutes
    */
    @PostMapping("/seedUser")
    public void seedUsers(){
        for (int i = 0; i < 20000; i++) {
            Faker faker = new Faker();
            User user = new User();
            Random random = new Random();
            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date dob = faker.date().birthday();

            user.setFirstName(faker.name().firstName());
            // user.setEmail(faker.internet().emailAddress());
            user.setEmail(faker.name().username() + i + "@gmail.com");
            user.setUsername(faker.name().username());
            user.setPassword("123qweasd");
            user.setStatus(random.nextInt(3) + 1);
            user.setBirth(dob);

            userService.save(user);   
        }
    }

}
