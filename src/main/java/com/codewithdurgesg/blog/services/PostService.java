package com.codewithdurgesg.blog.services;

import com.codewithdurgesg.blog.payloads.PostDto;
import com.codewithdurgesg.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {
     PostDto createPost(PostDto postDTO, int userID, int categoryId);
     PostDto updatePost(PostDto postDTO, Integer id);

     void deletePost(Integer id);

     PostResponse getAllPost(int pageNumber, int pageSize,String sortBy,String sortDir);

     PostDto getPostById(Integer postId);

     List<PostDto> getPostByCategory(int categoryId);

     List <PostDto> getPostByUser(Integer userId);

//     List<PostDTO> searchPost(String keyword);

}
