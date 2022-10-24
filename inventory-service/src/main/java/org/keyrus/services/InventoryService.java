package org.keyrus.services;

import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.keyrus.models.Inventory;
import org.keyrus.repositories.InventoryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class InventoryService {
    @Inject
    InventoryRepository inventoryRepository;

    public Uni<Response> getAll() {
        return inventoryRepository.streamAll()
                .collect().asList().map(items -> Response.ok(items).build());
    }

    public Uni<Response> getOne(ObjectId id) {
        return inventoryRepository.findById(id)
                .onItem().ifNull().failWith(notFound())
                .map(item -> Response.ok(item).build());
    }

    public Uni<Response> save(Inventory inventory) {
        if (inventory == null) {
            return nullInventory();
        }
        return inventoryRepository.persist(inventory).map(item -> Response.ok(item).build());
    }

    public Uni<Response> update(Inventory inventory) {
        if (inventory == null) {
            return nullInventory();
        }
        return inventoryRepository.findById(inventory.getId())
                .onItem().ifNull().failWith(notFound())
                .chain(found -> inventoryRepository.update(inventory))
                .map(updated_inventory -> Response.accepted(updated_inventory).build());
    }

    public Uni<Response> delete(ObjectId id) {
        return inventoryRepository.findById(id)
                .onItem().ifNull().failWith(notFound())
                .chain(found -> inventoryRepository.deleteById(id))
                .map(deleted -> Response.noContent().build());
    }

    private Uni<Response> nullInventory() {
        return Uni.createFrom()
                .item(Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"You must give a valid Inventory\"}")
                        .build());
    }

    private NotFoundException notFound() {
        return new NotFoundException(Response.status(Response.Status.NOT_FOUND)
                .entity("{\"message\":\"Inventory does not exist\"}").build());
    }
}
