package com.example.web_banhang.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Data
@Entity
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String thumbnal;

    private String description;

    // Các constructors, getters, setters và các phương thức khác

    // Liên kết với bảng sản phẩm thông qua khóa ngoại
    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products;
}
