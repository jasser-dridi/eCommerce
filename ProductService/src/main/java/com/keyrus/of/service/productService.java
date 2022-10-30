package com.keyrus.of.service;

import com.keyrus.of.Client.BundleClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.Exception;
import java.time.Duration;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import com.keyrus.of.repository.productRepository;
import com.keyrus.of.model.Product;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class productService {
    @Inject
    productRepository productRepository;
    productRepos
    @Inject
    @RestClient
    BundleClient bundleClient;

    public Multi<Product> all() {
        return productRepository.streamAll().onCompletion().ifEmpty().failWith(() -> new Exception("No data found "));
    }

    public Uni<Product> findOne(@PathParam("id") String id) {
        return productRepository.findById(new ObjectId(id)).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }

    public Uni<Response> addProduct(Product p) {
        return productRepository.persist(p).map(book1 -> Response.status(Response.Status.CREATED).entity(book1).build());
    }


    public Uni<Response> updateProduct(Product p) {
        return productRepository
                .findById(p.id)
                .call(Product -> productRepository.update(p))
                .map(it -> Response.status(Response.Status.OK).entity(it).build())
                .ifNoItem()
                .after(Duration.ofSeconds(7))
                .recoverWithUni(Uni.createFrom().item(Response.noContent().build()));

    }

    public Uni<Long> categoryExist(ObjectId categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
    public Uni<Response> deleteProduct(String id) {
        Response response=bundleClient.findByProductID(id);
        if ( response.getStatus() == 302 || response.getStatus()>=400) {
            return Uni.createFrom().item(Response.notModified(response.getEntity().toString()).build());
        }

        Uni<Product> p = productRepository.findById(new ObjectId(id));
        if (p == null) {
            throw new NotFoundException();
        }

        return productRepository.deleteById(new ObjectId(id)).map(it -> Response.status(Response.Status.NOT_FOUND).entity(it).build());
    }
}