package com.reszy.searchflix;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Main controller
 * Created by Rafał on 23.04.2017.
 */
@Controller
public class AppController {

    private SearchEngine service;
    private React react;
    private ObjectMapper mapper;

    @Autowired
    public AppController(SearchEngine service) {
        this.service = service;
        this.react = new React();
        this.mapper = new ObjectMapper();
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) throws Exception {
        List<MovieEntry> movies = service.getMovies();
        String moviesList = react.renderMoviesList(movies);
        String data = mapper.writeValueAsString(movies);
        model.put("content", moviesList);
        model.put("data", data);
        return "index";
    }

}
