package com.websearch.service;

import com.websearch.model.Page;
import com.websearch.repository.PageRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchService {

    private final PageRepository repository;

    public SearchService(PageRepository repository) {
        this.repository = repository;
    }

    public List<Page> search(String query) {

        List<Page> results = new ArrayList<>();

        // 1. busca por título (mais forte)
        List<Page> titleResults = repository.findByTitleContainingIgnoreCase(query);

        // 2. busca por conteúdo (mais fraco)
        List<Page> contentResults = repository.findByContentContainingIgnoreCase(query);

        // 3. merge simples (sem duplicados)
        Set<Long> seen = new HashSet<>();

        for (Page p : titleResults) {
            results.add(p);
            seen.add(p.getId());
        }

        for (Page p : contentResults) {
            if (!seen.contains(p.getId())) {
                results.add(p);
            }
        }

        return results;
    }
}