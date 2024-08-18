package com.example.springredis.redistemplate;

import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1/turns")
public class TurnCacheController {

  private final ListOperations<String, Object> listOperations;

  public TurnCacheController(RedisTemplate<String, Object> redisTemplate) {
    this.listOperations = redisTemplate.opsForList();
  }

  @PostConstruct
  private void setup() {
    IntStream.range(1, 20).forEach(value -> listOperations.leftPush("turn", value));
  }

  @GetMapping
  public ResponseEntity<String> get() {
    Object objectTurn = listOperations.rightPop("turn");
    if (objectTurn == null) {
      return ResponseEntity.ok("The queue is empty.");
    }

    return ResponseEntity.ok(objectTurn.toString());
  }

}
