package com.learn.springinaction.neo4j;

import com.learn.springinaction.neo4j.domain.Order;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface OrderRepository extends GraphRepository<Order> {
    List<Order> findByCustomer(String customer);

    @Query("match (o:Order)-[:HAS_ITEMS]->(i:Item) where i.product ='Spring in Action' return o")
    List<Order> findSiAOrders();
}
