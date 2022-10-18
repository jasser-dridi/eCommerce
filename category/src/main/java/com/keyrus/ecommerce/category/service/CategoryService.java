package com.keyrus.ecommerce.category.service;

import com.keyrus.ecommerce.category.model.Category;
import com.keyrus.ecommerce.category.repository.CategoryRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.Duration;
@ApplicationScoped

public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;


    public Multi<Category> getAll()
    {
       return categoryRepository.streamAll();
    }
    public Uni<Response> getOne(ObjectId id)
    {
      return  categoryRepository.
              findById(id)
              .onItem()
              .ifNotNull()
              .transform(category -> Response.status(Response.Status.OK).entity(category).build())
              .ifNoItem()
              .after(Duration.ofSeconds(2))
              .recoverWithUni(Uni.createFrom().item(Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"USER does not exist\"}").build())) ;
    }
    public Uni<Response> update(Category category)
    {
        return categoryRepository.findById(category.getId())
                .onItem()
                .call(category1 -> categoryRepository.update(category) )
                .map(category1 -> Response.status(Response.Status.OK).entity(category1).build());
    }
    public Uni<Response> add(Category category)
    {
       return categoryRepository.persist(category)
                .map(category1 -> Response.status(Response.Status.CREATED).entity(category1).build());

    }

    public Uni<Response> delete(ObjectId id)
    {
        return categoryRepository.findById(id)
                .call(category -> categoryRepository.delete(category))
                .map(category1 -> Response.status(Response.Status.OK).entity("{\"message\":\"Category IS NOT EXIST\"}").build())
                .onFailure()
                .recoverWithUni(Uni.createFrom().item(Response.status(Response.Status.NOT_FOUND).entity("{\"message\": \"Category IS NOT EXIST\"}").build()));

    }
}
