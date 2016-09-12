package wikiCrawler.exceptions.domain;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(final String message) {
        super(message);
    }
}
