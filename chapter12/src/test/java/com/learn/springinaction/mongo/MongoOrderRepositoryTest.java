package com.learn.springinaction.mongo;

import com.learn.springinaction.mongo.domain.MongoOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfig.class)
public class MongoOrderRepositoryTest {

    @Autowired
    private MongoOrderRepository mongoOrderRepository;

    @Test
    public void testSave() {
        MongoOrder order = new MongoOrder();
        order.setType("hello");
        order.setCustomer("first");
        mongoOrderRepository.save(order);
        List<MongoOrder> orders = mongoOrderRepository.findByCustomer("first");
        assert orders != null;
        assert orders.size() > 0;
        System.out.println(Arrays.deepToString(orders.toArray()));
    }

    @Test
    public void testFindByChucksOrders() {
        MongoOrder order = new MongoOrder();
        order.setType("70");
        order.setCustomer("chuck Wagon");
        mongoOrderRepository.save(order);
        List<MongoOrder> list = mongoOrderRepository.findChucksOrders("70");
        assert list != null && list.size() > 0;
        System.out.println(Arrays.deepToString(list.toArray()));
    }
}