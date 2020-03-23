package com.abdurrahman.movies.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;
import com.abdurrahman.movies.data.vo.Resource;

import java.util.List;

public interface MoviesDataSource {

//    LiveData<List<MoviesEntity>> getAllMovies();
//    LiveData<List<TVShowEntity>> getAllTVShows();
//    LiveData<MoviesEntity> getDetailsMovies(int moviesId);
//    LiveData<TVShowEntity> getDetailsTvShows(int tvShowsId);

    LiveData<Resource<List<TVShowEntity>>> getAllTVShows();

    LiveData<Resource<List<MoviesEntity>>> getAllMovies();

    LiveData<Resource<MoviesEntity>> getDetailsMovies(int moviesId);

    void setMoviesFavorite(MoviesEntity moviesEntity, boolean state);

    LiveData<Resource<PagedList<MoviesEntity>>> getFavoriteMoviesPaged();

    LiveData<Resource<TVShowEntity>> getDetailsTvShows(int tvShowId);

    void setTvShowsFavorite(TVShowEntity tvShowEntity, boolean state);

    LiveData<Resource<PagedList<TVShowEntity>>> getFavoriteTvShowPaged();

}
