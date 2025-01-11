package dev.jtarango.web_scrapper.services;

import dev.jtarango.web_scrapper.models.WebPage;

import java.io.IOException;
import java.util.List;

public interface IWebScrapperService {

    public void scrapeAndSave(String url) throws IOException;

    public List<WebPage> findByText(String text);

    public List<String> getAllLinks(String url) throws IOException;
}
