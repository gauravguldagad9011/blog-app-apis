package com.codewithdurgesg.blog.repositories;

import com.codewithdurgesg.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("RoleRepo")
public interface RoleRepo extends JpaRepository<Role, Integer> {
}
