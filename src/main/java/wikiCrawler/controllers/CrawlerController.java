package wikiCrawler.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import wikiCrawler.domain.CrawlDTO;
import wikiCrawler.exceptions.domain.InvalidInputException;
import wikiCrawler.services.CrawlerService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by ab on 9/11/16.
 */
@Component
@Path("/crawl")
public class CrawlerController {

    @Inject
    private CrawlerService crawlerService;

    private static final String PHILOSOPHY_URL ="https://en.wikipedia.org/wiki/philosophy";

    @GET
    @Produces("application/json")
    public CrawlDTO getResult(@QueryParam("input") String input,
                              @QueryParam("target") String target) {
        if (StringUtils.isBlank(input)){
            throw new InvalidInputException("Input cannot be blank");
        }
        return crawlerService.getCrawlResult(input,
                StringUtils.isBlank(target)?PHILOSOPHY_URL:target);
    }

    public void setCrawlerService(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }
}
