package com.reszy.searchflix;

/**
 * Data container for sending form from react to server
 * Created by Rafał on 25.04.2017.
 */
public class SearchContainer {
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public SearchContainer() {
        this.search = new String();
    }


    public SearchContainer(String search) {
        this.search = search;
    }

    private String search;
}
