package com.codewithdurgesg.blog.repositories;

import com.codewithdurgesg.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
