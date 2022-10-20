package com.keyrus.of.Client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/bundles")
@RegisterRestClient
public interface BundleClient {
    @GET
    @Path("/get-by-product-id/{product_Id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response findByProductID(@PathParam("product_Id") String product_id);
}