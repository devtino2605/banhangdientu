package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenReponsitory extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findByToken(String token);
    @Query("SELECT tk FROM RefreshToken tk WHERE tk.userInfo.email=:userEmail")
    Optional<RefreshToken> findByUserEmail(String userEmail);
}
