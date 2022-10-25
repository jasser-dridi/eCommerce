package com.keyrus.of.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    public State state;
    public ObjectId id;
    public  User user;
    public  Product product;


}