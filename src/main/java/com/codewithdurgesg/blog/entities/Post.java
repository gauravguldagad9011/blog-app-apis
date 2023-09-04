package com.codewithdurgesg.blog.entities;

import com.codewithdurgesg.blog.payloads.CommentDTO;
//import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO
    )
    private int id;

    @Column(name="Post_title", length=100)
    private String tittle;

    @Column(length=10000)
    private String content;

    private String Image;

    private Date date;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="Category_Id")
    private Category category;

    @OneToMany(mappedBy="post",cascade=CascadeType.ALL)
    private List<Comment> comments;
}
