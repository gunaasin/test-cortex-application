package com.clone.amazon.product;

import lombok.Builder;

@Builder
public record ProductResponseForCartDTO(
        int id,
        String name,
        String image,
        int price
) {
}
