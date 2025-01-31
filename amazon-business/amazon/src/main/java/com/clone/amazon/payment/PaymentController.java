package com.clone.amazon.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final StripService stripService;

    PaymentController(StripService stripService){
        this.stripService=stripService;
    }

    @PostMapping("/request")
    public ResponseEntity<?> makePayment(
            @RequestBody  PaymentRequest paymentRequest){
        try{
            return ResponseEntity.ok().body(stripService.checkoutProduct(paymentRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "wrong credential"));
        }

    }
}
