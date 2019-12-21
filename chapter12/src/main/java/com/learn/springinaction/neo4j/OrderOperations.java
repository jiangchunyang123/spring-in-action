package com.learn.springinaction.neo4j;

import com.learn.springinaction.neo4j.domain.Order;

import java.util.List;

public interface OrderOperations {
    List<Order> findSiAOrders();
}
