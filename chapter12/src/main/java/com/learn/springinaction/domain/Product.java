package com.learn.springinaction.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.io.Serializable;

@NodeEntity
public class Product implements Serializable {

    @GraphId
    private Long id;

    private String name;

    private String sku;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Product) {
            Product product = (Product) o;
            return product.id.equals(this.id) &&
                    (product.name == null ? "" : product.name).equals(name) &&
                    (product.sku == null ? "" : product.sku).equals(this.sku);
        }
        return false;
    }
}
