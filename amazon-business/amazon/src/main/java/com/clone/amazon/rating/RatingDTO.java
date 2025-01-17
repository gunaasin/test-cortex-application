package com.clone.amazon.rating;

import lombok.Builder;

@Builder
public record RatingDTO(
        float stars,
        int count
) {
}
