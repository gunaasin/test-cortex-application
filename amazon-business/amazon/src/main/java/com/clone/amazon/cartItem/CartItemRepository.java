package com.clone.amazon.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {



    List<CartItem> findByCartId(Integer cartId);

    @Query("SELECT c FROM CartItem c WHERE c.cart.id = :cartId AND c.product.id = :productId")
    CartItem findByCartIdAndProductId(@Param("cartId") int cartId, @Param("productId") int productId);


    @Query("SELECT COUNT(c) FROM CartItem c WHERE c.cart.id = :cartId")
    int countByCartId(Integer cartId);
}
