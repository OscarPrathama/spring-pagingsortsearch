package com.masgan.pagination.controllers;

import java.util.List;
import java.util.Optional;

import com.github.slugify.Slugify;
import com.masgan.pagination.entities.Post;
import com.masgan.pagination.entities.User;
import com.masgan.pagination.services.PostService;
import com.masgan.pagination.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    Slugify slug = new Slugify();

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping(value="")
    public List<Post> getPosts(){
        // return postService.getPosts();
        return findPaginated(1, "postTitle", "asc");
    }

    @GetMapping(value="/view/{id}")
    public Post getPost(@PathVariable("id") Long id){
        Optional<Post> post = postService.getPost(id);
        if(!post.isPresent()){
            throw new RuntimeException("Post with id : "+id+" not found");
        }
        return post.get();
    }

    @GetMapping(value = "/view/{id}/user")
    public User getPostUser(@PathVariable("id") Long id){
        Optional<Post> post = postService.getPost(id);
        if(!post.isPresent()){
            throw new RuntimeException("Post with id : " + id + " not found");
        }
        return post.get().getUser();
    }

    @GetMapping("/page/{pageNo}")
    public List<Post> findPaginated(
        // define uri variable like id, or pageNo
        @PathVariable(value = "pageNo") int pageNo,

        // define request parameter like ?sortField=postTitle&sortDir=asc
        @RequestParam(value = "sortField") String sortField,
        @RequestParam(value = "sortDir") String sortDirection
    ){
        int pageSize = 5;
        Page<Post> page = postService.findPaginated(pageNo, pageSize, sortField, sortDirection);
        List<Post> listPost = page.getContent();
        int totalPages = page.getTotalPages();
        long totalTotalElements = page.getTotalElements();
        int numberOfElements = page.getNumberOfElements();
        int size = page.getSize();

        System.out.println("page : " + pageNo );
        System.out.println("sortField : " + sortField );
        System.out.println("sortDirection : " + sortDirection );
        System.out.println("totalPages : " + totalPages );
        System.out.println("totalTotalElements : " + totalTotalElements );
        System.out.println("numberOfElements : " + numberOfElements );
        System.out.println("size : " + size );

        return listPost;

    }

    @GetMapping("/search/page/{pageNo}")
    public List<Post> searchPost(
        @PathVariable(value = "pageNo") int pageNo,
        @RequestParam(value = "keyword") String keyword,
        @RequestParam(value = "sortField") String sortField,
        @RequestParam(value = "sortDir") String sortDir
    ){
        int pageSize = 5;

        if(sortField == null){
			sortField = "firstName";
		}
		if(sortDir == null){
			sortDir = "asc";
		}

        Page<Post> page = postService.findPaginatedSearching(keyword, pageNo, pageSize, sortField, sortDir);
        List<Post> listPosts = page.getContent();

        return listPosts;

    }

    @PostMapping(value="")
    public String store(@RequestBody Post post){
        String setPostSlug = slug.slugify(post.getPostTitle());
        post.setPostSlug( setPostSlug );
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
