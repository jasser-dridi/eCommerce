package com.keyrus.ecommerce.category.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
@Builder
@Getter
@Setter
@ToString
public class Product {
    private ObjectId id;
    private String name;
    private String description;
    private double price;
    private ObjectId category;

}
