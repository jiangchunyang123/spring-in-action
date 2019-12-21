package com.learn.spiactn.mongo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * 此示例为spring in action 338页，使用继承的方式来实现配置类
 * 此方式比MongoConfig的实现更为简洁，其实他隐式的创建了一个MongoTemplate Bean
 *
 */
@Configuration
@EnableMongoRepositories(basePackages = {"com.learn.spiactn.mongo"})
public class ExtendMongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "OrdersDB";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("106.52.212.50");
    }
}
