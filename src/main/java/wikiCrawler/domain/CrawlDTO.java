package wikiCrawler.domain;

import java.util.List;

public class CrawlDTO {
    private String message;
    private List<String> links;

    public CrawlDTO(String message) {
        this.message = message;
    }

    public CrawlDTO(String message, List<String> links) {
        this.message = message;
        this.links = links;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
