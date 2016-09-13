# wikiCrawler
Wikipedia Crawler is web crawler that crawls through the first link from a given input wikipedia URL to the Philosophy wikipedia page.
The Crawler has an upper limit of crawling through 100 links before it gives up.
It is a springboot application with a small jquery frontend connecting to a java webapp on the backend via HTTP.
The API can be directly hit too via a HTTP REST client like Postman.
This application has been tested and developed on Linux Kernel 4.4.0-36-generic

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisities

What things you need to run the software

```
JDK 1.8+
Gradle 2.3+
```

### Installation

Install JDK 1.8+ on your system

Install Gradle 2.3+ on your system

Checkout project from gitHub

```
$git clone https://github.com/ab-roy/wikiCrawler.git
```

Run gradle commands to get the application running. Please note application runs on port 8080 by default.

```
$gradle clean build bootRun
```

On a browser go to

```
localhost:8080/wikiCrawler/index.html
```

## Direct API Usage

The HTTP GET endpoint can be directly accessed to crawl to any target wikipedia page.

To use the endpoint directly use a basic HTTP REST client like Postman on google chrome or Advanced Rest Client or directly putting the URL in the browser

Get the application up and running

```
$gradle clean build bootRun
```

Then make a HTTP GET call on

```
http://localhost:8080/wikiCrawler/api/crawl?input=https://en.wikipedia.org/wiki/Paraguay
```

The above will crawl from wikipedia article Paraguay to Philosophy

To add target wikipedia page add the query parameter 
```
target
```

this would look something like 

```
http://localhost:8080/wikiCrawler/api/crawl?input=https://en.wikipedia.org/wiki/Paraguay&target=https://en.wikipedia.org/wiki/State_(polity)
```

## Running the tests

At this time the project only contains some basic unit tests.

To run them

```
$gradle clean test
```

## Built With

* SpringBoot - Embedded application server (Setup to use jetty container)
* Gradle - Dependency management/ build tool
* Jsoup - Used to connect to various wikiLinks and select content div
* Jersey - For the rest endpoints.

## Authors

* **Abhishek Roy** - *Initial work* - [AB_ROY](https://github.com/ab-roy)

## Resources

* http://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
* https://en.wikipedia.org/wiki/Philosophy
