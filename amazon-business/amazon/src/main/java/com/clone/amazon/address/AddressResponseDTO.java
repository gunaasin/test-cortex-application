package com.clone.amazon.address;

import lombok.Builder;

@Builder
public record AddressResponseDTO(
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
