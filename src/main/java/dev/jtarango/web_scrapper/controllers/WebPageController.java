package dev.jtarango.web_scrapper.controllers;

import dev.jtarango.web_scrapper.models.WebPage;
import dev.jtarango.web_scrapper.services.ISpiderService;
import dev.jtarango.web_scrapper.services.IWebScrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
public class WebPageController {

    @Autowired
    IWebScrapperService webScrapperService;

    @Autowired
    ISpiderService spiderService;

    @GetMapping("/api/search")
    public List<WebPage> search(@RequestParam("query") String query) {
        return webScrapperService.findByText(query);
    }

    @GetMapping("/api/webscrapper")
    public void scrapeAndSave(@RequestParam("url") String url) throws IOException {
        webScrapperService.scrapeAndSave(url);
    }

    @GetMapping("/api/start")
    public void startScraper() throws IOException {
        spiderService.start();
    }
}
