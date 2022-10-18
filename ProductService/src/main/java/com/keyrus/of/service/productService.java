package com.keyrus.of.service;

import com.keyrus.of.repository.productRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.Exception;
import java.net.URI;
import java.time.Duration;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import com.keyrus.of.model.product;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.Panache.*;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

@ApplicationScoped
public class productService {
    @Inject
    productRepository productRepository;

    public Multi<product> all() {
        return productRepository.streamAll().onCompletion().ifEmpty().failWith(() -> new Exception("No data found "));
    }

    public Uni<product> findOne(@PathParam("id") String id) {
        return productRepository.findById(new ObjectId(id)).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }

    public Uni<Response> addProduct(product p) {
        return productRepository.persist(p).map(book1 -> Response.status(Response.Status.CREATED).entity(book1).build());
    }

    public Uni<Response> deleteProduct(String id) {
        Uni<product> p = productRepository.findById(new ObjectId(id));
        if (p == null) {
            throw new NotFoundException();
        }
        return productRepository.deleteById(new ObjectId(id)).map(it -> Response.status(Response.Status.NOT_FOUND).entity(it).build());
    }

    public Uni<Response> updateProduct(product p) {
      return productRepository
                .findById(p.id)
                .call(product -> productRepository.update(p))
                .map(it->Response.status(Response.Status.OK).entity(it).build())
                .ifNoItem()
                .after(Duration.ofSeconds(7))
                .recoverWithUni(Uni.createFrom().item(Response.noContent().build()));

    }
    }