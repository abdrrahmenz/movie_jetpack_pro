package com.abdurrahman.movies.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdurrahman.movies.BuildConfig;
import com.abdurrahman.movies.data.source.remote.response.MoviesListResponses;
import com.abdurrahman.movies.data.source.remote.response.MoviesResponses;
import com.abdurrahman.movies.data.source.remote.response.TVShowListResponses;
import com.abdurrahman.movies.data.source.remote.response.TVShowResponses;
import com.abdurrahman.movies.data.source.remote.service.ApiClient;
import com.abdurrahman.movies.data.source.remote.service.ApiInterface;
import com.abdurrahman.movies.utils.EspressoIdlingResource;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    private RemoteRepository() {
    }

    public static RemoteRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository();
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MoviesResponses>>> getAllMovies() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MoviesResponses>>> resultMovies = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Call<MoviesListResponses> call = apiInterface.getMovies(BuildConfig.API_KEY);
            call.enqueue(new Callback<MoviesListResponses>() {
                @Override
                public void onResponse(@NotNull Call<MoviesListResponses> call, @NotNull Response<MoviesListResponses> response) {
                    assert response.body() != null;
                    resultMovies.setValue(ApiResponse.success(response.body().getMoviesList()));
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<MoviesListResponses> call, @NotNull Throwable t) {

                }
            });
        },2000);

        return resultMovies;
    }

    public LiveData<ApiResponse<List<TVShowResponses>>> getAllTVShows() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TVShowResponses>>> resultTvShow = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Call<TVShowListResponses> call = apiInterface.getTVShows(BuildConfig.API_KEY);
            call.enqueue(new Callback<TVShowListResponses>() {
                @Override
                public void onResponse(Call<TVShowListResponses> call, Response<TVShowListResponses> response) {
                    resultTvShow.setValue(ApiResponse.success(response.body().getTVShowList()));
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement();
                    }
                }

                @Override
                public void onFailure(Call<TVShowListResponses> call, Throwable t) {

                }
            });
        }, 2000);

        return resultTvShow;
    }
}
