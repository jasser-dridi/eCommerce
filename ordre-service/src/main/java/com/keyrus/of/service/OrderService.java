package com.keyrus.of.service;

import com.keyrus.of.model.Order;
import com.keyrus.of.model.State;
import com.keyrus.of.repository.OrdreRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderService {
    private Set<Order> Orders = new HashSet<>();
    @Inject
    OrdreRepository OrderRepository;

    public Multi<Order> findAllOrders()
    {
        return OrderRepository.streamAll();
    }
    public Uni<Response> createOrder(Order o) {

        o.state= State.Pending;
        return OrderRepository.persist(o)
                .map(orderCreated -> Response.status(Response.Status.CREATED).build());
    }

    public Uni<Response> ValidateOrder(Order o) {
        o.state=State.Validated;

            return OrderRepository
                    .findById(o.id)
                    .call(order -> OrderRepository.update(o))
                    .map(order -> Response.status(Response.Status.OK).entity(order).build())
                    .ifNoItem()
                    .after(Duration.ofSeconds(7))
                    .recoverWithUni(Uni.createFrom().item(Response.noContent().build()));

        }
    public Uni<Response> CancelOrder(Order o) {

        o.state=State.Canceled;
        return OrderRepository

                .findById(o.id)

                .call(order -> OrderRepository.update(o))
                //.chain(o->Response.status(Response.Status.OK).entity(o).build())
                .map(it -> Response.status(Response.Status.OK).entity(it).build())
                .ifNoItem()
                .after(Duration.ofSeconds(7))
                .recoverWithUni(Uni.createFrom().item(Response.noContent().build()));
// map pour retourner le res modifié avec rep OK
    }

   public Multi<Order> findByIdUser(String id) {
       return OrderRepository
               .streamAll()
               .filter(a -> a.getUser().getId().equals(new ObjectId(id)));

   }
/*
    public Multi<Order> findByIdUser(String id) {
        return Orders.stream().filter(a -> a.getUser().equals(id)).collect(Collectors.toSet());
    }
*/

}