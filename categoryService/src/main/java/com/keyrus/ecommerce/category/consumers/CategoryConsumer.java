package com.keyrus.ecommerce.category.consumers;

import com.keyrus.ecommerce.category.model.Category;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.Record;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
@ApplicationScoped

public class CategoryConsumer {


@Incoming("category-inV2")

    public Multi<Category> consume(Record<String, Category> record)
{
        String key = record.key(); // Can be `null` if the incoming record has no key
    return Multi.createFrom().item(record.value());

    }


}
