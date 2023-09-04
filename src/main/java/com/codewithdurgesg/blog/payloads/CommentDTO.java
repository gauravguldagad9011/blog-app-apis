package com.codewithdurgesg.blog.payloads;

import com.codewithdurgesg.blog.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private int id;

    private String comment;

    private PostDto postDto;

}
