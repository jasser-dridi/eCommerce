package com.keyrus.of.Client;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
//import pl.piomin.services.department.model.bundel;
@Singleton
@Path("/api/bundles")
@RegisterRestClient
public interface BundelClient {

    @GET
    @Path("/{product_Id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response findByDepartment(@PathParam("product_id") String product_id);

}