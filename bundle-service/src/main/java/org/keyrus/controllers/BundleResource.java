package org.keyrus.controllers;

import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.keyrus.models.Bundle;
import org.keyrus.services.BundleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/bundles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BundleResource {

    @Inject
    BundleService bundleService;

    @GET
    public Uni<Response> getAll() {
        return bundleService.findAll();
    }

    @GET
    @Path("/{bundleID}")
    public Uni<Response> getByID(@PathParam("bundleID") ObjectId id) {
        return bundleService.findById(id);
    }

    @GET
    @Path("get-by-product-id/{productID}")
    public Uni<Response> getByProductID(@PathParam("productID") ObjectId id){
        return bundleService.findAllByProductId(id);
    }

    @POST
    @Path("add-bundle")
    public Uni<Response> addBundle(Bundle bundle){
        return bundleService.save(bundle);
    }

    @PUT
    @Path("update-bundle")
    public Uni<Response> updateBundle(Bundle bundle){
        return bundleService.update(bundle);
    }

    @DELETE
    @Path("delete-bundle/{bundleID}")
    public Uni<Response> deleteBundle(@PathParam("bundleID") ObjectId id){
        return bundleService.delete(id);
    }
}
