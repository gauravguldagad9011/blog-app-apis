package com.codewithdurgesg.blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name="role")
@Data
public class Role {
    @Id
    private int id;

    private String name;


}
