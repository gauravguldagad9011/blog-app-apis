package com.codewithdurgesg.blog.payloads;

import lombok.*;

import java.util.Date;
import java.util.List;

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
