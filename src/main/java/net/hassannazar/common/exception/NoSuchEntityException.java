package net.hassannazar.common.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class NoSuchEntityException extends WebApplicationException {

    public NoSuchEntityException (String message) {
        super(message, Response.Status.NOT_FOUND);
    }

}
