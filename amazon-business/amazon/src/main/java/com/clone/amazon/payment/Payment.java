package com.clone.amazon.payment;

import com.clone.amazon.orders.Orders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String paymentDate;
    private String paymentMethod;
    private double amount;
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Orders orders;
}
