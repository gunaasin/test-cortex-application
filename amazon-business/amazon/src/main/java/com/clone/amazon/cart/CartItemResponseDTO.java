package com.clone.amazon.cart;



import com.clone.amazon.product.ProductResponseForCartDTO;
import lombok.Builder;

@Builder
public record CartItemResponseDTO(
       int id,
       ProductResponseForCartDTO productResponseForCartDTO,
       int quantity,
       int deliveryOptionId,
       int deliveryCharge
) {
}
