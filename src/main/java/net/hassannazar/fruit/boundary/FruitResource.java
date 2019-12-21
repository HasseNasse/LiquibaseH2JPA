package net.hassannazar.fruit.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.hassannazar.fruit.controller.FruitController;
import net.hassannazar.fruit.model.Fruit;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author hassannazar.net
 */
@Timed
@Path("fruits")
public class FruitResource {

    @Inject
    FruitController controller;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Fruit fruit() {
        return controller.getAllFruit();
    }

}
