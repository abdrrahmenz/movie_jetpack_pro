package com.abdurrahman.movies.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.abdurrahman.movies.data.NetworkBoundResource;
import com.abdurrahman.movies.data.source.local.LocalDataSource;
import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;
import com.abdurrahman.movies.data.source.remote.ApiResponse;
import com.abdurrahman.movies.data.source.remote.RemoteRepository;
import com.abdurrahman.movies.data.source.remote.response.MoviesResponses;
import com.abdurrahman.movies.data.source.remote.response.TVShowResponses;
import com.abdurrahman.movies.data.vo.Resource;
import com.abdurrahman.movies.utils.AppExecutors;
import java.util.ArrayList;
import java.util.List;

public class MoviesRepository implements MoviesDataSource {

    private volatile static MoviesRepository INSTANCE = null;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;
    private final RemoteRepository remoteRepository;

    private MoviesRepository(@NonNull RemoteRepository remoteRepository, LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.remoteRepository = remoteRepository;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }

    public static MoviesRepository getInstance(RemoteRepository remoteData, LocalDataSource localDataSource, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (MoviesRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MoviesRepository(remoteData, localDataSource, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    /*
    @Override
    public LiveData<List<MoviesEntity>> getAllMovies() {

        MutableLiveData<List<MoviesEntity>> moviesResults = new MutableLiveData<>();
        remoteRepository.getAllMovies(new RemoteRepository.LoadMoviesCallback() {
            @Override
            public void onAllMoviesReceived(List<MoviesResponses> moviesResponses) {
                ArrayList<MoviesEntity> movieList = new ArrayList<>();
                for (int i = 0; i < moviesResponses.size(); i++) {
                    MoviesResponses response = moviesResponses.get(i);
                    MoviesEntity movies = new MoviesEntity(
                            response.getId(),
                            response.getVoteAverage().toString(),
                            response.getTitle(),
                            response.getReleaseDate(),
                            response.getOverview(),
                            response.getPosterPath()
                    );
                    movieList.add(movies);
                }
                moviesResults.postValue(movieList);
            }

            @Override
            public void onDataNotAvailable(String message) {

            }
        });
        return moviesResults;
    } */

    @Override
    public LiveData<Resource<List<MoviesEntity>>> getAllMovies() {
        return new NetworkBoundResource<List<MoviesEntity>, List<MoviesResponses>>(appExecutors) {
            @Override
            protected LiveData<List<MoviesEntity>> loadFromDB() {
                return localDataSource.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MoviesEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MoviesResponses>>> createCall() {
                return remoteRepository.getAllMovies();
            }

            @Override
            protected void saveCallResult(List<MoviesResponses> data) {
                List<MoviesEntity> moviesEntities = new ArrayList<>();
                for (MoviesResponses movies : data) {
                    moviesEntities.add(new MoviesEntity(
                            movies.getId(),
                            movies.getVoteAverage().toString(),
                            movies.getTitle(),
                            movies.getReleaseDate(),
                            movies.getOverview(),
                            movies.getPosterPath(),
                            null
                    ));
                }
                localDataSource.insertMovies(moviesEntities);
            }
        }.asLiveData();
    }

    /*
    @Override
    public LiveData<List<TVShowEntity>> getAllTVShows() {
        MutableLiveData<List<TVShowEntity>> tvShowResults = new MutableLiveData<>();
        remoteRepository.getAllTVShows(new RemoteRepository.LoadTVShowsCallback() {
            @Override
            public void onAllTVShowsReceived(List<TVShowResponses> tvShowResponses) {
                ArrayList<TVShowEntity> tvShowsList = new ArrayList<>();
                for (int i = 0; i < tvShowResponses.size(); i++) {
                    TVShowResponses response = tvShowResponses.get(i);
                    TVShowEntity tvShows = new TVShowEntity(
                            response.getId(),
                            response.getVoteAverage().toString(),
                            response.getName(),
                            response.getFirstAirDate(),
                            response.getOverview(),
                            response.getPosterPath()
                    );
                    tvShowsList.add(tvShows);
                }
                tvShowResults.postValue(tvShowsList);
            }

            @Override
            public void onDataNotAvailable(String message) {

            }
        });
        return tvShowResults;
    } */

    @Override
    public LiveData<Resource<List<TVShowEntity>>> getAllTVShows() {
        return new NetworkBoundResource<List<TVShowEntity>, List<TVShowResponses>>(appExecutors) {

            @Override
            protected LiveData<List<TVShowEntity>> loadFromDB() {
                return localDataSource.getAllTvShow();
            }

            @Override
            protected Boolean shouldFetch(List<TVShowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TVShowResponses>>> createCall() {
                return remoteRepository.getAllTVShows();
            }

            @Override
            protected void saveCallResult(List<TVShowResponses> data) {
                List<TVShowEntity> tvShowEntities = new ArrayList<>();
                for (TVShowResponses movies : data) {
                    tvShowEntities.add(new TVShowEntity(
                            movies.getId(),
                            movies.getVoteAverage().toString(),
                            movies.getName(),
                            movies.getFirstAirDate(),
                            movies.getOverview(),
                            movies.getPosterPath(),
                            null));
                }

                localDataSource.insertTvShow(tvShowEntities);

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MoviesEntity>> getDetailsMovies(int moviesId) {
        return new NetworkBoundResource<MoviesEntity, List<MoviesResponses>>(appExecutors) {

            @Override
            protected LiveData<MoviesEntity> loadFromDB() {
                return localDataSource.getDetailMovies(moviesId);
            }

            @Override
            protected Boolean shouldFetch(MoviesEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<List<MoviesResponses>>> createCall() {
                return remoteRepository.getAllMovies();
            }

            @Override
            protected void saveCallResult(List<MoviesResponses> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TVShowEntity>> getDetailsTvShows(int tvShowId) {
        return new NetworkBoundResource<TVShowEntity, List<TVShowResponses>>(appExecutors) {

            @Override
            protected LiveData<TVShowEntity> loadFromDB() {
                return localDataSource.getDetailTvShow(tvShowId);
            }

            @Override
            protected Boolean shouldFetch(TVShowEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<List<TVShowResponses>>> createCall() {
                return remoteRepository.getAllTVShows();
            }

            @Override
            protected void saveCallResult(List<TVShowResponses> data) {

            }
        }.asLiveData();
    }
    @Override
    public void setMoviesFavorite(MoviesEntity moviesEntity, boolean state) {
        Runnable runnable = () -> localDataSource.setMoviesFavorite(moviesEntity, state);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void setTvShowsFavorite(TVShowEntity tvShowEntity, boolean state) {
        Runnable runnable = () -> localDataSource.setTvShowFavorite(tvShowEntity, state);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public LiveData<Resource<PagedList<MoviesEntity>>> getFavoriteMoviesPaged() {
        return new NetworkBoundResource<PagedList<MoviesEntity>, List<MoviesResponses>>(appExecutors) {

            @Override
            protected LiveData<PagedList<MoviesEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localDataSource.getFavoriteMoviesAsPaged(), 10).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MoviesEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MoviesResponses>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MoviesResponses> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TVShowEntity>>> getFavoriteTvShowPaged() {
        return new NetworkBoundResource<PagedList<TVShowEntity>, List<TVShowResponses>>(appExecutors) {
            @Override
            protected LiveData<PagedList<TVShowEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localDataSource.getFavoriteTvShowAsPaged(), 10).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TVShowEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TVShowResponses>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TVShowResponses> data) {

            }
        }.asLiveData();
    }

    /*
    @Override
    public LiveData<MoviesEntity> getDetailsMovies(int moviesId) {
        MutableLiveData<MoviesEntity> moviesResult = new MutableLiveData<>();
        remoteRepository.getDetailMovies(moviesId, new RemoteRepository.LoadMovieDetailsCallback() {
            @Override
            public void onAllMovieDetailsReceived(MovieDetailsResponse movieDetailsResponse) {
                if (movieDetailsResponse.getId().equals(moviesId)) {
                    MoviesEntity detailMovie = new MoviesEntity(
                            movieDetailsResponse.getId(),
                            movieDetailsResponse.getVoteAverage().toString(),
                            movieDetailsResponse.getTitle(),
                            movieDetailsResponse.getReleaseDate(),
                            movieDetailsResponse.getOverview(),
                            movieDetailsResponse.getPosterPath());
                    moviesResult.postValue(detailMovie);
                }
            }
            @Override
            public void onDataNotAvailable(String message) {

            }
        });
        return moviesResult;
    }*/

    /*
    @Override
    public LiveData<TVShowEntity> getDetailsTvShows(int tvShowsId) {
        MutableLiveData<TVShowEntity> tvShowsResult = new MutableLiveData<>();

        remoteRepository.getDetailTVShows(tvShowsId, new RemoteRepository.LoadTVShowDetailsCallback() {

            @Override
            public void onAllTVShowDetailsReceived(TVShowDetailsResponse tvShowDetailsResponse) {
                if (tvShowDetailsResponse.getId().equals(tvShowsId)) {
                    TVShowEntity detailsResponse = new TVShowEntity(
                            tvShowDetailsResponse.getId(),
                            tvShowDetailsResponse.getVoteAverage().toString(),
                            tvShowDetailsResponse.getName(),
                            tvShowDetailsResponse.getFirstAirDate(),
                            tvShowDetailsResponse.getOverview(),
                            tvShowDetailsResponse.getPosterPath());
                    tvShowsResult.postValue(detailsResponse);
                }
            }
            @Override
            public void onDataNotAvailable(String message) {

            }
        });
        return tvShowsResult;
    }*/
}
