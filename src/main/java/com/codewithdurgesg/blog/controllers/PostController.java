package com.codewithdurgesg.blog.controllers;

import com.codewithdurgesg.blog.payloads.ApiResponse;
import com.codewithdurgesg.blog.payloads.PostDto;
import com.codewithdurgesg.blog.payloads.PostResponse;
import com.codewithdurgesg.blog.services.FileService;
import com.codewithdurgesg.blog.services.PostService;
//import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/apis/posts")
public class
PostController {
    @Autowired
    PostService postService;

    @Autowired
    FileService fileService;

    @Value("{project.image}")
    String path;
    @PostMapping("/user/{userID}/category/{categoryId}")
    ResponseEntity<PostDto> createPost(@RequestBody PostDto postDTO, @PathVariable int userID, @PathVariable int categoryId){
        PostDto post=this.postService.createPost(postDTO,userID,categoryId);

        return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
    }

    @GetMapping("/userId/{userId}")
    ResponseEntity<List<PostDto>> getPostByUser(@RequestBody @PathVariable Integer userId){
        List<PostDto> post=this.postService.getPostByUser(userId);

        return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
    }

    @GetMapping("/categoryId/{categoryId}")
    ResponseEntity<List<PostDto>> getPostByCategory(@RequestBody @PathVariable Integer categoryId){
        List<PostDto> post=this.postService.getPostByCategory(categoryId);

        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @GetMapping("/post/id/{postId}")
    ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto p=this.postService.getPostById(postId);

        return new ResponseEntity<PostDto>(p,HttpStatus.OK);
    }

    @GetMapping("/post")
    ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value="pageNumber", defaultValue = "1", required=false) Integer pageNumber,
            @RequestParam(value="pageSize", defaultValue = "10", required=false)Integer pageSize,
            @RequestParam(value="sortBy",defaultValue="id",required=false)String sortBy,
            @RequestParam(value="sortDir", defaultValue="asc",required=false)String sortDir
    ){
        PostResponse p=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);

        return new ResponseEntity(p,HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id){
        this.postService.deletePost(id);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted sucessfully",true),HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    ResponseEntity<PostDto> updatePost(@RequestBody PostDto u, @PathVariable Integer id){
        PostDto p=this.postService.updatePost(u,id);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    ResponseEntity<PostDto> uploadPostImage(@RequestParam ("image")MultipartFile image,
                                            @PathVariable Integer postId
    )throws IOException {
        String fileName=this.fileService.uploadImage(path,image);
        PostDto postDTO=this.postService.getPostById(postId);
        postDTO.setImage(fileName);
       PostDto updatePost= this.postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(updatePost,HttpStatus.OK);
    }
    @GetMapping(value="/post/image/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response)throws IOException{
        InputStream resource=this.fileService.getResource(path,imageName);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

//    @GetMapping("/posts/search/{keyword}")
//    ResponseEntity<List<PostDTO>> searching(
//            @PathVariable String keyword
//    ){
//        List<PostDTO> postDTOS=this.postService.searchPost(keyword);
//        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
//    }




}
