package com.clone.amazon.user;

import lombok.Builder;

@Builder
public record AmazonUserResponseDTO(
        String name
) {
}
