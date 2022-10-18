package org.keyrus.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import org.keyrus.models.Bundle;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BundleRepository implements ReactivePanacheMongoRepository<Bundle> {
}
