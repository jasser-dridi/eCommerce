package org.keyrus.resources;

import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.keyrus.models.Inventory;
import org.keyrus.services.InventoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/inventories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InventoryResource {
    @Inject
    InventoryService inventoryService;

    @GET
    public Uni<Response> findAll() {
        return inventoryService.getAll();
    }

    @GET
    @Path("/{inventoryID}")
    public Uni<Response> findOne(@PathParam("inventoryID") ObjectId id) {
        return inventoryService.getOne(id);
    }

    @POST
    @Path("add-inventory")
    public Uni<Response> addInventory(Inventory inventory) {
        return inventoryService.save(inventory);
    }

    @PUT
    @Path("update-inventory")
    public Uni<Response> updateInventory(Inventory inventory) {
        return inventoryService.update(inventory);
    }

    @DELETE
    @Path("delete-inventory/{inventoryID}")
    public Uni<Response> deleteInventory(@PathParam("inventoryID") ObjectId id) {
        return inventoryService.delete(id);
    }

    @Path("get-inventory/{prodId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Inventory> getInventoryByProductID(@PathParam("prodId") ObjectId id) {
        return inventoryService.getInventoryByProductID(id);
    }


}
