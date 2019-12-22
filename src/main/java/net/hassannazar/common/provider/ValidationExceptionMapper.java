package net.hassannazar.common.provider;

import javax.json.bind.JsonbException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<JsonbException> {

    @Override
    public Response toResponse (JsonbException e) {
        var responseBuilder = Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.TEXT_PLAIN);
        responseBuilder.header("violation", e.getCause().getMessage());

        return responseBuilder.build();
    }
}
