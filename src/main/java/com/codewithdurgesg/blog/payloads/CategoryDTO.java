package com.codewithdurgesg.blog.payloads;

//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private int id;

    @NotEmpty(message ="Tittle should bot be empty")
    private String tittle;

    @NotEmpty()
    @Size(min = 5,max=25,message="Description should not be empty")
    private String description;

}
