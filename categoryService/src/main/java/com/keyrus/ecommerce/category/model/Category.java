package com.keyrus.ecommerce.category.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@Builder

public class Category {

    private ObjectId id;
    private String name;
    private String description;

}
