package com.clone.amazon.product;
import com.clone.amazon.category.CategoryDTO;
import com.clone.amazon.rating.RatingDTO;
import com.clone.amazon.review.ReviewDTO;
import lombok.Builder;


import java.util.List;

@Builder
public record ProductResponseDTO(
        Integer id,
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
