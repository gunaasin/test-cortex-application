package com.clone.amazon.category;

import com.clone.amazon.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(nullable = false)
    private String categoryName;

    private String brand;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties({"category","cartItems","reviews","keywords"})
    private List<Product> products;

}


