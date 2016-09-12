package wikiCrawler.controllers;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/health")
public class Hello {
    @GET
    public String message() {
        return "Webapplication is up and running";
    }
}
