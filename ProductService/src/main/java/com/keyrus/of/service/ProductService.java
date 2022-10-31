package com.keyrus.of.service;

import com.keyrus.of.Client.BundleClient;
import static com.mongodb.client.model.Filters.eq;

import com.keyrus.of.model.Category;
import com.keyrus.of.model.Product;
import com.keyrus.of.repository.ProductRepository;
import com.mongodb.client.model.Updates;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.Duration;

@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository productRepository;


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
    public Uni<Product> updateTopicProduct(Category category1) {
        System.out.println(category1.id);
       return  productRepository.findProductByCategoryId(category1.id)
                .call(product ->{
                    System.out.println("product : "+product.toString());
                    return productRepository.
                        mongoCollection()
                        .updateMany(eq("category._id", product.category.id)
                                , Updates.set("category", category1)).map(product1 -> Uni.createFrom().item(product1.getMatchedCount()));}).toUni();

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