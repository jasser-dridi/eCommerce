package com.keyrus.of.model;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.Builder;
import org.bson.types.ObjectId;
import javax.validation.constraints.NotNull;
import com.keyrus.of.model.category;
@Builder

@MongoEntity(collection="product")
public class product extends ReactivePanacheMongoEntity {
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
    public category category;

    public product(ObjectId id, String name, float price, String description, category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public product() {
    }
}