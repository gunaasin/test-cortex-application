package com.clone.amazon.payment;

import com.clone.amazon.orders.Orders;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PaymentMapper {

   public Payment requestToPayment(PaymentRequest paymentRequest){
       LocalDateTime now = LocalDateTime.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String formattedDateTime = now.format(formatter);
        return Payment.builder()
                .paymentDate(formattedDateTime)
                .amount(paymentRequest.amount())
                .paymentMethod("stripe")
                .status("order placed")
                .orders(new Orders())
                .build();
    }
}
