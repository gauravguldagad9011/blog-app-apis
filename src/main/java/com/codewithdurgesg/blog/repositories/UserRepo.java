package com.codewithdurgesg.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codewithdurgesg.blog.entities.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepo")
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String username);
}
