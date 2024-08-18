package com.example.springredis.product;

import java.math.BigDecimal;

public record ProductCreationRequest(
    String name,
    BigDecimal price,
    int quantity
) {
}
