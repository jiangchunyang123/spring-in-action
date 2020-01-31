package com.learn.springinaction.service;

import com.learn.springinaction.EmbeddedDatasourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedDatasourceConfig.class)
public class SpitterServiceTest {

    @Test
    public void getRecentSpittles() {
    }

    @Test
    public void saveSpittle() {
    }

    @Test
    public void saveSpitter() {
    }
}