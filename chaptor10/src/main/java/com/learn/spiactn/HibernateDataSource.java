package com.learn.spiactn;


import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

public class HibernateDataSource {

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.learn.spiactn.model");
        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.H2Diablect");
        factoryBean.setHibernateProperties(props);
        return factoryBean;
    }
}
