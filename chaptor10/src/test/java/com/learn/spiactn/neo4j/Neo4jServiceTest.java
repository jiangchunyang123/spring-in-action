package com.learn.spiactn.neo4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Neo4jConfig.class)
public class Neo4jServiceTest {

    @Autowired
    private Neo4jOperations neo4jOperations;

    @Test
    public void testInject() {
        assert neo4jOperations != null;
    }
}