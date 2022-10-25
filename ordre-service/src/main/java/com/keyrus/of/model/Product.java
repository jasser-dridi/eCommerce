package com.keyrus.of.model;

import org.bson.types.ObjectId;

public class Product {

    public ObjectId id;
    public float price;

    public Product(ObjectId id, float price) {
        this.id = id;
        this.price = price;
    }

    public Product(){}


}