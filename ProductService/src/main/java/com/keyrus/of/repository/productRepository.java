package com.keyrus.of.repository;

import com.keyrus.of.model.category;
import com.keyrus.of.model.product;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class productRepository implements ReactivePanacheMongoRepository<product> {


    }



