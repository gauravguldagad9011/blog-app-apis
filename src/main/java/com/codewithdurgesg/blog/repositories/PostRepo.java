package com.codewithdurgesg.blog.repositories;


import com.codewithdurgesg.blog.entities.Category;
import com.codewithdurgesg.blog.entities.Post;
import com.codewithdurgesg.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PostRepo")
public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

//    @Query("Select p from Post p where p.content like :key")
//    List<Post> searchByContent(@Param("key") String content);

}
