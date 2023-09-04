package com.codewithdurgesg.blog.Security;

import com.codewithdurgesg.blog.entities.User;
import com.codewithdurgesg.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesg.blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user=this.userRepo.findUserByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","UserId",0));

        return user;
    }


}
