package net.hassannazar.fruit.boundary;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import net.hassannazar.fruit.controller.FruitController;
import net.hassannazar.fruit.model.read.FruitRO;
import org.eclipse.microprofile.metrics.annotation.Timed;

/**
 * @author hassannazar.net
 */
@Timed
@Path("fruits")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FruitResource {

    @Context
    UriInfo uriInfo;

    @Inject
    FruitController controller;

    @GET
    @Path("{id}")
    public Response fruit(@PathParam("id") long id) {
        final var fruit = controller.getFruit(id);
        return Response.ok(fruit).build();
    }

    @GET
    public Response allFruits() {
        // Get all fruits
        final var fruits = controller.getAllFruits();

        if(fruits.isEmpty())
            return Response.noContent().build();

        return Response.ok(fruits).build();
    }

    @POST
    public Response postFruit(@Valid FruitRO fruit) {
        final var id = controller.createFruit(fruit);

        // Create response URI
        var uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Long.toString(id));

        return Response.created(uriBuilder.build())
                .build();
    }

    @PUT
    public Response putFruit(@Valid FruitRO fruit) {
        throw new WebApplicationException("Not yet implemented");
    }

}
