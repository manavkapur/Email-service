package com.supremesolutions.emailservice.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

    private final StringRedisTemplate redisTemplate;

    public RedisTestController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/test-redis")
    public String testRedis() {
        try {
            redisTemplate.opsForValue().set("email:test", "Hello from Email Service!");
            String v = redisTemplate.opsForValue().get("email:test");
            return "✅ Redis Connected (Email Service)! Value: " + v;
        } catch (Exception e) {
            return "❌ Redis error: " + e.getMessage();
        }
    }
}
