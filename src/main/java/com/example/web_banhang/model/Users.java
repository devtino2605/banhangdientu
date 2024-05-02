package com.example.web_banhang.model;

import com.example.web_banhang.View.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Public.class)
    private int id;

    @JsonView(Views.Public.class)
    @Column(name = "name")
    private String name;
    @Column(name="email")

    @JsonView(Views.Public.class)
    private String email;
    @Column(name = "gioitinh")
    private String gioitinh;
    @Column(name = "address")
    private String address;


}
