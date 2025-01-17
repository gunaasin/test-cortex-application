package com.clone.amazon.review;

import com.clone.amazon.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue
    private Integer id;
    private float ratings;
    private String comment;
    private String reviewDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
//    @JsonIgnore
    private Product product;



}
