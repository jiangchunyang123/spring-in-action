package com.learn.springinaction.neo4j;

import com.learn.springinaction.neo4j.domain.Item;
import com.learn.springinaction.neo4j.domain.LineItem;
import com.learn.springinaction.neo4j.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Neo4jConfig.class)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Neo4jOperations neo4jOperations;

    @Test
    public void testSave() {
        Order order = new Order();
        order.setCustomer("handsome man");
        order.setType("tee");
        orderRepository.save(order);
    }

    @Test
    public void testQuery() {
        Order order = new Order();
        order.setCustomer("handsome man");
        order.setType("tee");
        orderRepository.save(order);
        List<Order> list = orderRepository.findByCustomer("handsome man");
        assert list != null && list.size() > 0;
    }

    @Test
    public void testfindSiAOrders() {
        Order order = new Order();
        order.setCustomer("handsome man");
        order.setType("tee");
        orderRepository.save(order);

        Item item = new Item();
        item.setProduct("Spring in Action");
        neo4jOperations.save(item);

        LineItem lineItem = neo4jOperations.createRelationshipBetween(order,
                item, LineItem.class, "HAS_ITEMS", false);
        neo4jOperations.save(lineItem);
        List<Order> list = orderRepository.findSiAOrders();
        assert list != null && list.size() > 0;
    }
}