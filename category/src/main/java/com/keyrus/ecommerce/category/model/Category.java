package com.keyrus.ecommerce.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@NoArgsConstructor
@Getter
@Setter
public class Category {

    private ObjectId id;
    private String name;
    private String description;

}
