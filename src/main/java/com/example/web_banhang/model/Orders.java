package com.example.web_banhang.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
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

    @NotBlank(message = "Full name is required")
    @Size(max = 255, message = "Full name must be less than or equal to 255 characters")
    private String fullname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must be less than or equal to 255 characters")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10,13}", message = "Invalid phone number format")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 500, message = "Address must be less than or equal to 500 characters")
    private String address;

    private int num;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    private int status;

    @Column(name = "total_money")
    private double totalMoney;

    // Constructors, getters, setters, etc.
}
