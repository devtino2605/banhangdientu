package com.example.web_banhang;

import com.example.web_banhang.model.ApplicationUser;
import com.example.web_banhang.model.Roles;
import com.example.web_banhang.reponsibility.RoleReponsitory;
import com.example.web_banhang.reponsibility.UserReponsitory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class WebBanhangApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBanhangApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleReponsitory roleReponsitory, UserReponsitory userReponsitory, PasswordEncoder passwordEncoder) {
        return args -> {
            if(roleReponsitory.findByAutherity("ADMIN").isPresent()) return;
            Roles adminRole = roleReponsitory.save(new Roles("ADMIN"));
            roleReponsitory.save(new Roles("USER"));

            Set<Roles> roles = new HashSet<>();
            roles.add(adminRole);

            ApplicationUser admin = new ApplicationUser(1,"Hoàng Văn Hoài","hoai19800@gmail.com","0356138252", passwordEncoder.encode("123123"),roles );
            userReponsitory.save(admin);
        };
    }
}
