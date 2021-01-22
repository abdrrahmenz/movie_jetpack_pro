package com.abdurrahman.movies.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.abdurrahman.movies.data.source.MoviesRepository;
import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.vo.Resource;

import java.util.List;

public class MoviesViewModel extends ViewModel {

    private MoviesRepository moviesRepository;

    private MutableLiveData<String> isLogin = new MutableLiveData<>();
    private MutableLiveData<Integer> movieId = new MutableLiveData<>();

    public MoviesViewModel(MoviesRepository repository) {
        this.moviesRepository = repository;
    }

    public LiveData<Resource<List<MoviesEntity>>> movies = Transformations.switchMap(isLogin,
            data -> moviesRepository.getAllMovies());

    public LiveData<Resource<MoviesEntity>> detailMovies = Transformations.switchMap(movieId,
            movieId -> moviesRepository.getDetailsMovies(movieId));

    public void setUsername(String username) {
        isLogin.setValue(username);
    }

    public void setMovieId(Integer movieId) {
        this.movieId.setValue(movieId);
    }

    public void setMovieFavorite() {
        if (detailMovies.getValue() != null) {
            MoviesEntity moviesEntity = detailMovies.getValue().data;

            if (moviesEntity != null) {
                final boolean newState = !moviesEntity.isFavorite();
                moviesRepository.setMoviesFavorite(moviesEntity, newState);
            }
        }
    }

    public LiveData<Resource<PagedList<MoviesEntity>>> getFavorite() {
        return moviesRepository.getFavoriteMoviesPaged();
    }
}
