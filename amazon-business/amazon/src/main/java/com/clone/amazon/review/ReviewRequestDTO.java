package com.clone.amazon.review;

public record ReviewRequestDTO(
        String token,
        String email,
        int id,
        String name,
        int rating,
        String title,
        String content,
        String date,
        int helpful,
        boolean verified
) {
}
