package com.learn.springinaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDatasourceConfig.class)
@ActiveProfiles(profiles = {"drivermanager"})
public class DriverManagerDatasourceConfigTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        assert dataSource instanceof DriverManagerDataSource;
    }
}
