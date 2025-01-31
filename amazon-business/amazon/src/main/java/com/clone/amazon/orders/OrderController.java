package com.clone.amazon.orders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {

    @GetMapping("/get/orders")
    public ResponseEntity<?> getOrder(
            @RequestParam String param
    ){
        return ResponseEntity.ok().body(Map.of("message",param));
    }
}
