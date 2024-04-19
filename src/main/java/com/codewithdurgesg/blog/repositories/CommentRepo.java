package com.codewithdurgesg.blog.repositories;

import com.codewithdurgesg.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("CommentRepo")
public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
