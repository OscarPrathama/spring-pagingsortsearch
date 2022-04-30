package com.masgan.pagination.controllers;

import java.util.List;
import java.util.Optional;

import com.masgan.pagination.entities.Post;
import com.masgan.pagination.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/posts")
public class PostAPIController {
    
    @Autowired
    PostService postService;

    @GetMapping(value="")
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping(value="/view/{id}")
    public Post getPost(@PathVariable("id") Long id){
        Optional<Post> post = postService.getPost(id);
        if(!post.isPresent()){
            throw new RuntimeException("Post with id : "+id+" not found");
        }
        return post.get();
    }

    @PostMapping(value="")
    public String store(@RequestBody Post post){
        postService.save(post);

        return "Saved...";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody Post post){
        Optional<Post> find_post = postService.getPost(id);
        Post getPost = null;
        if(!find_post.isPresent()){
            throw new RuntimeException("Post with id : "+id+" not found");
        }else{
            getPost = find_post.get();
            getPost.setPostTitle(post.getPostTitle());
            getPost.setPostSlug(post.getPostSlug());
            getPost.setPostContent(post.getPostContent());
            postService.save(getPost);
        }
        return "Post updated";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        postService.delete(id);

        return "Post with id "+id+" deleted";
    }

}
