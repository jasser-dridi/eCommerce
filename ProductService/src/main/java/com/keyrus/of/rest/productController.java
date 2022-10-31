package com.keyrus.of.rest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.keyrus.of.model.Product;
import com.keyrus.of.service.ProductService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

//@Authenticated
@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
public class productController {


    @Inject
    ProductService productService;


    @GET
    @Path("listProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Product> getListProduct() {
        return productService.all();
    }

    @GET
    @Path("one-product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Product> findOne(@PathParam("id") String id) {

        return productService.findOne(id);
    }

    @POST
    @Path("/add-product")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addProduct(Product b) {
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
    public Uni<Response> updateProduct(Product p) {
        return productService.updateProduct(p);

    }
    @GET
    @Path("/existCategory/{idCategory}")
        public Uni<Long> existCategory(@PathParam("idCategory") ObjectId    id)
    {
        System.out.println(id);
        return productService.categoryExist(id);
    }
}