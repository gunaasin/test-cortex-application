package com.clone.amazon.cart;

import com.clone.amazon.cartItem.CartItem;
import com.clone.amazon.user.AmazonUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "cart")
    private AmazonUser amazonUser;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;


}