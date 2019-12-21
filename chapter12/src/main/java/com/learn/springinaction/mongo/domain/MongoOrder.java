package com.learn.springinaction.mongo.domain;

import com.learn.springinaction.neo4j.domain.Item;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedHashSet;
import java.util.Set;

@Document(collection = "mongoOrder")
public class MongoOrder {

    @Id
    private String id;

    @Field("customer")
    private String customer;

    private String type;

    private Set<Item> items = new LinkedHashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
