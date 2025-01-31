package com.clone.amazon.product;

import com.clone.amazon.category.CategoryDTO;
import com.clone.amazon.rating.RatingDTO;
import com.clone.amazon.review.ReviewDTO;

import java.util.List;

public record ProductRequestDTO(
        String token,
        String email,
        String name ,
        String description,
        String image,
        int price,
        String keywords,
        CategoryDTO category,
        List<ReviewDTO> reviews,
        RatingDTO rating
) {
}
