package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserReponsitory extends JpaRepository<ApplicationUser,Integer> {
    Optional<ApplicationUser> findByEmail(String email);

    ApplicationUser findByUsername(String username);
}
