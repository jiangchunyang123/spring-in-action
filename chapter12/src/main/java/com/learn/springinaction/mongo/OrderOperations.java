package com.learn.springinaction.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderOperations extends MongoRepository<Order, String>,
        OrderRepository {
    List<Order> findOrdersByType(String t);
}
