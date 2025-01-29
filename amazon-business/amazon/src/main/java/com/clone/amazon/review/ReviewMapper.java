package com.clone.amazon.review;

import com.clone.amazon.product.Product;
import com.clone.amazon.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMapper {

    private final ProductRepository productRepository;

    Review requestToReview(ReviewRequestDTO reviewRequestDTO) throws Exception {
        Product product = productRepository.findById(reviewRequestDTO.id()).orElseThrow(() -> new Exception("what ever you want that is the exception"));
        return Review.builder()
                .product(product)
                .name(reviewRequestDTO.name())
                .rating(reviewRequestDTO.rating())
                .title(reviewRequestDTO.title())
                .content(reviewRequestDTO.content())
                .reviewDate(reviewRequestDTO.date())
                .helpful(reviewRequestDTO.helpful())
                .verified(reviewRequestDTO.verified())
                .build();
    }
}
