package com.clone.amazon.cart;

import com.clone.amazon.cartItem.CartItem;
import com.clone.amazon.cartItem.CartItemRepository;
import com.clone.amazon.security.JwtService;

import com.clone.amazon.user.AmazonUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartService {

    private final AmazonUserRepository amazonUserRepository;
    private final JwtService jwtService;
    private final CartMapper cartMapper;
    private final CartItemRepository cartItemRepository;

    public CartService(AmazonUserRepository amazonUserRepository,
                       CartMapper cartMapper,
                       JwtService jwtService,
                       CartItemRepository cartItemRepository){
        this.amazonUserRepository=amazonUserRepository;
        this.jwtService=jwtService;
        this.cartMapper=cartMapper;
        this.cartItemRepository=cartItemRepository;

    }
    public List<CartItemResponseDTO> getCart(CartRequestDTO cartRequestDTO) {
            if(jwtService.validateToken(cartRequestDTO.token(), cartRequestDTO.email())){
                var email = jwtService.extractMailId(cartRequestDTO.token());
//                System.out.println(cartRequestDTO.token());
//                System.out.println(cartRequestDTO.email());
//                System.out.println(email);
                var cartId = amazonUserRepository.findByEmail(email).getCart().getId();
                return cartItemRepository.findByCartId(cartId)
                        .stream()
                        .map(cartMapper::cartToCartResponse)
                        .toList();
            }else {
                return null;
            }
    }

    public List<CartItemResponseDTO> updateCartInfo(CartUpdateRequestDTO cartUpdateRequestDTO){
           List<CartItemResponseDTO> responce = null;
            try {
                cartMapper.updateRequestToItem(cartUpdateRequestDTO);
                responce = getCart(cartMapper.requestToResponse(cartUpdateRequestDTO));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return responce;
    }

    public Object addCartToUser(AddToCartDTO addToCartDTO) {
        try {
            if(jwtService.validateToken(addToCartDTO.token(), addToCartDTO.email())) {
                var email = jwtService.extractMailId(addToCartDTO.token());
//                System.out.println(cartRequestDTO.token());
//                System.out.println(cartRequestDTO.email());
//                System.out.println(email);
                var cartId = amazonUserRepository.findByEmail(email).getCart().getId();
            }else{

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
