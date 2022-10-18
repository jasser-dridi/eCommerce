package org.keyrus.models;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import org.bson.types.ObjectId;

import java.util.List;

public class Bundle extends ReactivePanacheMongoEntity {
    ObjectId id;
    String name;
    List<Product> products;

    public Bundle() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
