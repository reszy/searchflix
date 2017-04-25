package com.reszy.searchflix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Rafał on 24.04.2017.
 */
@RestController
@RequestMapping("/results.json")
public class SearchEngineController {

    private SearchEngine service;

    @Autowired
    public SearchEngineController(SearchEngine service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<MovieEntry> getMovies() {
        return service.getMovies();
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<MovieEntry> search(String query) {
        return service.search(query);
    }

}
