package com.codewithdurgesg.blog.repositories;

import com.codewithdurgesg.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
