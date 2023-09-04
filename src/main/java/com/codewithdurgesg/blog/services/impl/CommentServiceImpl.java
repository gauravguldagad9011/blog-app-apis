package com.codewithdurgesg.blog.services.impl;

import com.codewithdurgesg.blog.entities.Comment;
import com.codewithdurgesg.blog.entities.Post;
import com.codewithdurgesg.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesg.blog.payloads.CommentDTO;
import com.codewithdurgesg.blog.repositories.CommentRepo;
import com.codewithdurgesg.blog.repositories.PostRepo;
import com.codewithdurgesg.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDTO createComment(int postId, CommentDTO commentDTO) {
        Post p=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post"," postId ",postId));
        Comment c=this.modelMapper.map(commentDTO,Comment.class);
        c.setPost(p);
        Comment comment=this.commentRepo.save(c);
        CommentDTO c1= this.modelMapper.map(comment,CommentDTO.class);
        return c1;
    }

    @Override
    public List<CommentDTO> getAllComment() {
        List<Comment> c=this.commentRepo.findAll();
        List<CommentDTO> commentDTOS=c.stream().map((e)->this.modelMapper.map(e,CommentDTO.class)).collect(Collectors.toList());
        return commentDTOS;
    }

    @Override
    public CommentDTO getById(int commentId) {
        Comment c=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentID",commentId));
        CommentDTO c1=this.modelMapper.map(c,CommentDTO.class);
        return c1;
    }

    @Override
    public void deleteById(int commentId) {
        Comment c=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentID",commentId));
        this.commentRepo.delete(c);
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO, int commentId) {
        Comment c=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment"," commentId ",commentId));
        c.setComment(commentDTO.getComment());
        Comment updatedComment=this.commentRepo.save(c);

        return this.modelMapper.map(c,CommentDTO.class);
    }
}
