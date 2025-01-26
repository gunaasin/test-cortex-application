package com.clone.amazon.cart;

import com.clone.amazon.cartItem.CartItem;
import com.clone.amazon.cartItem.CartItemRepository;
import com.clone.amazon.delivery.DeliveryInfo;
import com.clone.amazon.product.ProductMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    private final ProductMapper productMapper;
    private final DeliveryInfo deliveryInfo;
    private final CartItemRepository cartItemRepository;

    public CartMapper(ProductMapper productMapper ,
                      DeliveryInfo deliveryInfo,
                      CartItemRepository cartItemRepository
    ){
        this.productMapper=productMapper;
        this.deliveryInfo=deliveryInfo;
        this.cartItemRepository=cartItemRepository;
    }

    public CartItemResponseDTO cartToCartResponse(CartItem cartItem){
        return CartItemResponseDTO.builder()
                        .id(cartItem.getId())
                        .productResponseForCartDTO(productMapper.productToCartProductDto(cartItem.getProduct()))
                        .quantity(cartItem.getQuantity())
                        .deliveryOptionId(cartItem.getDeliveryOptionId())
                        .deliveryCharge(cartItem.getDeliveryCharge())
                        .deliveryOptionId(cartItem.getDeliveryOptionId())
                        .build();
    }

    @Transactional
    public void updateRequestToItem(CartUpdateRequestDTO cartUpdateRequestDTO) {
        CartItem existingCartItem = cartItemRepository.findById(cartUpdateRequestDTO.cartItemId())
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        int deliveryCharge = deliveryInfo.getDeliveryCharge(cartUpdateRequestDTO.deliveryOptionId());
        existingCartItem.setDeliveryOptionId(cartUpdateRequestDTO.deliveryOptionId());
        existingCartItem.setDeliveryCharge(deliveryCharge);

    }

    public CartRequestDTO requestToResponse(CartUpdateRequestDTO cartUpdateRequestDTO){
        return CartRequestDTO.builder()
                .token(cartUpdateRequestDTO.token())
                .email(cartUpdateRequestDTO.email())
                .build();
    }
}
