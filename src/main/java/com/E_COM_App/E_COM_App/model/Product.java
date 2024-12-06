package com.E_COM_App.E_COM_App.model;

import com.E_COM_App.E_COM_App.model.Category;
import com.E_COM_App.E_COM_App.model.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy="product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;
}
