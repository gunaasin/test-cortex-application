package com.clone.amazon.payment;

import com.clone.amazon.orders.Orders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;       // Primary Key
    private String paymentDate;
    private String paymentMethod; // e.g., Credit Card, PayPal, Cash on Delivery
    private double amount;
    private String status;       // e.g., Paid, Failed, Pending

    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders orders;
}
