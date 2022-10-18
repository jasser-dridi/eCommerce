package com.keyrus.ecommerce.category.ressource;

import com.keyrus.ecommerce.category.model.Category;
import com.keyrus.ecommerce.category.repository.CategoryRepository;
import com.keyrus.ecommerce.category.service.CategoryService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
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


@GET
@Path("/")
    public Multi<Category> getAll()
    {
     return    categoryService.getAll();
    }
    @GET
    @Path("/{categoryId}")
    public Uni<Response> getOne(@PathParam("categoryId") ObjectId id)
    {
        return categoryService.getOne(id);
    }

    @POST
    @Path("/add-category")
    public Uni<Response> addCategory(Category category)
    {
        return categoryService.add(category);
    }

    @PUT
    @Path("/update-category")
    public Uni<Response> updateCategory(Category category)
    {
        return categoryService.update(category);
    }

    @DELETE
    @Path("/delete-category/{categoryId}")
    public Uni<Response> deleteCategory(@PathParam("categoryId") ObjectId id)
    {
        return categoryService.delete(id);
    }
}
