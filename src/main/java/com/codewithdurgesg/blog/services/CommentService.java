package com.codewithdurgesg.blog.services;

import com.codewithdurgesg.blog.payloads.CommentDTO;

import java.util.List;

public interface CommentService {
    public CommentDTO createComment(int postId,CommentDTO commentDTO);
    public List<CommentDTO> getAllComment();
    public CommentDTO getById(int commentId);

    public void deleteById(int commentId);

    public CommentDTO update(CommentDTO commentDTO,int commentId);
}
