package com.keyrus.ecommerce.category.repository;

import com.keyrus.ecommerce.category.model.Category;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryRepository implements ReactivePanacheMongoRepository<Category> {
}
