package com.example.web_banhang.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser user;

    private String fullname;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;

    private String note;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    private int status;

    @Column(name = "total_money")
    private int totalMoney;

    // Constructors, getters, setters, etc.
}
