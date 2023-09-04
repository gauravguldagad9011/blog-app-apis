package com.codewithdurgesg.blog.controllers;

import com.codewithdurgesg.blog.Security.JwtAuthResponse;
import com.codewithdurgesg.blog.Security.JwtTokenHelper;
import com.codewithdurgesg.blog.payloads.JwtAuthRequest;
import com.codewithdurgesg.blog.payloads.UserDTO;
import com.codewithdurgesg.blog.repositories.UserRepo;
import com.codewithdurgesg.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenTicationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest jwtAuthRequest
            ) throws Exception {
        this.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
        String token=this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response=new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }
    @PostMapping("/post/users")
    public ResponseEntity<UserDTO> registerNewUser(@RequestBody UserDTO userDto){
        UserDTO user=this.userService.registerNewUser(userDto);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
       try {
           this.authenTicationManager.authenticate(authenticationToken);
       }catch(BadCredentialsException e){
           System.out.println("Invalid details!!");
           throw new Exception("Invalid username or password!!!");
       }
       }
}
