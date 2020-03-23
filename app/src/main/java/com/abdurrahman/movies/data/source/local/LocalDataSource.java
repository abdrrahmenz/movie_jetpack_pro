package com.abdurrahman.movies.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;
import com.abdurrahman.movies.data.source.local.room.MoviesDao;
import java.util.List;

public class LocalDataSource {

    private final MoviesDao moviesDao;
    private static LocalDataSource INSTANCE;

    public LocalDataSource(MoviesDao moviesDao) {
        this.moviesDao = moviesDao;
    }

    public static LocalDataSource getInstance(MoviesDao moviesDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(moviesDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MoviesEntity>> getAllMovies() {
        return moviesDao.getMovies();
    }

    public void insertMovies(List<MoviesEntity> movies) {
        moviesDao.insertMovies(movies);
    }

    public LiveData<MoviesEntity> getDetailMovies(int id) {
        return moviesDao.getDetailMovies(id);
    }

    public DataSource.Factory<Integer, MoviesEntity> getFavoriteMoviesAsPaged() {
        return moviesDao.getFavoriteMoviesAsPaged();
    }

    public void setMoviesFavorite(MoviesEntity movies, boolean newState) {
        movies.setFavorite(newState);
        moviesDao.updateMovies(movies);
    }


    public LiveData<List<TVShowEntity>> getAllTvShow() {
        return moviesDao.getTvShow();
    }

    public void insertTvShow(List<TVShowEntity> tvShow) {
        moviesDao.insertTvShow(tvShow);
    }

    public LiveData<TVShowEntity> getDetailTvShow(int id) {
        return moviesDao.getDetailTvShow(id);
    }

    public DataSource.Factory<Integer, TVShowEntity> getFavoriteTvShowAsPaged() {
        return moviesDao.getFavoriteTvShowAsPaged();
    }

    public void setTvShowFavorite(TVShowEntity tvShow, boolean newState) {
        tvShow.setFavorite(newState);
        moviesDao.updateTvShow(tvShow);
    }

}
