package wikiCrawler.services;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import wikiCrawler.domain.CrawlDTO;
import wikiCrawler.exceptions.domain.InvalidInputException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Component
public class CrawlerService {

    private static final String WIKIPEDIA_BASE_URL = "https://en.wikipedia.org/wiki/";
    private static final int MAX_CRAWLS=100;

    Logger logger = LoggerFactory.getLogger(CrawlerService.class);

    public CrawlDTO getCrawlResult(String input, String target){
        CrawlDTO result=null;

        if(isValidUrl(input) && isValidUrl(target)){
            try {
                List<String> hops = new ArrayList<>();
                result= new CrawlDTO(findTargetWiki(input, target, 0, hops), hops);
            } catch (IOException e) {
                logger.error(e.toString());
                e.printStackTrace();
            }

        }else{
            throw new InvalidInputException("input or target invalid");
        }
        return result;
    }

    private String findTargetWiki(String article, String target, Integer linkCounter, List<String> hops) throws IOException {

        hops.add(article);
        // base-case: resets counter and breaks loop when target is reached
        if (article.equalsIgnoreCase(target)) {
            int total = linkCounter;
            return "Reached Philosophy entry in " + total + " links.";

           //max number of links to crawl through
        } else if (linkCounter > MAX_CRAWLS) {
            logger.info("Too far away...giving up after "+MAX_CRAWLS+" crawls");
            hops = new ArrayList<>();
            return "Too far away...giving up";

        } else {
            String nextLink = grabNextLink(article);
            linkCounter++;
            return findTargetWiki(nextLink, target, linkCounter, hops);
        }
    }

    private void printTitle(String article) {
        // formats titles for printing to the console
        System.out.println("         ▼\n      " + article.substring(30).replace("_", " "));
    }

    private String grabNextLink(String article) throws IOException {
        String nextAnchore = "";

        URL url = new URL(article);
        Document doc = Jsoup.parse(url, 100000);        // parses wiki page, times out after 100 seconds

        Element content = doc.getElementById("mw-content-text");

        Elements paras = content.select("p");

        for (Element para : paras){
            if (isaValidPara(para)){
                nextAnchore = getFirstAnchore(para);
                if(StringUtils.isNotBlank(nextAnchore)){
                    break;
                }
            }
        }

        return "https://en.wikipedia.org" + nextAnchore.substring(9, nextAnchore.indexOf("\"", 10));
    }

    private String getFirstAnchore(Element para) {

            String parag = para.toString();
            String firstAnchore="";

            int opens = 0;
            for (int i=0; i<parag.length(); i++){
                if(parag.charAt(i)== '('){
                    opens++;
                }else if (parag.charAt(i)== ')'){
                    opens--;
                }else if(opens==0 && parag.charAt(i)=='<'&&i+1<parag.length()&& parag.charAt(i+1)=='a'){
                    firstAnchore = parag.substring(i, parag.indexOf(">", i+1)+1);
                    if(isAWikiLink(firstAnchore)){
                        break;
                    }
                }
            }
            return firstAnchore;
    }

    private boolean isaValidPara(Element para) {
        return !para.toString().startsWith("<p><s");
    }

    private boolean isAWikiLink(String link) {
        return (link.contains("wiki") && !link.contains("wiktionary"));
    }

    private boolean isValidUrl(String input) {
        return input.startsWith(WIKIPEDIA_BASE_URL);
    }
}
