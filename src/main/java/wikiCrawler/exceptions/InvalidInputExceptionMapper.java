package wikiCrawler.exceptions;

import wikiCrawler.domain.ErrorDTO;
import wikiCrawler.exceptions.domain.InvalidInputException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException> {
    @Override
    public Response toResponse(InvalidInputException exception) {
        return Response.status(400)
                .entity(new ErrorDTO(exception.getMessage()))
                .type("application/json")
                .build();
    }
}
