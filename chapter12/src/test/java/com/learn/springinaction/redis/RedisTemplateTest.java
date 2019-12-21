package com.learn.springinaction.redis;

import com.learn.springinaction.neo4j.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSetAndGet() {
        redisTemplate.opsForValue().set("hello", "wiki");
        assert "wiki".equals(redisTemplate.opsForValue().get("hello"));
    }

    @Test
    public void testOperforListPush() {
        redisTemplate.opsForList().leftPush("list", "left");
        assert "left".equals(redisTemplate.opsForList().leftPop("list"));

        redisTemplate.opsForList().leftPush("list", "right");
        assert "right".equals(redisTemplate.opsForList().leftPop("list"));
    }

    @Test
    public void testOpForSet() {
        Product product = new Product();
        product.setName("car");
        product.setSku("1000");
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        setOperations.add("cars", product);
        Product repeatProduct = new Product();
        repeatProduct.setName("car");
        repeatProduct.setSku("1000");
        setOperations.add("cars", repeatProduct);
        long size = setOperations.size("cars");
        //set存储重复的成员会覆盖
        assert size == 1;
        setOperations.remove("cars", product);
        assert setOperations.size("cars") == 0;

    }

    @Test
    public void testBoundKey() {
        BoundValueOperations<String, Object> boundKeyOperations = redisTemplate.boundValueOps("boundsKey");
        boundKeyOperations.set("123456");
        assert "123456".equals(boundKeyOperations.get());
    }
}
