package com.learn.springinaction;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatasourcePoolConfig.class)
@ActiveProfiles(profiles = {"dbcppool"})
public class DbcpDatasourcePoolConfigTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void poolDataSource() {
        assert dataSource instanceof BasicDataSource;
    }

}