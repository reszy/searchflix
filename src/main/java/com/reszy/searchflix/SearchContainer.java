package com.reszy.searchflix;

/**
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
