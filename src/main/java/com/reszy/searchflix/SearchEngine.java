package com.reszy.searchflix;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to find movies in outsourced API
 * Created by Rafał on 23.04.2017.
 */
@Service
public class SearchEngine {
    private List<MovieEntry> movies = new ArrayList<>();

    public List<MovieEntry> getMovies() {
        return movies;
    }

    public SearchEngine(){
    }

    private void addJsonsToList(JSONArray array){
        for(int i =0; i<array.length(); i++){
            JSONObject singleJson = array.getJSONObject(i);
            movies.add(new MovieEntry(singleJson.getString("show_title"),singleJson.getString("poster")));
        }
    }

    public List<MovieEntry> search(String query) {
        System.out.print("Query was send:[ " + query + " ]\n");
        movies.clear();
        MiniNetflixRouletteWrapper nflxr = new MiniNetflixRouletteWrapper();
        try {
            JSONArray json = new JSONArray(nflxr.getMovie(query));
            addJsonsToList(json);
        } catch (IOException e) {
        }
        try {
            JSONArray json = new JSONArray(nflxr.getActorData(query));
            addJsonsToList(json);
        } catch (IOException e) {
        }
        try {
            JSONArray json = new JSONArray(nflxr.getDirectorData(query));
            addJsonsToList(json);
        } catch (IOException e) {
        }
        return movies;
    }
}
