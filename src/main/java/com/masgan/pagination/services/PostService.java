package com.masgan.pagination.services;

import java.util.List;
import java.util.Optional;

import com.masgan.pagination.entities.Post;
import com.masgan.pagination.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    
    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts(){
        return postRepository.findAll(Sort.by(Direction.DESC, "dateCreated"));
    }

    /**
     * A Page is a sublist of a list of objects. 
     * It allows gain information about the position of it in the containing entire list.
    */
    public Page<Post> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                    Sort.by(sortField).ascending() :
                    Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return postRepository.findAll(pageable);
    }

    public Page<Post> findPaginatedSearching(
        String keyword,
        int pageNo,
        int pageSize,
        String sortField,
        String sortDirection
    ){
        // equalsIgnoreCase compare specific string to another string without see a case
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                    Sort.by(sortField).ascending() :
                    Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return postRepository.search(keyword, pageable);

    }

    public Optional<Post> getPost(Long id){
        return postRepository.findById(id);
    }

    public void save(Post post){
        postRepository.save(post);
    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }

}
