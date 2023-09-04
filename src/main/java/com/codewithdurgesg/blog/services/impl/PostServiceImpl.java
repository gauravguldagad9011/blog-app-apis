package com.codewithdurgesg.blog.services.impl;


import com.codewithdurgesg.blog.entities.Category;
import com.codewithdurgesg.blog.entities.Post;
import com.codewithdurgesg.blog.entities.User;
import com.codewithdurgesg.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesg.blog.payloads.PostDto;
import com.codewithdurgesg.blog.payloads.PostResponse;
import com.codewithdurgesg.blog.repositories.CategoryRepo;
import com.codewithdurgesg.blog.repositories.PostRepo;
import com.codewithdurgesg.blog.repositories.UserRepo;
import com.codewithdurgesg.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDTO, int userID, int categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category"," CategoryID ",categoryId));

        User user=this.userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userID));

        Post post=this.modelMapper.map(postDTO,Post.class);

        post.setTittle(postDTO.getTittle());
        post.setContent(postDTO.getContent());
        post.setImage(postDTO.getImage());
        post.setDate(new Date());

        post.setCategory(category);
        post.setUser(user);

        Post savedPost=this.postRepo.save(post);

        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDTO, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post"," Post ID ",postId));

        post.setTittle(postDTO.getTittle());
        post.setContent(postDTO.getContent());
        post.setImage(postDTO.getImage());
//        postDTO.setUser
        Post updatedPost=this.postRepo.save(post);

        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer id) {
        Post p=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post"," Post id ",id));
        this.postRepo.delete(p);
    }

    @Override
    public PostResponse getAllPost(int pageNumber, int pageSize,String sortBy,String sortDir) {

        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc")){
           sort=Sort.by(sortBy).ascending();
        }else{
           sort=Sort.by(sortBy).descending();
        }

         Pageable p=PageRequest.of(pageNumber,pageSize, sort);
         Page<Post> allPost=this.postRepo.findAll(p);

         List<Post> posts=allPost.getContent();

         List<PostDto> post=posts.stream().map((e)->modelMapper.map(e, PostDto.class)).collect(Collectors.toList());

         PostResponse postResponse=new PostResponse();
         postResponse.setContent(post);
         postResponse.setPageNumber(allPost.getNumber());
         postResponse.setPageSize(allPost.getSize());
         postResponse.setTotalElement(allPost.getTotalElements());
         postResponse.setTotalPages(allPost.getTotalPages());
         postResponse.setLastPage(allPost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(int categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category"," Category ID", categoryId));

        List<Post> posts=this.postRepo.findByCategory(cat);

       List<PostDto> post= posts.stream().map(e->modelMapper.map(e, PostDto.class)).collect(Collectors.toList());

        return post;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User u=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," User Id ",userId));
        List<Post> posts=this.postRepo.findByUser(u);

        List<PostDto> p = posts.stream().map(e->this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
        return p;
    }

//    @Override
//    public List<PostDTO> searchPost(String keyword) {
//        List<Post> posts=this.postRepo.searchByContent("%"+keyword+"%");
//
//        List<PostDTO> postDto=posts.stream().map((e)->modelMapper.map(e,PostDTO.class)).collect(Collectors.toList());
//        return postDto;
//    }
}
