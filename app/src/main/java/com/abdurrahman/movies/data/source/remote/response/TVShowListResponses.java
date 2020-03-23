package com.abdurrahman.movies.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShowListResponses {
    @SerializedName("page")
    private Integer page;
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("total_pages")
    private Integer totalPages;
    @SerializedName("results")
    private List<TVShowResponses> resultsTvShow = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<TVShowResponses> getTVShowList() {
        return resultsTvShow;
    }

    public void setTVShowList(List<TVShowResponses> results) {
        this.resultsTvShow = results;
    }

}

