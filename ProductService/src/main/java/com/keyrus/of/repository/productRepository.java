package com.keyrus.of.repository;
import com.keyrus.of.model.product;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class productRepository implements ReactivePanacheMongoRepository<product> {

}


