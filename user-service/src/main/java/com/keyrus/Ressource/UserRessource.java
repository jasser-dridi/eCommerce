package com.keyrus.Ressource;

import com.keyrus.OrderClient.Order;
import com.keyrus.Service.UserService;
import com.keyrus.model.User;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/users")
public class UserRessource {
    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<User> getListUsers() {
        return userService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<User> findOne(@PathParam("id") String id) {

        return userService.findOne(id);
    }

    @POST
    @Path("/add-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addUser(User u) {
        return userService.addUser(u);
    }

    @DELETE
    @Path("delete-user/{id}")
    public Uni<Response> deleteProduct(@PathParam("id") String id) throws NotFoundException {
        return userService.deleteUser(id);
    }

    @PUT
    @Path("update-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updateProduct(User u) {
        return userService.updateUser(u);
    }

    @GET
    @Path("findByIdUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Order> AllOrders(@PathParam("id") String id) {
        return userService.AllOrders(id);
    }

}