package com.codewithdurgesg.blog.controllers;

import com.codewithdurgesg.blog.payloads.ApiResponse;
import com.codewithdurgesg.blog.payloads.UserDTO;
import com.codewithdurgesg.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/Post/User")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user) {
        UserDTO createdUser = this.userService.createUser(user);

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    //	update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO user, @PathVariable Integer userId) {
        UserDTO updatedUser = this.userService.updateUser(user, userId);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    @GetMapping("/AllUser")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> l1 = this.userService.getAllUsers();

        return new ResponseEntity<>(l1, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        UserDTO ud1 = this.userService.getUserById(userId);

        return new ResponseEntity<>(ud1, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUserBy(@PathVariable Integer userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted successfuly", true), HttpStatus.OK);
    }

}
