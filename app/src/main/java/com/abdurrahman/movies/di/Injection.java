package com.abdurrahman.movies.di;

import android.app.Application;

import com.abdurrahman.movies.data.source.MoviesRepository;
import com.abdurrahman.movies.data.source.local.LocalDataSource;
import com.abdurrahman.movies.data.source.local.room.MoviesDatabase;
import com.abdurrahman.movies.data.source.remote.RemoteRepository;
import com.abdurrahman.movies.utils.AppExecutors;

public class Injection {
    public static MoviesRepository provideRepository(Application application) {

//        RemoteRepository remoteRepository = RemoteRepository.getInstance();
//
//        return MoviesRepository.getInstance(remoteRepository);
        MoviesDatabase database = MoviesDatabase.getInstance(application);

        RemoteRepository remoteRepository = RemoteRepository.getInstance();
        LocalDataSource localRepository = LocalDataSource.getInstance(database.moviesDao());
        AppExecutors appExecutors = new AppExecutors();

        return MoviesRepository.getInstance(remoteRepository, localRepository, appExecutors);
    }
}
