package wikiCrawler.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wikiCrawler.domain.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<RuntimeException> {

    Logger logger = LoggerFactory.getLogger(DefaultExceptionMapper.class);

    @Override
    public Response toResponse(RuntimeException ex) {
        logger.error(ex.getStackTrace().toString());
        return Response.status(500)
                .entity(new ErrorDTO(ex.getMessage()))
                .type("application/json")
                .build();
    }
}
