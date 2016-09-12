package wikiCrawler.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import wikiCrawler.domain.CrawlDTO;

public class CrawlerServiceTest {

    private CrawlerService crawlerService;
    @Before
    public void setUp(){
        crawlerService = new CrawlerService();
    }

    @Test
    public void getCrawlResult() throws Exception {

        String target = "https://en.wikipedia.org/wiki/philosophy";
        String input = "https://en.wikipedia.org/wiki/Paraguay";

        CrawlDTO result = crawlerService.getCrawlResult(input, target);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.getMessage().startsWith("Reached Philosophy"));

    }

}