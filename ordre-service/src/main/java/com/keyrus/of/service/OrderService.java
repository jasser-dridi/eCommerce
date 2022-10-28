package com.keyrus.of.service;

import com.keyrus.of.Client.InventoryClient;
import com.keyrus.of.model.Inventory;
import com.keyrus.of.model.Order;
import com.keyrus.of.model.State;
import com.keyrus.of.repository.OrdreRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class OrderService {
    @Inject
    OrdreRepository OrderRepository;
    @Inject
    @RestClient
    InventoryClient inventoryClient;

    @Inject
    KafkaEvents kafkaEvents;

    Inventory savedInventory;
    private Set<Order> Orders = new HashSet<>();

    public Multi<Order> findAllOrders() {
        return OrderRepository.streamAll();
    }

    public Uni<Response> createOrder(Order o) {

        o.state = State.Pending;
        return OrderRepository.persist(o)
                .map(orderCreated -> Response.status(Response.Status.CREATED).build());
    }

    public Uni<Response> ValidateOrder(Order o) {
        return inventoryClient.quantityProduct(o.getProduct().id)
                .map(inventory -> {
                    savedInventory = inventory;
                    if (inventory.getQte() < 1) {
                        throw new NotAcceptableException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("{\"message\": \"Quantity is null\"}").build());
                    }
                    o.setState(State.Validated);
                    return o;
                })
                .chain(order -> OrderRepository.findById(o.id))
                .onItem().ifNull().failWith(NotFoundException::new)
                .chain(order -> OrderRepository.update(o))
                .map(order -> Response.status(Response.Status.OK).entity(order).build())
                .ifNoItem()
                .after(Duration.ofSeconds(3))
                .recoverWithUni(Uni.createFrom().item(Response.noContent().build()))
                .call(res -> {
                    if (res.getStatus() == 200) {
                        savedInventory.setQte(savedInventory.getQte() - 1);
                        kafkaEvents.sendInventoryUpdater(savedInventory);
                        System.out.println(savedInventory);
                    }
                    return Uni.createFrom().item(res);
                })
                ;

    }

    public Uni<Response> CancelOrder(Order o) {

        o.state = State.Canceled;
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

    public Uni<Long> findCountProductId(ObjectId id) {
        return OrderRepository.count(" state='Validated' where product.id = ?1 ", id);

    }
}