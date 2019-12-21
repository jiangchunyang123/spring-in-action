package com.learn.spiactn.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByCustomer(String consumer);

    @Query("{'client':'chuck Wagon','type':?0}")
    List<Order> findChucksOrders(String c);
}
