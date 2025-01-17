package com.clone.amazon.category;

import lombok.Builder;

@Builder
public record CategoryDTO(
        String categoryName,
        String brand
) {
}
