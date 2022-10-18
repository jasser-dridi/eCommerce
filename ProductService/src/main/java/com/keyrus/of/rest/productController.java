package com.keyrus.of.rest;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.keyrus.of.model.category;
import com.keyrus.of.model.product;
import com.keyrus.of.repository.productRepository;
import com.keyrus.of.service.productService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

//@Authenticated
@Path("api/products")
public class productController {

    @Inject
    productRepository productRepository;
    @Inject
    productService productService;

    @GET
    @Path("listProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<product> getListProduct() {
        return productService.all();
    }

    @GET
    @Path("one-product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<product> findOne(@PathParam("id") String id) {

        return productService.findOne(id);
    }

    @POST
    @Path("/add-product")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addProduct(product b) {
       return productService.addProduct(b);
    }

    @DELETE
    @Path("delete-product/{id}")
    public Uni<Response> deleteProduct(@PathParam("id") String id) throws NotFoundException {
        return productService.deleteProduct(id);
    }

    @PUT
    @Path("update-product")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updateProduct(product p) {
   return productService.updateProduct(p);

    }
}