package com.clone.amazon.address;

import lombok.Builder;

@Builder
public record AddressRequestDTO(
        String token,
        String email,
        String name,
        String mobile,
        String pinCode,
        String address,
        String city,
        String state,
        String type
) {
}
