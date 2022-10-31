package com.keyrus.of.consumer;

import com.keyrus.of.model.Category;
import com.keyrus.of.model.Product;
import com.keyrus.of.service.ProductService;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CategoryConsumer {

    @Inject
    ProductService productService;
    @Incoming("category-inV2")
        public Uni<Product>  consumeTopic(Category message){
        System.out.println("inside a productConsumer");
       return productService.updateTopicProduct(message);
    }
}
