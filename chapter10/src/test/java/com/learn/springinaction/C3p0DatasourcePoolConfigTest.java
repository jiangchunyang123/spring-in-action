package com.learn.springinaction;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatasourcePoolConfig.class)
@ActiveProfiles(profiles = {"c3p0pool"})
public class C3p0DatasourcePoolConfigTest {

    @Autowired
    private DataSource dataSource;


    @Test
    public void c3p0PoolDataSource() {
        assert dataSource instanceof ComboPooledDataSource;
    }
}