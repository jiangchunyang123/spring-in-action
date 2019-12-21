package com.learn.springinaction.neo4j;

import com.learn.springinaction.domain.Item;
import com.learn.springinaction.domain.LineItem;
import com.learn.springinaction.domain.Order;
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
public class OrderRepositoryImplTest {

    @Autowired
    private OrderMixRepository orderMixRepository;


    @Autowired
    private Neo4jOperations neo4jOperations;

    @Test
    public void findSiAOrders() {
        Order order = new Order();
        order.setCustomer("handsome man");
        order.setType("tea");
        orderMixRepository.save(order);

        Item item = new Item();
        item.setProduct("Spring in Action");
        neo4jOperations.save(item);

        LineItem lineItem = neo4jOperations.createRelationshipBetween(order,
                item, LineItem.class, "HAS_ITEMS", false);
        neo4jOperations.save(lineItem);
        List<Order> orderList = orderMixRepository.findSiAOrders();
        assert orderList != null && orderList.size() > 0;
    }
}