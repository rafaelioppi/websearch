package com.websearch.controller;

import com.websearch.model.Page;
import com.websearch.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService service;

    public SearchController(SearchService service) {
        this.service = service;
    }

    @GetMapping
    public List<Page> search(@RequestParam String q) {
        return service.search(q);
    }
}
