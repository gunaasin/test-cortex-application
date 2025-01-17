package com.clone.amazon.orders;

import com.clone.amazon.orderdetail.OrderDetail;
import com.clone.amazon.payment.Payment;
import com.clone.amazon.user.AmazonUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;           // Primary Key
    private String orderDate;      // Date or Timestamp
    private String status;         // e.g., Pending, Shipped, Delivered, Canceled
    private double totalAmount;
    private String shippingAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AmazonUser amazonUser;

    @OneToOne
    @JoinColumn(name = "order_detail_id", referencedColumnName ="id")
    private OrderDetail orderDetail;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

}