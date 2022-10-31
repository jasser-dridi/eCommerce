package com.keyrus.of.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.Builder;
import lombok.ToString;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;

@Builder
@ToString
@MongoEntity(collection = "product")
public class Product extends ReactivePanacheMongoEntity {
    @NotNull
    public ObjectId id;

    @NotNull
    public String name;
    @NotNull
    public float price;
    @NotNull
    public String description;

    @NotNull
    //   public String category;
    public Category category;

    public Product(ObjectId id, String name, float price, String description, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Product() {
    }
}