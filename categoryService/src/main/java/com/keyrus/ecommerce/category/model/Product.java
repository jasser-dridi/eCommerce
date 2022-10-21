package com.keyrus.ecommerce.category.model;

import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {
    private ObjectId id;
    private String name;
    private String description;
    private double price;
    private ObjectId category;


}
