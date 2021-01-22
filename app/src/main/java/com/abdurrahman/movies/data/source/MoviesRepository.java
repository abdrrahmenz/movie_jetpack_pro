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
}
