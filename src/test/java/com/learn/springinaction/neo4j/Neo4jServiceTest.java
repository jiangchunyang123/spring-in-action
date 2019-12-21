package com.learn.springinaction.neo4j;

import com.learn.springinaction.domain.LineItem;
import com.learn.springinaction.domain.Order;
import com.learn.springinaction.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Neo4jConfig.class)
public class Neo4jServiceTest {

    @Autowired
    private Neo4jOperations neo4jOperations;

    @Test
    public void testInject() {
        assert neo4jOperations != null;
    }

    @Test
    public void testSave() {
        Order order = new Order();
        order.setCustomer("handsome man");
        order.setType("man");
        neo4jOperations.save(order);
    }

    @Test
    public void testFindOne() {
        Order order = new Order();
        order.setCustomer("handsome man2");
        order.setType("man2");
        order = neo4jOperations.save(order);
        assert order != null;
        order = neo4jOperations.findOne(order.getId(), Order.class);
        assert order != null;
    }

    @Test
    public void testCount() {
        Order order = new Order();
        order.setCustomer("handsome man2");
        order.setType("man2");
        order = neo4jOperations.save(order);
        assert order != null;
        long count = neo4jOperations.count(Order.class);
        assert count > 0;
    }

    @Test
    public void delete() {
        Order order = new Order();
        order.setCustomer("handsome man2");
        order.setType("man2");
        order = neo4jOperations.save(order);
        assert order != null;
        neo4jOperations.delete(order);
        long count = neo4jOperations.count(Order.class);
        assert count == 0;
    }

    @Test
    public void testRelastionsBetween() {
        Order order = new Order();
        order.setCustomer("handsome man");
        order.setType("man");
        order = neo4jOperations.save(order);

        Product product = new Product();
        product.setName("testProduct");
        product = neo4jOperations.save(product);
        LineItem lineItem = neo4jOperations.createRelationshipBetween(order, product,
                LineItem.class, "HAS_LINE_ITEM_FOR", false);
        lineItem.setQuantity(5);
        neo4jOperations.save(lineItem);
    }

}