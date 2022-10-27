package com.keyrus.ecommerce.category.ressource;

import com.keyrus.ecommerce.category.model.Category;
import com.keyrus.ecommerce.category.model.Product;
import com.keyrus.ecommerce.category.producers.CategoryProducer;
import com.keyrus.ecommerce.category.repository.CategoryRepository;
import com.keyrus.ecommerce.category.service.CategoryService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.Record;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryRessource {

    @Inject
    CategoryService categoryService;

    @Inject
    CategoryProducer categoryProducer;
    @GET
    @Path("/")
    public Multi<Record<ObjectId, Category>> getAll() {
      return   categoryProducer.category();

    }

    @GET
    @Path("/{categoryId}")
    public Uni<Response> getOne(@PathParam("categoryId") ObjectId id) {
        return categoryService.getOne(id);
    }

    @POST
    @Path("/add-category")
    public Uni<Response> addCategory(Category category) {
        return categoryService.add(category);
    }

    @PUT
    @Path("/update-category")
    public Uni<Response> updateCategory(Category category) {
        return categoryService.update(category);
    }

    @DELETE
    @Path("/delete-category/{categoryId}")
    public Uni<Response> deleteCategory(@PathParam("categoryId") ObjectId id) {
        return categoryService.delete(id);
    }
}
