package com.codewithdurgesg.blog.controllers;

import com.codewithdurgesg.blog.payloads.CommentDTO;
import com.codewithdurgesg.blog.services.CommentService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comments/postId/{postId}")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,@PathVariable int postId){
        CommentDTO commentDTO1=this.commentService.createComment(postId,commentDTO);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @GetMapping("/comments/all")
    public ResponseEntity <List<CommentDTO>> getAllComment(){
        List<CommentDTO> commentDTOS=this.commentService.getAllComment();
        return new ResponseEntity<>(commentDTOS,HttpStatus.OK);
    }

    @GetMapping("/get/{commentId}")
    public ResponseEntity<CommentDTO> getById(@PathVariable int commentId){
        CommentDTO c1=this.commentService.getById(commentId);
        return new ResponseEntity<>(c1,HttpStatus.OK);
    }
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO,@PathVariable int commentId){
        CommentDTO c1=this.commentService.update(commentDTO,commentId);
        return new ResponseEntity<>(c1,HttpStatus.OK);

    }




}
