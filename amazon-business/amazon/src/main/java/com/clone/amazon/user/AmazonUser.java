package com.clone.amazon.user;

import com.clone.amazon.cart.Cart;
import com.clone.amazon.orders.Orders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AmazonUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(
            unique = true,
            nullable = false
    )
    private String email;
    private String password;
    @Column(
            unique = true,
            nullable = false
    )
    private String phoneNumber;
    private String address;

    @OneToMany(mappedBy = "amazonUser")
    private List<Orders> orders;

    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;


}
