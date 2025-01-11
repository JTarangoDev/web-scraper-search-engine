package dev.jtarango.web_scrapper.services;

import dev.jtarango.web_scrapper.models.WebPage;
import dev.jtarango.web_scrapper.repository.WebPageRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebScraperService implements IWebScrapperService{

    @Autowired
    private WebPageRepository webPageRepository;

    @Override
    public void scrapeAndSave(String url) throws IOException {
        Document document = Jsoup.connect(url).get();

        String domain = getDomainFromUrl(url);
        String title = document.title();
        String description = document.select("meta[name=description]").attr("content");
        Element faviconElement = document.selectFirst("link[rel=icon], link[rel=\"shortcut icon\"], link[rel=\"apple-touch-icon\"]");

        String picture;


        if (faviconElement != null){
            picture = faviconElement.attr("abs:href");
        } else {
            picture = url + "/favicon.ico";
        }

        WebPage webPage = new WebPage();

        webPage.setUrl(url);
        webPage.setTitle(title);
        webPage.setDescription(description);
        webPage.setPicture(picture);
        webPage.setDomain(domain);

        webPageRepository.save(webPage);
    }

    @Override
    public List<WebPage> findByText(String text) {
        return webPageRepository.findByText(text);
    }

    private String getDomainFromUrl(String url){
        String domain = url.replaceFirst("^(https?://)?(www\\.)?", "");
        int index = domain.indexOf('/');
        if (index != -1){
            domain = domain.substring(0, index);
        }
        return domain;
    }

    public List<String> getAllLinks(String url) throws IOException {
        WebPage findWebPage = webPageRepository.findByUrl(url);
        if (findWebPage != null){
            return new ArrayList<>();
        }


        List<String> result = new ArrayList<>();

        Document document = Jsoup.connect(url).get();

        Elements links = document.select("a[href]");

        links.stream().parallel().forEach(link -> {
            String linkHref = link.attr("href");
            if (linkHref.startsWith("/")){
                linkHref = "https://" + getDomainFromUrl(url) + linkHref;
            }
            if (!result.contains(linkHref)){
                result.add(linkHref);
            }
        });

        return result;
    }
}