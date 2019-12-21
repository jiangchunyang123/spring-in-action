package com.learn.springinaction.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class OrderOperationsImpl implements OrderOperations {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Order> findOrdersByType(String t) {
        String type = t.equals("NET") ? "WEB" : t;
        Criteria criteria = Criteria.where("type").is(type);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, Order.class);
    }
}
