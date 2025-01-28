package com.clone.amazon.user;

import com.clone.amazon.address.Address;
import com.clone.amazon.cart.Cart;
import com.clone.amazon.orders.Orders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
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

    @Column(length = 10)
    private String role = "user";

    private String password;
    @Column(
            unique = true,
            nullable = false
    )
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "amazonUser")
    private List<Orders> orders;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

}
