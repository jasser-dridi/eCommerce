package com.keyrus.OrderClient;

import com.keyrus.model.User;
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
    public User user;
    public Product product;


}
