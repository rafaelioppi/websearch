package com.websearch.controller;

import com.websearch.model.Page;
import com.websearch.service.CrawlerService;
import com.websearch.repository.PageRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crawl")
public class CrawlController {

    private final CrawlerService crawlerService;
    private final PageRepository repository;

    public CrawlController(CrawlerService crawlerService, PageRepository repository) {
        this.crawlerService = crawlerService;
        this.repository = repository;
    }

    @PostMapping
    public Page crawl(@RequestParam String url) {
        Page page = crawlerService.crawl(url);
        return repository.save(page);
    }
}