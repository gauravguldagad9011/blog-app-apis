package com.codewithdurgesg.blog.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.codewithdurgesg.blog.entities.*;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findUserByEmail(String username);
}
