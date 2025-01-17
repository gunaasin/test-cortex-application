package com.clone.amazon.rating;

import com.clone.amazon.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Rating {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;
    @Column(nullable = false)
    private float stars = 1.0f;

    @Column(nullable = false)
    private int count = 1;

    @OneToOne
    @JoinColumn(name = "product_id" , nullable = false)
    @JsonIgnore
    private Product product;
}