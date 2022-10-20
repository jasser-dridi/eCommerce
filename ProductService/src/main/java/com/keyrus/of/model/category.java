package com.keyrus.of.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.Builder;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;

//@Builder
@MongoEntity(collection = "category")
public class category extends ReactivePanacheMongoEntity {
    @NotNull
    public ObjectId id;
    @NotNull
    public String name;

    @NotNull
    public String description;

    public category(ObjectId id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public category() {
    }
}