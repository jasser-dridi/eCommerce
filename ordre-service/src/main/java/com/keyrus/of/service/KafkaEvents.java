package com.keyrus.of.service;


import com.keyrus.of.model.Inventory;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;

public class KafkaEvents {

    @Inject
    @Channel("update_quantity")
    Emitter<Inventory> inventoryUpdater;

    public void sendInventoryUpdater(Inventory inventory) {
        inventoryUpdater.send(inventory);
    }
}
