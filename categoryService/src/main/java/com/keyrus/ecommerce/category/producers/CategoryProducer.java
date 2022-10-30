package com.keyrus.ecommerce.category.producers;

import com.keyrus.ecommerce.category.model.Category;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.Record;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class CategoryProducer {

    @Inject
    @Channel("category-outV2")
    Emitter<Message<Category>> inventoryEmitter;
   /* public Uni<Category> generate(Category category) {
        inventoryEmitter.send(Record.of(category.getId(),category));
        return Uni.createFrom().item(category);
    }

    */

    public void sendEvToProduct(Category c)
    {
        inventoryEmitter.send(Message.of(c));
    }
}
