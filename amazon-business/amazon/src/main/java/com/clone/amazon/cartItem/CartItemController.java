package com.clone.amazon.cartItem;

import com.clone.amazon.cart.AddToCartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    private final CartItemService cartItemService;
    public CartItemController(CartItemService cartItemService){
        this.cartItemService=cartItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartDTO addToCartDTO){
        try {
            return ResponseEntity.ok().body(cartItemService.addCartToUser(addToCartDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message" ,"wrong credential"));
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeProductFromCart(@RequestBody RemoveProductDTO removeProductDTO){
        try {
            return ResponseEntity.accepted().body(cartItemService.removeProductFromCart(removeProductDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message" ,"wrong credential"));
        }
    }


}
