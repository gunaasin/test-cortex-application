package com.clone.amazon.cartItem;

import com.clone.amazon.cart.AddToCartDTO;
import com.clone.amazon.security.JwtService;
import com.clone.amazon.user.AmazonUserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private final JwtService jwtService;
    private final AmazonUserRepository amazonUserRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public CartItemService(JwtService jwtService,
                           CartItemRepository cartItemRepository,
                           CartItemMapper cartItemMapper,
                           AmazonUserRepository amazonUserRepository
    ) {
        this.jwtService = jwtService;
        this.cartItemRepository = cartItemRepository;
        this.amazonUserRepository = amazonUserRepository;
        this.cartItemMapper = cartItemMapper;
    }

    public boolean addCartToUser(AddToCartDTO addToCartDTO) {
        try {
            if (jwtService.validateToken(addToCartDTO.token(), addToCartDTO.email())) {
                var email = jwtService.extractMailId(addToCartDTO.token());
//                System.out.println(cartRequestDTO.token());
//                System.out.println(cartRequestDTO.email());
//                System.out.println(email);
                var cartId = amazonUserRepository.findByEmail(email).getCart().getId();
                var cartItem = CartItemRequestDTO.builder()
                        .cartId(cartId)
                        .quantity(addToCartDTO.quantity())
                        .productId(addToCartDTO.productId())
                        .build();

                cartItemRepository.save(cartItemMapper.requestToCartItem(cartItem));
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;

    }
}
