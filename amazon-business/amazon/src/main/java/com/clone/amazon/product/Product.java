package com.clone.amazon.product;

import com.clone.amazon.cartItem.CartItem;
import com.clone.amazon.category.Category;
import com.clone.amazon.rating.Rating;
import com.clone.amazon.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_sequence", initialValue = 1000, allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String image;

    private int price;

    @Column(nullable = false)
    private String keywords;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    @JsonIgnoreProperties({"products" , "id"})
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @OneToOne(mappedBy = "product" , cascade = CascadeType.PERSIST)
    private Rating rating;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;


    public Rating getRating() {
        return rating != null ? rating : Rating.builder().count(0).stars(0)
                                                .build();  // Default to 0 stars
    }
    public List<Review> getReviews() {
        return reviews != null ? reviews : new ArrayList<>(); // Return an empty list if reviews is null
    }


}







