package com.clone.amazon.user;

public record AmazonUserRequestDTO(
        String name,
        String email,
        String password,
        String phoneNumber,
        String role
) {
}
