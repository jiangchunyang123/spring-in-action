package com.learn.springinaction.neo4j;

import com.learn.springinaction.domain.Order;
import org.neo4j.helpers.collection.IteratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.template.Neo4jOperations;

import java.util.List;
import java.util.Map;

public class OrderMixRepositoryImpl implements OrderOperations {

    private final Neo4jOperations neo4jOperations;

    @Autowired
    public OrderMixRepositoryImpl(Neo4jOperations neo4jOperations) {
        this.neo4jOperations = neo4jOperations;
    }

    @Override
    public List<Order> findSiAOrders() {
        Result<Map<String, Object>> result = neo4jOperations.query(
                "match (o:Order)-[:HAS_ITEMS]->(i:Item) " +
                        "where i.product ='Spring in Action' return o", null);
        Result<Order> endResult = result.to(Order.class);
        return IteratorUtil.asList(endResult);
    }
}
