package com.codewithdurgesg.blog.payloads;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;

import com.codewithdurgesg.blog.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Email(message = "Email id is not valid!!")
    private String email;

    @NotEmpty
    @Size(min = 4, max = 10, message = "Min length must be 4 char and max must be 10")
    private String password;

    @NotEmpty(message = "Message should not be empty")
    private String about;

    private Set<Role> role = new HashSet<>();
}
