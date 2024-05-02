package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionReponsitory extends JpaRepository<Transaction,Integer> {
}
