package com.keyrus.of.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.ToString;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;

//@Builder
@ToString
@MongoEntity(collection = "category")
public class Category extends ReactivePanacheMongoEntity  {
    @NotNull
    public ObjectId id;
    @NotNull
    public String name;

    @NotNull
    public String description;

    public Category(ObjectId id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category() {
    }
}