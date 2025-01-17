package com.clone.amazon.category;

import com.clone.amazon.product.ProductRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    public Category saveCategoryBeforeProduct(ProductRequestDTO productRequestDTO){
        return Category.builder()
                .categoryName(productRequestDTO.category().categoryName())
                .brand(productRequestDTO.category().brand())
                .build();
    }
}
