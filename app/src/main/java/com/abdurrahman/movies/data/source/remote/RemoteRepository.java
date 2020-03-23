package com.abdurrahman.movies.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdurrahman.movies.data.source.remote.response.MovieDetailsResponse;
import com.abdurrahman.movies.data.source.remote.response.MoviesListResponses;
import com.abdurrahman.movies.data.source.remote.response.MoviesResponses;
import com.abdurrahman.movies.data.source.remote.response.TVShowDetailsResponse;
import com.abdurrahman.movies.data.source.remote.response.TVShowListResponses;
import com.abdurrahman.movies.data.source.remote.response.TVShowResponses;
import com.abdurrahman.movies.data.source.remote.service.ApiClient;
import com.abdurrahman.movies.data.source.remote.service.ApiInterface;
import com.abdurrahman.movies.utils.EspressoIdlingResource;
import com.abdurrahman.movies.BuildConfig;
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

//    public void getAllMovies(LoadMoviesCallback callback) {
//        EspressoIdlingResource.increment();
//        Call<MoviesListResponses> call = apiInterface.getMovies(BuildConfig.API_KEY);
//        call.enqueue(new Callback<MoviesListResponses>() {
//            @Override
//            public void onResponse(@NotNull Call<MoviesListResponses> call, @NotNull Response<MoviesListResponses> response) {
//                if (response.body() != null && response.isSuccessful()){
//                    callback.onAllMoviesReceived(response.body().getMoviesList());
//                    EspressoIdlingResource.decrement();
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<MoviesListResponses> call, @NotNull Throwable t) {
//                callback.onDataNotAvailable(t.toString());
//                EspressoIdlingResource.decrement();
//            }
//        });
//    }

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
        /*EspressoIdlingResource.increment();
        Call<TVShowListResponses> call = apiInterface.getTVShows(BuildConfig.API_KEY);
        call.enqueue(new Callback<TVShowListResponses>() {
            @Override
            public void onResponse(@NotNull Call<TVShowListResponses> call, @NotNull Response<TVShowListResponses> response) {
                if (response.body() != null && response.isSuccessful()){
                    callback.onAllTVShowsReceived(response.body().getTVShowList());
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NotNull Call<TVShowListResponses> call, @NotNull Throwable t) {
                callback.onDataNotAvailable(t.toString());
                EspressoIdlingResource.decrement();
            }
        });
         */
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

    public void getDetailMovies(int movieId, LoadMovieDetailsCallback callback) {
        EspressoIdlingResource.increment();
        Call<MovieDetailsResponse> call = apiInterface.getDetailMovies(movieId, BuildConfig.API_KEY);
        call.enqueue(new Callback<MovieDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieDetailsResponse> call, @NotNull Response<MovieDetailsResponse> response) {
                if (response.body() != null && response.isSuccessful()){
                    callback.onAllMovieDetailsReceived(response.body());
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NotNull Call<MovieDetailsResponse> call, @NotNull Throwable t) {
                callback.onDataNotAvailable(t.toString());
                EspressoIdlingResource.decrement();
            }
        });
    }

    public void getDetailTVShows(int tvId, LoadTVShowDetailsCallback callback) {
        EspressoIdlingResource.increment();
        Call<TVShowDetailsResponse> call = apiInterface.getDetailTVShows(tvId, BuildConfig.API_KEY);
        call.enqueue(new Callback<TVShowDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVShowDetailsResponse> call, @NotNull Response<TVShowDetailsResponse> response) {
                if (response.body() != null && response.isSuccessful()){
                    callback.onAllTVShowDetailsReceived(response.body());
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NotNull Call<TVShowDetailsResponse> call, @NotNull Throwable t) {
                callback.onDataNotAvailable(t.toString());
                EspressoIdlingResource.decrement();
            }
        });
    }

    public interface LoadMoviesCallback {
        void onAllMoviesReceived(List<MoviesResponses> moviesResponses);

        void onDataNotAvailable(String message);
    }

    public interface LoadMovieDetailsCallback {
        void onAllMovieDetailsReceived(MovieDetailsResponse movieDetailsResponse);

        void onDataNotAvailable(String message);
    }

    public interface LoadTVShowsCallback {
        void onAllTVShowsReceived(List<TVShowResponses> tvShowResponses);

        void onDataNotAvailable(String message);
    }

    public interface LoadTVShowDetailsCallback {
        void onAllTVShowDetailsReceived(TVShowDetailsResponse tvShowDetailsResponse);

        void onDataNotAvailable(String message);
    }
}
