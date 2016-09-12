package wikiCrawler.controllers;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


/**
 * Created by ab on 9/10/16.
 */
@Component
@Path("/hello")
public class Hello {
    @GET
    public String message() {
        return "Hello ";
    }
}
