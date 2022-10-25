package com.keyrus.OrderClient;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("api/orders")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
public interface OrderClient {

    @GET
    @Path("/findByIdUser/{id}")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    Multi<Order> findByIdUser(@PathParam("id") String id);

}