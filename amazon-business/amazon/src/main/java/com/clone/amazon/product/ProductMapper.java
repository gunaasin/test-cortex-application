package com.clone.amazon.product;

import com.clone.amazon.category.CategoryDTO;
import com.clone.amazon.category.CategoryRepository;
import com.clone.amazon.category.CategoryService;
import com.clone.amazon.rating.Rating;
import com.clone.amazon.rating.RatingDTO;
import com.clone.amazon.review.ReviewDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final CategoryService categoryService;

    public ProductMapper(CategoryRepository categoryRepository , CategoryService categoryService){
        this.categoryService=categoryService;
    }

     ProductResponseDTO productResponseDTO(Product product){
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .keywords(product.getKeywords())
                .category(CategoryDTO.builder()
                                .categoryName(product.getCategory().getCategoryName())
                                .brand(product.getCategory().getBrand())
                                .build())
                .reviews(product.getReviews().stream()
                        .map(review -> ReviewDTO.builder()
                                .name(review.getName())
                                .title(review.getTitle())
                                .helpful(review.getHelpful())
                                .verified(review.isVerified())
                                .rating(review.getRating())
                                .content(review.getContent())
                                .date(review.getReviewDate())
                                .build())
                        .toList()
                )
                .rating(RatingDTO.builder()
                        .stars(product.getRating().getStars())
                        .count(product.getRating().getCount())
                        .build())
                .build();
    }


     Product convertRequestToProduct(ProductRequestDTO productRequestDTO){
        
        Product product = Product.builder()
                .name(productRequestDTO.name())
                .description(productRequestDTO.description())
                .image(productRequestDTO.image())
                .price(productRequestDTO.price())
                .keywords(productRequestDTO.keywords())
                .category(categoryService.saveCategoryBeforeProduct(productRequestDTO)) // save the category first then save the product
                .rating(Rating.builder()
                        .stars(productRequestDTO.rating().stars())
                        .build())
                .build();

        // back to store the product
        product.getRating().setProduct(product);
        return product;

    }


    public ProductResponseForCartDTO productToCartProductDto(Product product){
        return ProductResponseForCartDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .build();
    }



}
