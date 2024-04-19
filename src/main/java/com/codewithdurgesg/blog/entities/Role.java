package com.codewithdurgesg.blog.entities;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="role")
@Data
public class Role {
    @Id
    private int id;

    private String name;


}
