package com.clone.amazon.review;

import lombok.Builder;

@Builder
public record ReviewDTO(
        float ratings,
        String comment,
        String reviewDate
) {
}
