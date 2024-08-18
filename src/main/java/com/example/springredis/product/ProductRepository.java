package com.example.springredis.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Modifying
    @Query("""
        UPDATE ProductEntity p SET p.quantity = :quantity WHERE p.id = :id
    """)
    void updateProductQuantity(
        @Param("id") Long id,
        @Param("quantity") int quantity
    );

}
