package org.keyrus.services;

import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.keyrus.models.Bundle;
import org.keyrus.repository.BundleRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class BundleService {
    @Inject
    BundleRepository bundleRepository;

    public Uni<Response> findAll() {
        return bundleRepository.streamAll().collect().asList().map(bundles -> Response.ok(bundles).build());
    }

    public Uni<Response> findAllByProductId(ObjectId id) {
        return bundleRepository.count("products._id", id).chain(count ->
                {
                    if (count < 1) {
                        return Uni.createFrom().item(Response.noContent().build());
                    }
                    return Uni.createFrom().item(Response.status(Response.Status.FOUND).entity(count).build());

                }
        );
        /*
        return bundleRepository.stream("products._id", id).collect().asList().map(bundles -> {
            if (bundles.isEmpty()) {
                return Response.noContent().build();
            }
            return Response.status(Response.Status.FOUND).entity(bundles).build();
        });
        */
    }

    public Uni<Response> findById(ObjectId id) {
        return bundleRepository.findById(id).onItem().ifNull().failWith(
                notFound()
        ).map(bundle -> Response.ok(bundle).build());
    }

    public Uni<Response> save(Bundle bundle) {
        if (bundle == null) {
            return nullBundle();
        }
        return bundleRepository.persist(bundle)
                .map(saved_bundle -> Response.status(Response.Status.CREATED)
                        .entity(saved_bundle).build());
    }

    public Uni<Response> update(Bundle bundle) {
        if (bundle == null) {
            return nullBundle();
        }

        return bundleRepository.findById(bundle.getId()).onItem()
                .ifNull().failWith(notFound())
                .chain(found -> bundleRepository.update(bundle))
                .map(updated_bundle -> Response.accepted(updated_bundle).build());
    }

    public Uni<Response> delete(ObjectId id) {
        return bundleRepository.findById(id)
                .onItem().ifNull().failWith(notFound())
                .call(bundle -> bundleRepository.delete(bundle))
                .map(it -> Response.noContent().build());
    }

    private Uni<Response> nullBundle() {
        return Uni.createFrom()
                .item(Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"You must give a valid Bundle\"}")
                        .build());
    }

    private NotFoundException notFound() {
        return new NotFoundException(Response.status(Response.Status.NOT_FOUND)
                .entity("{\"message\":\"Bundle does not exist\"}").build());
    }
}
