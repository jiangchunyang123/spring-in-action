package com.learn.springinaction;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.naming.NamingException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JndiDatasourceConfig.class)
public class JndiDatasourceConfigTest {

    @Autowired
    private JndiObjectFactoryBean jndiObjectFactoryBean;

    /**
     * 用嵌入式数据库h2创建一个jndi命名空间，提供给jndiObjectFactoryBean连接使用
     */
    @BeforeClass
    public static void init() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/test");
        ds.setUser("root");
        ds.setPassword("root");
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("jdbc/SpittrDS", ds);
        try {
            builder.activate();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dataSource() {
        assertNotNull(jndiObjectFactoryBean);
    }
}