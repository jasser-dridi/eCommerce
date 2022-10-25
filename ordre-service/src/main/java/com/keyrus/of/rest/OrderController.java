package com.keyrus.of.rest;

import com.keyrus.of.model.Order;
import com.keyrus.of.service.OrderService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/orders")

public class OrderController {

    @Inject
    OrderService orderService;

    @Path("/findAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Order> findAllOrders()
    {
        return orderService.findAllOrders();
    }
    @Path("/add-order")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addOrdre( Order o)
    {
        return orderService.createOrder(o);
    }

    @Path("/ValidateOrder")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> ValidateOrder( Order o)
    {
        return orderService.ValidateOrder(o);
    }

    @Path("/CancelOrder")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> CancelOrder( Order o)
    {
        return orderService.CancelOrder(o);
    }

    @Path("/findByIdUser/{id}")
    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Order> findByIdUser(String id)
    {
        return orderService.findByIdUser(id);
    }

}