package com.codewithdurgesg.blog.repositories;


import com.codewithdurgesg.blog.entities.Category;
import com.codewithdurgesg.blog.entities.User;
import com.codewithdurgesg.blog.entities.Post;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

//    @Query("Select p from Post p where p.content like :key")
//    List<Post> searchByContent(@Param("key") String content);

}
