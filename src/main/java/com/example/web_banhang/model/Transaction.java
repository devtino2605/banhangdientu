package com.example.web_banhang.model;

import jakarta.persistence.*;

import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotNull(message = "Transaction date is required")
    @PastOrPresent(message = "Transaction date must be in the past or present")
    @Column(name = "transaction_date")
    private Date transactionDate;


    @NotBlank(message = "Transaction type is required")
    @Size(max = 100, message = "Transaction type cannot exceed 100 characters")
    @Column(name = "transaction_type")
    private String transactionType;

    @Positive(message = "Amount must be a positive number")
    @Column(name = "amount")
    private double amount;

    // Constructors, getters and setters
}