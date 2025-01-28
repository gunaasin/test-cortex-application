package com.clone.amazon.Inforamtion;

import lombok.Builder;

@Builder
public record NavInfoDTO(
        int cartCount,
        String name,
        String address
) {
}
