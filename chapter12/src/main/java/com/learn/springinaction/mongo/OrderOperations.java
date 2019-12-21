package com.learn.springinaction.mongo;

import com.learn.springinaction.mongo.domain.MongoOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderOperations extends MongoRepository<MongoOrder, String>,
        MongoOrderRepository {
    List<MongoOrder> findOrdersByType(String t);
}
