package com.codewithdurgesg.blog;

import com.codewithdurgesg.blog.config.AppConstant;
import com.codewithdurgesg.blog.entities.Role;
import com.codewithdurgesg.blog.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Bean
    public ModelMapper modelMapping() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.passwordEncoder.encode("Gaurav@90"));

        try {
            Role role1 = new Role();
            role1.setId(AppConstant.ADMIN_USER);
            role1.setName("ADMIN_USER");

            Role role2 = new Role();
            role2.setId(AppConstant.NORMAL_USER);
            role2.setName("NORMAL_USER");

            List<Role> roles = List.of(role1, role2);
            List<Role> result = this.roleRepo.saveAll(roles);

            result.forEach((r) -> {
                System.out.println(r.getName());
            });


        } catch (Exception e) {

            System.out.println("Already Entered");
            e.printStackTrace();
        }
    }
}
