package com.codewithdurgesg.blog.entities;

//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotEmpty;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="category")
public class Category {

    @Id
    @Column(name="category_Id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String tittle;

    private String description;

    @OneToMany(mappedBy="category", cascade=CascadeType.ALL)
    private List<Post> posts;
}
