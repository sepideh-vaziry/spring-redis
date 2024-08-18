package com.example.springredis.product;

import java.math.BigDecimal;

public record ProductUpdateRequest(
    BigDecimal price,
    int quantity
) {
}
