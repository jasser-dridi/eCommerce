package org.keyrus.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private ObjectId id;
    private ObjectId productId;
    private Long qte;
}
