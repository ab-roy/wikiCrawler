package wikiCrawler.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import wikiCrawler.domain.CrawlDTO;
import wikiCrawler.services.CrawlerService;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CrawlerControllerTest {

    private CrawlerController crawlerController;
    private CrawlerService crawlerService;

    @Before
    public void setUp() throws Exception {
        crawlerController = new CrawlerController();
        crawlerService = mock(CrawlerService.class);
        crawlerController.setCrawlerService(crawlerService);
    }

    @Test
    public void getResult() throws Exception {
        String input ="https://en.wikipedia.org/wiki/Paraguay";
        String target = "https://en.wikipedia.org/wiki/philosophy";

        when(crawlerService.getCrawlResult(input,target)).thenReturn(new CrawlDTO("Some message"));

        CrawlDTO result =crawlerController.getResult(input, target);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getMessage(),"Some message");
    }

    @Test
    public void getResultWithoutTarget() throws Exception {
        String input ="https://en.wikipedia.org/wiki/Paraguay";
        String philosophyUrl = "https://en.wikipedia.org/wiki/philosophy";

        when(crawlerService.getCrawlResult(eq(input),eq(philosophyUrl))).thenReturn(new CrawlDTO("Some message"));

        CrawlDTO result =crawlerController.getResult(input, "");

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getMessage(),"Some message");
    }

}