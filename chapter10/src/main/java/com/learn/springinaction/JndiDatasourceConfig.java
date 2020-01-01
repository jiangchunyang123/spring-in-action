package com.learn.springinaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

@Configuration
public class JndiDatasourceConfig {

    @Bean
    public JndiObjectFactoryBean dataSource() {
        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        factoryBean.setJndiName("jdbc/SpittrDS");
        factoryBean.setResourceRef(true);
        factoryBean.setProxyInterface(DataSource.class);
        return factoryBean;
    }

}
