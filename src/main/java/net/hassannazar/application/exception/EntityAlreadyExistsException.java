package net.hassannazar.application.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.URI;

public class EntityAlreadyExistsException extends WebApplicationException {

    public EntityAlreadyExistsException (String uriInfo, long id) {
        super(
                Response.status(Response.Status.BAD_REQUEST)
                        .location(URI.create(uriInfo + "/" + id))
                        .header("message", "Object with id: " + id + " already exists.")
                        .build()
        );
    }

}
