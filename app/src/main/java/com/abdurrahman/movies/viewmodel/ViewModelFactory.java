package com.abdurrahman.movies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.abdurrahman.movies.data.source.MoviesRepository;
import com.abdurrahman.movies.di.Injection;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final MoviesRepository mMoviesRepository;

    private ViewModelFactory(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel > T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MoviesViewModel.class)) {
            //noinspection unchecked
            return (T) new MoviesViewModel(mMoviesRepository);
        } else if (modelClass.isAssignableFrom(TVShowsViewModel.class)) {
            //noinspection unchecked
            return (T) new TVShowsViewModel(mMoviesRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
