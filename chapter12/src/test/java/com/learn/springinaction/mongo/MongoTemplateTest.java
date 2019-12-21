package com.learn.springinaction.mongo;

import com.learn.springinaction.mongo.domain.MongoOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:mongodb-applicationContext.xml")
public class MongoTemplateTest {

    @Autowired
    private MongoOperations mongoTemplate;

    @Test
    public void tesSaveOrder() {
        long orderCount = mongoTemplate.getCollection("order").count();
        System.out.println(orderCount);
        MongoOrder order = new MongoOrder();
        order.setType("123");
        mongoTemplate.save(order, "order");
        orderCount = mongoTemplate.getCollection("order").count();
        System.out.println(orderCount);

        assert orderCount > 0;
        mongoTemplate.remove(order);
        orderCount = mongoTemplate.getCollection("order").count();
        System.out.println(orderCount);

    }

}
