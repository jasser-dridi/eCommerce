package org.keyrus.repositories;
import org.keyrus.models.Inventory;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InventoryRepository implements ReactivePanacheMongoRepository<Inventory> {

}