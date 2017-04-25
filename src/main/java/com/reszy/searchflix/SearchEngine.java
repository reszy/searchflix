package com.reszy.searchflix;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.net.codeusa.NetflixRoulette;

/**
 * Created by Rafał on 23.04.2017.
 */
@Service
public class SearchEngine {
    private List<MovieEntry> movies = new ArrayList<>();

    public List<MovieEntry> getMovies() {
        return movies;
    }

    public SearchEngine(){
        movies.add(new MovieEntry("x","http://x3.wykop.pl/cdn/c3201142/comment_40LkFHlJHp36E66iaRawwKGNUqX721h7,w400.jpg"));
    }

    public List<MovieEntry> search(String query) {
        NetflixRoulette nflxr = new NetflixRoulette();
        try {
            JSONObject result = new JSONObject(nflxr.getAllData(query));
            movies.add(new MovieEntry("x","d"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
