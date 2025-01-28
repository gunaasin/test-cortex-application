package com.clone.amazon.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public  CartController(CartService cartService
    ){
        this.cartService = cartService;
    }


    @PostMapping("/product")
    public ResponseEntity<?> getCart(@RequestBody CartRequestDTO cartRequestDTO)
    {
        try {
            return ResponseEntity.ok().body(cartService.getCart(cartRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message" ,"wrong credential"));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCart(@RequestBody CartUpdateRequestDTO cartUpdateRequestDTO){
        try {
            return ResponseEntity.ok().body(cartService.updateCartInfo(cartUpdateRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message" ,"wrong credential"));
        }
    }

}
