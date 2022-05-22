package com.masgan.pagination.services;

import java.util.List;
import java.util.Optional;

import com.masgan.pagination.entities.Comment;
import com.masgan.pagination.repositories.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    
    @Autowired
    CommentRepository commentRepo;

    public List<Comment> findAll() {
        return commentRepo.findAll(Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "dateCreated"));
    }

    public Page<Comment> findPaginated(int pageNo, int pageSize, String sortField, String order) {
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                    Sort.by(sortField).ascending() : 
                    Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return commentRepo.findAll(pageable);

    }

    public Page<Comment> findSearchedPaginated(int pageNo, int pageSize, String keyword, String sortField, String order) {
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                    Sort.by(sortField).ascending() : 
                    Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return commentRepo.search(keyword, pageable);
    }

    public void save(Comment comment){
        commentRepo.save(comment);
    }

    public Comment getComment(Long id) {
        Optional<Comment> findComment = commentRepo.findById(id);
        if (!findComment.isPresent()) {
            throw new RuntimeException("Comment with id "+id+" not found !");
        }
        return findComment.get();
    }

    public String delete(Long id) {
        Optional<Comment> findComment = commentRepo.findById(id);
        if (!findComment.isPresent()) {
            throw new RuntimeException("Comment with id "+id+" not found !");
        }else{
            commentRepo.deleteById(id);
            return "Deleted...";
        }
    }

}
