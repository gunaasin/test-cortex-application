package com.clone.amazon.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(
            "SELECT p FROM Product p WHERE " +
                    "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(p.keywords) LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
    List<Product> searchProducts(String keyword);

    @Query(
            "SELECT p FROM Product p WHERE" +
            " LOWER(p.category.brand) LIKE LOWER(CONCAT('%', :brand, '%')) " +
            "AND LOWER(p.category.categoryName) LIKE LOWER(CONCAT('%', :categoryName, '%'))")
    List<Product> relatedProducts(@Param("categoryName") String categoryName ,@Param("brand") String brand );

}
