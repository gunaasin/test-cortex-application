package com.clone.amazon.review;

import com.clone.amazon.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Review {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int rating;
    private String title;
    @Column(length = 600)
    private String content;
    private String reviewDate;
    private int helpful;
    private boolean verified;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;



}
