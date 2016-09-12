package wikiCrawler.domain;

/**
 * Created by ab on 9/11/16.
 */
public class CrawlDTO {
    private String message;

    public CrawlDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
