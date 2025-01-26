package com.clone.amazon.cartItem;

import com.clone.amazon.cart.Cart;
import com.clone.amazon.cart.CartRepository;
import com.clone.amazon.product.Product;
import com.clone.amazon.product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartItemMapper {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public CartItemMapper(ProductRepository productRepository,
                          CartRepository cartRepository){
        this.productRepository=productRepository;
        this.cartRepository=cartRepository;
    }

    public CartItem requestToCartItem(CartItemRequestDTO cartItemRequestDTO){
        // Fetch the Product using the product ID
        Product product = productRepository.findById(cartItemRequestDTO.productId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + cartItemRequestDTO.productId()));

        // Fetch the Cart using the cart ID
        Cart cart = cartRepository.findById(cartItemRequestDTO.cartId())
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + cartItemRequestDTO.cartId()));

        return CartItem.builder()
                .quantity(cartItemRequestDTO.quantity())
                .product(product)
                .cart(cart)
                .build();
    }
}
