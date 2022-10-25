package com.keyrus.of.repository;
import com.keyrus.of.model.Order;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrdreRepository implements ReactivePanacheMongoRepository<Order> {

}