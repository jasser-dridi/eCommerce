package com.keyrus.of.service;


import com.keyrus.of.model.Inventory;
import com.keyrus.of.model.Order;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class KafkaEvents {

    @Inject
    @Channel("update_quantity")
    Emitter<Inventory> inventoryUpdater;

    public void sendInventoryUpdater(Inventory inventory) {
        inventoryUpdater.send(inventory);
    }


    public String sendEmails(Order order){
        return "Bonjour mr/mrs "+order.getUser().nom + order.getUser().getPrenom()+" votre commande en cours doit être annulée car le produit est hors stock";
    }

}
