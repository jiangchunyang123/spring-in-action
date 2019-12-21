package com.learn.springinaction.mongo;

import com.learn.springinaction.mongo.domain.MongoOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MongoOrderRepository extends MongoRepository<MongoOrder, String> {
    List<MongoOrder> findByCustomer(String consumer);

    @Query("{'customer':'chuck Wagon','type':?0}")
    List<MongoOrder> findChucksOrders(String c);
}
