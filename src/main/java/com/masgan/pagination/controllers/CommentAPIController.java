package com.masgan.pagination.controllers;

import java.util.List;
import java.util.Optional;

import com.github.javafaker.Faker;
import com.masgan.pagination.entities.Comment;
import com.masgan.pagination.entities.Post;
import com.masgan.pagination.services.CommentService;
import com.masgan.pagination.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentAPIController {
    
    private Faker faker = new Faker();

    @Autowired
    CommentService commentService;

    @Autowired
    private PostService postService;

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
    
    /**
     * Find all comments, without (pagination, sorting, searching)
     * 
     * @return List Comments
    */
    @GetMapping("/all")
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    /**
     * Find all comments (default paging, sorting)
     * 
     * @return  List    Comments
    */
    @GetMapping("/")
    public List<Comment> geComments() {
        return findPaginated(1, "dateCreated", "desc");
    }

    @GetMapping("/page/{pageNo}")
    public List<Comment> findPaginated(
        @PathVariable("pageNo") int pageNo,
        @RequestParam(value = "sortField") String sortField,
        @RequestParam(value = "order") String order
    ) {
        int pageSize = 10;
        Page<Comment> page = commentService.findPaginated(pageNo, pageSize, sortField, order);
        List<Comment> commentsList = page.getContent();

        return commentsList;

    }

    /**
     * Get searching comments
     * 
     * @param   int     pageNo
     * @return  List    comments
    */
    @GetMapping(value = "/search/page/{pageNo}")
    public List<Comment> searchComments(
        @PathVariable(value = "pageNo") int pageNo,
        @RequestParam(value = "keyword") String keyword,
        @RequestParam(value = "sortField") String sortField,
        @RequestParam(value = "order") String order
    ) {
        int pageSize = 10;

        if (sortField == null) {
            sortField = "dateCreated";
        }

        if (order == null) {
            order = "desc";
        }

        Page<Comment> page = commentService.findSearchedPaginated(pageNo, pageSize, keyword, sortField, order);
        List<Comment> commentsList = page.getContent();

        return commentsList;

    }

    /**
     * Get Comment
     * 
     * @param   Long    id
     * @return  Object  Comment
    */
    @GetMapping("/view/{id}")
    public Comment getComment(@PathVariable("id") Long id){
        return commentService.getComment(id);
    }

    /**
     * Delete comment
     * 
     * @param   Long    id
     * @return  String
    */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return commentService.delete(id);
    }

    /* Others Method */

    /**
     * Get Comment Post
     * 
     * @param   Long    id
     * @return  Object  Post
    */
    public Post getPost(long id){
        Optional<Post> findPost = postService.getPost(id);
        if (!findPost.isPresent()) {
            throw new RuntimeException("Post with id : "+id+" not found !");
        }
        return findPost.get();
    }

}
