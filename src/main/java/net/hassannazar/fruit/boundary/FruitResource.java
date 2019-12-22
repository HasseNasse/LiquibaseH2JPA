package net.hassannazar.fruit.boundary;

import net.hassannazar.fruit.controller.FruitController;
import net.hassannazar.fruit.model.read.FruitRO;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author hassannazar.net
 */
@Timed
@Path("fruits")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Fruit", description = "Fruit API endpoints")

public class FruitResource {

    @Context
    UriInfo uriInfo;

    @Inject
    FruitController controller;

    @GET
    @Path("{id}")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = FruitRO.class)
                            ),
                            description = "Fruit object"
                    ),
                    @APIResponse(
                            responseCode = "404",
                            content = @Content(mediaType = MediaType.TEXT_PLAIN),
                            description = "No object found"
                    )
            }
    )
    @Operation(
            summary = "Get a specific fruit",
            description = "Returns a specific fruit to the caller who passes in an ID."
    )
    public Response fruit (@PathParam("id") final long id) {
        final var fruit = controller.getFruit(id);
        return Response.ok(fruit).build();
    }

    @GET
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(
                                            implementation = FruitRO.class,
                                            type = SchemaType.ARRAY)
                            ),
                            description = "All Fruit objects"
                    ),
                    @APIResponse(
                            responseCode = "404",
                            description = "No object found"
                    )
            }
    )
    @Operation(
            summary = "Fetch multiple fruits",
            description = "Returns multiple fruits based on an offset and a limit for pagination."
    )
    public Response allFruits() {
        // Get all fruits
        final var fruits = controller.getAllFruits();

        if(fruits.isEmpty())
            return Response.noContent().build();

        return Response.ok(fruits).build();
    }

    @POST
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "201",
                            content = @Content(),
                            description = "Object created"
                    ),
                    @APIResponse(
                            responseCode = "400",
                            description = "Invalid object"
                    )
            }
    )
    @Operation(
            summary = "Create a new fruit",
            description = "Creates a new fruit."
    )
    public Response postFruit (@Valid final FruitRO fruit) {
        final var id = controller.createFruit(fruit);

        // Create response URI
        final var uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Long.toString(id));

        return Response.created(uriBuilder.build())
                .build();
    }

    @PUT
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "201",
                            description = "Object created, if not previously existant"
                    ),
                    @APIResponse(
                            responseCode = "204",
                            description = "Object modified"
                    ),
                    @APIResponse(
                            responseCode = "400",
                            description = "Invalid object"
                    )
            }
    )
    @Operation(
            summary = "Modify a fruit object",
            description = "Modify an existing fruit object."
    )
    public Response putFruit (@Valid final FruitRO fruit) {
        final var id = controller.modifyFruit(fruit);
        // If no modifiable object present, create it!
        if (id == -1L) {
            return postFruit(fruit);
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "202",
                            description = "Delete accepted"
                    ),
                    @APIResponse(
                            responseCode = "404",
                            description = "No such object found"
                    )
            }
    )
    @Operation(
            summary = "Delete a fruit",
            description = "Deletes a fruit from the persistence."
    )
    public Response deleteFruit (@PathParam("id") final long id) {
        controller.deleteFruit(id);
        return Response.accepted().build();
    }

}
