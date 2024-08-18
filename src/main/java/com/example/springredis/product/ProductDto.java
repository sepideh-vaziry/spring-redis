package com.example.springredis.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public record ProductDto(
    Long id,
    String name,
    String price,
    int quantity
) {
}
