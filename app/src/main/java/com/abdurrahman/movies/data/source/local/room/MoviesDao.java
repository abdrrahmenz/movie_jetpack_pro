package com.abdurrahman.movies.data.source.local.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;

import java.util.List;

@Dao
public interface MoviesDao {

    @WorkerThread
    @Query("SELECT * FROM movies")
    LiveData<List<MoviesEntity>> getMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovies(List<MoviesEntity> moviesEntities);

    @WorkerThread
    @Query("SELECT * FROM movies where favorite = 1")
    DataSource.Factory<Integer, MoviesEntity> getFavoriteMoviesAsPaged();

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateMovies(MoviesEntity moviesEntity);

    @Query("SELECT * FROM movies WHERE id = :id")
    LiveData<MoviesEntity> getDetailMovies(int id);

    @WorkerThread
    @Query("SELECT * FROM tvshows")
    LiveData<List<TVShowEntity>> getTvShow();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTvShow(List<TVShowEntity> tvShowEntities);

    @WorkerThread
    @Query("SELECT * FROM tvshows where favorite = 1")
    DataSource.Factory<Integer, TVShowEntity> getFavoriteTvShowAsPaged();

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateTvShow(TVShowEntity tvShowEntity);

    @Query("SELECT * FROM tvshows WHERE id = :id")
    LiveData<TVShowEntity> getDetailTvShow(int id);



}
