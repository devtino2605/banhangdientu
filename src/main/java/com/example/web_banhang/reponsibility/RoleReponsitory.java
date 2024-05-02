package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleReponsitory extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByAutherity(String autherity);
}
