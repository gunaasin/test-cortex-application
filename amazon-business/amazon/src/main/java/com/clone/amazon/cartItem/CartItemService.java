package com.clone.amazon.cartItem;

import com.clone.amazon.Inforamtion.NavInfoDTO;
import com.clone.amazon.cart.AddToCartDTO;
import com.clone.amazon.security.JwtService;
import com.clone.amazon.user.AmazonUserRepository;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.MediaSize;

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
                var cartId = amazonUserRepository.findByEmail(email).getCart().getId();
                var existingCartItem = cartItemRepository.findByCartIdAndProductId(cartId,addToCartDTO.productId());

                if(existingCartItem != null){
                    existingCartItem.setQuantity(existingCartItem.getQuantity() + addToCartDTO.quantity());
                    cartItemRepository.save(existingCartItem);
                    return true;
                }else{
                    var cartItem = CartItemRequestDTO.builder()
                        .cartId(cartId)
                        .quantity(addToCartDTO.quantity())
                        .productId(addToCartDTO.productId())
                        .build();
                    cartItemRepository.save(cartItemMapper.requestToCartItem(cartItem));
                return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public String removeProductFromCart(RemoveProductDTO removeProductDTO) {
        try {
            if (jwtService.validateToken(removeProductDTO.token(), removeProductDTO.email())) {
                var email = jwtService.extractMailId(removeProductDTO.token());
                var cartId = amazonUserRepository.findByEmail(email).getCart().getId();
                var existingCartItem = cartItemRepository.findByCartIdAndProductId(cartId, removeProductDTO.productId());
                if(existingCartItem !=null){
                    cartItemRepository.deleteById(existingCartItem.getId());
                    return "removed";
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "wrong credential";
    }

    public NavInfoDTO getNavInfo(cartItemCountDTO cartItemCountDTO) {
        try {
            if (jwtService.validateToken(cartItemCountDTO.token(), cartItemCountDTO.email())) {
                var email = jwtService.extractMailId(cartItemCountDTO.token());
                var cartId = amazonUserRepository.findByEmail(email).getCart().getId();
                var name = amazonUserRepository.findByEmail(email).getName();
                var address = amazonUserRepository.findByEmail(email).getAddress();

                if(cartId!=null){
                    return NavInfoDTO.builder()
                            .cartCount(cartItemRepository.countByCartId(cartId))
                            .name(name)
                            .address(address.getStreetAddress())
                            .build();
                }
            }
        } catch (Exception e) {
            System.out.println("exception in cartItemService");
        }
        return NavInfoDTO.builder()
                .address(null)
                .cartCount(0)
                .name("")
                .build();
    }
}
