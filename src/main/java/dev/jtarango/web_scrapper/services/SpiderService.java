package dev.jtarango.web_scrapper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SpiderService implements ISpiderService{

    @Autowired
    IWebScrapperService webScrapperService;

    @Override
    public void start() throws IOException {


        String initialLink = "https://elpais.com/";
        scrapeLinksAndSave(initialLink);
    }

    public void scrapeLinksAndSave(String url) throws IOException {
        List<String> links = webScrapperService.getAllLinks(url);

        links.stream().parallel().forEach(link -> {
            try {
                webScrapperService.scrapeAndSave(link);
                scrapeLinksAndSave(link);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }


}
