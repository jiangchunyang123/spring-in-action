package com.learn.spiactn.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@Configuration
@EnableMongoRepositories(basePackages = "com.learn.spiactn")
public class Neo4jConfig extends Neo4jConfiguration {

    public Neo4jConfig(){
        setBasePackage("com.learn.spiactn");
    }

    @Bean
    public GraphDatabaseService graphDatabaseService(){
        return new GraphDatabaseFactory().newEmbeddedDatabase("/tmp/graphdb");
    }
}
