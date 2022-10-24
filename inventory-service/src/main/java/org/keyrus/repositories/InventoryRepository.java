package org.keyrus.repositories;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import org.keyrus.models.Inventory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InventoryRepository implements ReactivePanacheMongoRepository<Inventory> {

}
