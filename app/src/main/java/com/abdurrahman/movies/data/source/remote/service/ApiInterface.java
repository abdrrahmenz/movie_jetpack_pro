package com.abdurrahman.movies.data.source.remote.service;

import com.abdurrahman.movies.data.source.remote.response.MoviesListResponses;
import com.abdurrahman.movies.data.source.remote.response.TVShowListResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("3/movie/popular")
    Call<MoviesListResponses> getMovies(@Query("api_key") String apiKey);

    @GET("3/tv/popular")
    Call<TVShowListResponses> getTVShows(@Query("api_key") String apiKey);

}
