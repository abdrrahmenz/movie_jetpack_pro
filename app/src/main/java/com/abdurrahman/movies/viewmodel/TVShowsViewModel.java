package com.abdurrahman.movies.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.abdurrahman.movies.data.source.MoviesRepository;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;
import com.abdurrahman.movies.data.vo.Resource;

import java.util.List;

public class TVShowsViewModel extends ViewModel {

    private MoviesRepository moviesRepository;

    private MutableLiveData<String> mLogin = new MutableLiveData<>();
    private MutableLiveData<Integer> tvShowId = new MutableLiveData<>();

    public TVShowsViewModel(MoviesRepository mMoviesRepository) {
        this.moviesRepository = mMoviesRepository;
    }

//    public LiveData<List<TVShowEntity>> getTVShows() {
//        return moviesRepository.getAllTVShows();
//    }

    public LiveData<Resource<List<TVShowEntity>>> tvShow = Transformations.switchMap(mLogin,
            data -> moviesRepository.getAllTVShows());

    public void setUsername(String username) {
        mLogin.setValue(username);
    }

    public LiveData<Resource<TVShowEntity>> detailTvShow = Transformations.switchMap(tvShowId,
            tvShowId -> moviesRepository.getDetailsTvShows(tvShowId));

    public void setTvShowId(Integer tvShowId) {
        this.tvShowId.setValue(tvShowId);
    }

    // Favorite
    public void setFavorite() {
        if (detailTvShow.getValue() != null) {
            TVShowEntity tvShowEntity = detailTvShow.getValue().data;

            if (tvShowEntity != null) {
                // Kode di bawah menggunakan tanda seru (!),
                // karena akan mengganti status dari apakah sudah di bookmark atau tidak menjadi apakah sudah siap dibookmark atau tidak
                final boolean newState = !tvShowEntity.isFavorite();
                moviesRepository.setTvShowsFavorite(tvShowEntity, newState);
            }
        }
    }

    public LiveData<Resource<PagedList<TVShowEntity>>> getFavorite() {
        return moviesRepository.getFavoriteTvShowPaged();
    }

    public void setBookmark(TVShowEntity tvShowEntity) {
        final boolean newState = !tvShowEntity.isFavorite();
        moviesRepository.setTvShowsFavorite(tvShowEntity, newState);
    }
}
