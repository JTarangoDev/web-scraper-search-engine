package dev.jtarango.web_scrapper.services;

import java.io.IOException;
import java.util.List;

public interface ISpiderService {

    public void start() throws IOException;

    public void scrapeLinksAndSave(String url) throws IOException;
}
