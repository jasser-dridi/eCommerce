package com.keyrus.ecommerce.category.service;

import com.keyrus.ecommerce.category.client.ProductClient;
import com.keyrus.ecommerce.category.model.Category;
import com.keyrus.ecommerce.category.producers.CategoryProducer;
import com.keyrus.ecommerce.category.repository.CategoryRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.Duration;

@ApplicationScoped

public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    @RestClient
    ProductClient productClient;

    @Inject
    CategoryProducer categoryProducer;
    public Multi<Category> getAll() {
        return categoryRepository.streamAll();
    }

    public Uni<Response> getOne(ObjectId id) {
        return categoryRepository.findById(id).onItem().ifNotNull().transform(category -> Response.status(Response.Status.OK).entity(category).build()).ifNoItem().after(Duration.ofSeconds(2)).recoverWithUni(Uni.createFrom().item(Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"USER does not exist\"}").build()));
    }

    public Uni<Response> update(Category category) {
        return categoryRepository.findById(category.getId())
                .chain(category1 ->   categoryRepository.update(category)
                        .chain(category2 -> {
                            categoryProducer.sendEvToProduct(category2);
                            return Uni.createFrom().item(category2);
                        }))

                .map(category1 -> Response.status(Response.Status.OK).entity(category1).build());
    }

    public Uni<Response> add(Category category) {
        return categoryRepository.persist(category).map(category1 -> Response.status(Response.Status.CREATED).entity(category1).build());

    }

    public Uni<Response> delete(ObjectId id) {
        return productClient.getOne(id).chain(aLong -> {
            if (aLong != 0)
                return Uni.createFrom().item(Response.status(Response.Status.NOT_ACCEPTABLE).entity("{\"message\":\"Cannot Deleted a filled categorie\"}").build());
            return categoryRepository.deleteById(id).map(it -> Response.ok("{\"message\":\"Deleted Sucessfully\"}").build());
        });


    }
}
