package com.keyrus.of.Client;

import com.keyrus.of.model.Inventory;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/inventories")
@RegisterRestClient
@ApplicationScoped
public interface InventoryClient {
    @GET
    @Path("/get-inventory/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Inventory> quantityProduct(@PathParam("productId") ObjectId productId);
}