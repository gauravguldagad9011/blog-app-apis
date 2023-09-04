package com.codewithdurgesg.blog.payloads;

import com.codewithdurgesg.blog.entities.Comment;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {

    private int id;

    private String tittle;

    private String content;

    private String image;

    private Date date;

    private CategoryDTO category;

    private UserDTO user;

    private List<CommentDTO> commentDTO;
}
