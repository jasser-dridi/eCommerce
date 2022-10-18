package com.keyrus.ecommerce.category.client;

import com.keyrus.ecommerce.category.model.Product;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Singleton
@Path("/api/products")
@RegisterRestClient
public interface ProductClient {

    @Path("/{productID}")
    public Uni<Product> getOne(@PathParam("productID")ObjectId productId);
}
