package com.learn.spiactn.mongo;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * EnableMongoRepositories 启动了mongodb类似于jpa的注解
 */
@Configuration
@EnableMongoRepositories(basePackages = {"com.learn.spiactn.mongo"})
public class MongoConfig {
    /**
     * 此bean启动了一个MongoDB实例
     * @return
     */
    @Bean
    public MongoFactoryBean mongo() {
        MongoFactoryBean mongoFactoryBean = new MongoFactoryBean();
        mongoFactoryBean.setHost("106.52.212.50");
        return mongoFactoryBean;
    }

    /**
     * 此bean提供了MongoTemplate，提供了一些访问数据库的功能，
     * 但是即使不直接使用此bean，实际上Repository的底层也使用了template的功能
     * @param mongo
     * @return
     */
    @Bean
    public MongoOperations mongoOperations(Mongo mongo) {
        return new MongoTemplate(mongo, "OrdersDB");
    }
}
