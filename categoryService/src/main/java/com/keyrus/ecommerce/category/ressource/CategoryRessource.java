package com.keyrus.ecommerce.category.ressource;

import com.keyrus.ecommerce.category.consumers.CategoryConsumer;
import com.keyrus.ecommerce.category.model.Category;
import com.keyrus.ecommerce.category.model.Product;
import com.keyrus.ecommerce.category.producers.CategoryProducer;
import com.keyrus.ecommerce.category.repository.CategoryRepository;
import com.keyrus.ecommerce.category.service.CategoryService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.Record;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.resteasy.reactive.RestStreamElementType;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Path("/api/categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryRessource {

    @Inject
    CategoryService categoryService;

    @Inject
    CategoryProducer categoryProducer;

  /*  @POST
    @Path("/sendtopic")
    public Uni< Category> sendTopic() {
        return   categoryProducer.generate(Category.builder().name("jannyah sdsdsd").build());

    }

   */
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Category> getTopic() {
        return categoryService.getAll();

//        List<Category> categoryList= new ArrayList<>();
//       ConsumerRecords<ObjectId, Category> consumerRecord= consumer.poll(Duration.ofSeconds(10));
//      consumerRecord
//               .records("category")
//               .forEach(objectIdCategoryConsumerRecord -> categoryList.add(objectIdCategoryConsumerRecord.value()) );
//    return  Uni.createFrom().item(categoryList);
    }


/*    @GET
    @Path("/")
    public Multi<Record<ObjectId, Category>> getAll() {

    }*/

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
