package com.example.springredis.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductDto>> getAll() {
    List<ProductDto> products = productService.getAll()
        .stream()
        .map(this::mapToDto)
        .toList();

    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> get(
      @PathVariable Long id
  ) {
    ProductEntity product = productService.get(id);

    return ResponseEntity.ok(mapToDto(product));
  }

  @PostMapping
  public ResponseEntity<ProductDto> create(
      @RequestBody ProductCreationRequest request
  ) {
    ProductEntity product = productService.create(request);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(mapToDto(product));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ProductDto> partialUpdate(
      @PathVariable Long id,
      @RequestBody ProductUpdateRequest request
  ) {
    ProductEntity product = productService.partialUpdate(id, request.price(), request.quantity());

    return ResponseEntity.ok(mapToDto(product));
  }

  private ProductDto mapToDto(ProductEntity productEntity) {
    return ProductDto.builder()
        .id(productEntity.getId())
        .name(productEntity.getName())
        .price(toPlainText(productEntity.getPrice()))
        .quantity(productEntity.getQuantity())
        .build();
  }

  private String toPlainText(BigDecimal amount) {
    if (amount == null) {
      return "0";
    }

    return amount.stripTrailingZeros().toPlainString();
  }

}
