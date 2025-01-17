package com.clone.amazon.orderdetail;

import com.clone.amazon.orders.Orders;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue
    private Integer id;   // Primary Key
    private int quantity;
    private double priceAtPurchase;

    @OneToOne(mappedBy = "orderDetail")
    private Orders orders;

//  private int productId;       // Foreign Key to Products
}
