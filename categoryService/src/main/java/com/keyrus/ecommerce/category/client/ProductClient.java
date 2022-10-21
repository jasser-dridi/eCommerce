package com.keyrus.ecommerce.category.client;

import com.keyrus.ecommerce.category.model.Product;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/api/products")
@RegisterRestClient
@javax.enterprise.context.ApplicationScoped
public interface ProductClient {

    @Path("/existCategory/{idCategory}")
    @javax.ws.rs.GET
   public  Uni<Long> getOne(@PathParam("idCategory") ObjectId productId);
}
