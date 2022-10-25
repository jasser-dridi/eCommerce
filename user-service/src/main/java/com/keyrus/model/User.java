package com.keyrus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public ObjectId id;
    public String firstname;
    public String lastprenom;
    public String email;

}