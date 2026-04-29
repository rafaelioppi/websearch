package com.websearch.service;

import com.websearch.model.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class CrawlerService {

    public Page crawl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            String title = doc.title();
            String content = doc.body().text();

            return new Page(null, url, title, content);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer crawl: " + url, e);
        }
    }
}