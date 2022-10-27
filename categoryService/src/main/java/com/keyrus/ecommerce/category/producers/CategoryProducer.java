package com.keyrus.ecommerce.category.producers;

import com.keyrus.ecommerce.category.model.Category;
import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.Record;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CategoryProducer {

    @Outgoing("category")
    public Multi<Record<ObjectId, Category>> category() {
        return Multi.createFrom().items(Category.builder().name("EAU").build()).map(m -> Record.of(m.getId(), m));
    }
}
