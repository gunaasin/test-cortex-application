package com.clone.amazon.Inforamtion;

import com.clone.amazon.cartItem.CartItemRepository;
import com.clone.amazon.cartItem.CartItemService;
import com.clone.amazon.cartItem.cartItemCountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class InformationController {

    private final CartItemService cartItemService;

    public  InformationController(CartItemService cartItemService){
        this.cartItemService=cartItemService;
    }

    @PostMapping("/information")
    public ResponseEntity<?> getNavInfo(@RequestBody cartItemCountDTO cartItemCountDTO){
        try {
            return ResponseEntity.accepted().body(cartItemService.getNavInfo(cartItemCountDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message" ,"wrong credential"));
        }
    }
}
