package com.keyrus.of.repository;

import com.keyrus.of.model.Category;
import com.keyrus.of.model.Product;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements ReactivePanacheMongoRepository<Product> {


    public Uni<Long> findByCategoryId(ObjectId id)
    {
       return find("category._id = ?1",id).count();
    }

    public Multi<Product> findProductByCategoryId(ObjectId id)
    {
        return find("category._id = ?1",id).stream();
    }
}



