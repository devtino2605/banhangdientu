package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpReponsitory extends JpaRepository<Users,Integer> {
}
