package com.keyrus.Service;

//import com.keyrus.OrderClient.OrderClient;

import com.keyrus.OrderClient.Order;
import com.keyrus.OrderClient.OrderClient;
import com.keyrus.model.User;
import com.keyrus.repository.UserRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.Duration;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    @RestClient
    OrderClient orderClient;

    public Multi<User> findAll() {
        return userRepository.streamAll();
    }

    public Uni<User> findOne(@PathParam("id") String id) {
        return userRepository.findById(new ObjectId(id)).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }

    public Uni<Response> addUser(User u) {
        return userRepository.persist(u).map(user -> Response.status(Response.Status.CREATED).entity(user).build());
    }


    public Uni<Response> updateUser(User u) {
        return userRepository
                .findById(u.id)
                .call(product -> userRepository.update(u))
                .map(it -> Response.status(Response.Status.OK).entity(it).build())
                .ifNoItem()
                .after(Duration.ofSeconds(7))
                .recoverWithUni(Uni.createFrom().item(Response.noContent().build()));

    }

    public Uni<Response> deleteUser(String id) {


        return userRepository
                .deleteById(new ObjectId(id)).map(it -> Response.status(Response.Status.OK).entity("{\"message\": \" user Deleted successfully\"}").build());
    }

    public Multi<Order> AllOrders(String id) {
        return orderClient.findByIdUser(id);
        // .map(res-> Response.status(Response.Status.OK).build());
        //  Uni.createFrom().item(Response.noContent().build());
        //  .map(res-> Response.status(Response.Status.OK).build());

    }

}