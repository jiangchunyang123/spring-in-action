package com.learn.spiactn.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:mongodb-applicationContext.xml")
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSave() {
        Order order = new Order();
        order.setType("hello");
        order.setCustomer("first");
        orderRepository.save(order);
        List<Order> orders = orderRepository.findByCustomer("first");
        assert orders != null;
        assert orders.size() > 0;
        System.out.println(Arrays.deepToString(orders.toArray()));
    }

    @Test
    public void testFindByChucksOrders() {
        Order order = new Order();
        order.setType("70");
        order.setCustomer("chuck Wagon");
        orderRepository.save(order);
        List<Order> list = orderRepository.findChucksOrders("70");
        assert list != null && list.size() > 0;
        System.out.println(Arrays.deepToString(list.toArray()));
    }
}