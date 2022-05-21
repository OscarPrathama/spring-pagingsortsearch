package com.masgan.pagination.controllers;

import java.util.List;
import java.util.Optional;

import com.github.javafaker.Faker;
import com.masgan.pagination.entities.Comment;
import com.masgan.pagination.entities.Post;
import com.masgan.pagination.services.CommentService;
import com.masgan.pagination.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentAPIController {
    
    private Faker faker = new Faker();

    @Autowired
    CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping("/all")
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    /**
     * Seed comments
     * 
     * @return void
    */
    @PostMapping("/seedComment")
    public String seedComments(){
        Post post = getPost(4);
        for (int i = 0; i < 3; i++) {

            Comment comment = new Comment();

            comment.setComments(faker.lorem().paragraph());
            comment.setPost(post);

            commentService.save(comment);
        }

        return "Success";
    }

    public Post getPost(long id){
        Optional<Post> findPost = postService.getPost(id);
        if (!findPost.isPresent()) {
            throw new RuntimeException("Post with id : "+id+" not found !");
        }
        return findPost.get();
    }

}
