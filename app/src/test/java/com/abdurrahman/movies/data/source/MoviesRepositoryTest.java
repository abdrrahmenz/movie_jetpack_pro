package com.abdurrahman.movies.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.abdurrahman.movies.data.source.local.LocalDataSource;
import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;
import com.abdurrahman.movies.data.source.remote.RemoteRepository;
import com.abdurrahman.movies.data.source.remote.response.MovieDetailsResponse;
import com.abdurrahman.movies.data.source.remote.response.MoviesResponses;
import com.abdurrahman.movies.data.source.remote.response.TVShowDetailsResponse;
import com.abdurrahman.movies.data.source.remote.response.TVShowResponses;
import com.abdurrahman.movies.data.vo.Resource;
import com.abdurrahman.movies.utils.AppExecutorTest;
import com.abdurrahman.movies.utils.FakeDataDummy;
import com.abdurrahman.movies.utils.LiveDataTestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MoviesRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remote = Mockito.mock(RemoteRepository.class);
    private LocalDataSource local = mock(LocalDataSource.class);
    private AppExecutorTest appExecutors = mock(AppExecutorTest.class);
    private FakeMoviesRepository moviesRepository = new FakeMoviesRepository(remote, local, appExecutors);

    private ArrayList<MoviesResponses> moviesResponses = FakeDataDummy.generateRemoteDummyMovies();
    private ArrayList<MoviesEntity> moviesEntities = FakeDataDummy.generateLocalDummyMovies();
    private int movieId = moviesResponses.get(0).getId();

    private ArrayList<TVShowResponses> tvShowResponses = FakeDataDummy.generateRemoteDummyTVShows();
    private ArrayList<TVShowEntity> tvShowEntities = FakeDataDummy.generateLocalDummyTVShows();
    private int tvShowId = tvShowResponses.get(0).getId();

    @Test
    public void getAllMovies() {
        /*
        doAnswer(invocation -> {
            ((RemoteRepository.LoadMoviesCallback) invocation.getArguments()[0])
                    .onAllMoviesReceived(moviesResponses);
            return null;
        }).when(remote).getAllMovies(any(RemoteRepository.LoadMoviesCallback.class));

        List<MoviesEntity> result = LiveDataTestUtil.getValue(moviesRepository.getAllMovies());

        verify(remote, times(1)).getAllMovies(any(RemoteRepository.LoadMoviesCallback.class));

        assertNotNull(result);
        assertEquals(moviesResponses.size(), result.size());*/
        MutableLiveData<List<MoviesEntity>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(moviesEntities);

        when(local.getAllMovies()).thenReturn(listMutableLiveData);

        Resource<List<MoviesEntity>> result = LiveDataTestUtil.getValue(moviesRepository.getAllMovies());

        verify(local).getAllMovies();
        assertNotNull(result.data);
        assertEquals(moviesResponses.size(), result.data.size());
    }

    @Test
    public void getAllTvShow() {
        MutableLiveData<List<TVShowEntity>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(tvShowEntities);

        when(local.getAllTvShow()).thenReturn(listMutableLiveData);

        Resource<List<TVShowEntity>> result = LiveDataTestUtil.getValue(moviesRepository.getAllTVShows());

        verify(local).getAllTvShow();
        assertNotNull(result.data);
        assertEquals(tvShowResponses.size(), result.data.size());
    }

    @Test
    public void getDetailMovies() {
        MutableLiveData<MoviesEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(FakeDataDummy.getMovieDetail(FakeDataDummy.generateLocalDummyMovies().get(0), false));

        when(local.getDetailMovies(movieId)).thenReturn(dummyEntity);

        Resource<MoviesEntity> result = LiveDataTestUtil.getValue(moviesRepository.getDetailsMovies(movieId));

        verify(local).getDetailMovies(movieId);
        assertNotNull(result.data);
        assertNotNull(result.data.getTitle());
        assertEquals(moviesResponses.get(0).getTitle(), result.data.getTitle());
    }

    @Test
    public void getDetailTvShow() {
        MutableLiveData<TVShowEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(FakeDataDummy.getTvShowsDetail(FakeDataDummy.generateLocalDummyTVShows().get(0), false));

        when(local.getDetailTvShow(tvShowId)).thenReturn(dummyEntity);

        Resource<TVShowEntity> result = LiveDataTestUtil.getValue(moviesRepository.getDetailsTvShows(tvShowId));

        verify(local).getDetailTvShow(tvShowId);
        assertNotNull(result.data);
        assertNotNull(result.data.getTitle());
        assertEquals(tvShowResponses.get(0).getName(), result.data.getTitle());
    }

    /*
    @Test
    public void getAllTVShows() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTVShowsCallback) invocation.getArguments()[0])
                    .onAllTVShowsReceived(tvShowResponses);
            return null;
        }).when(remote).getAllTVShows(any(RemoteRepository.LoadTVShowsCallback.class));

        List<TVShowEntity> result = LiveDataTestUtil.getValue(moviesRepository.getAllTVShows());

        verify(remote, times(1)).getAllTVShows(any(RemoteRepository.LoadTVShowsCallback.class));

        assertNotNull(result);
        assertEquals(tvShowResponses.size(), result.size());
    }*/

    /*
    @Test
    public void getDetailsMovies() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadMovieDetailsCallback) invocation.getArguments()[1])
                    .onAllMovieDetailsReceived(movieDetailsResponse);
            return null;
        }).when(remote).getDetailMovies(eq(movieId),any(RemoteRepository.LoadMovieDetailsCallback.class));

        MoviesEntity result = LiveDataTestUtil.getValue(moviesRepository.getDetailsMovies(movieId));

        verify(remote, times(1)).getDetailMovies(eq(movieId),any(RemoteRepository.LoadMovieDetailsCallback.class));

        assertNotNull(result);
        assertNotNull(result.getTitle());
        assertEquals(movieDetailsResponse.getTitle(), result.getTitle());
    }*/

    /*
    @Test
    public void getDetailsTvShows() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTVShowDetailsCallback) invocation.getArguments()[1])
                    .onAllTVShowDetailsReceived(tvShowDetailsResponse);
            return null;
        }).when(remote).getDetailTVShows(eq(tvShowId),any(RemoteRepository.LoadTVShowDetailsCallback.class));

        TVShowEntity result = LiveDataTestUtil.getValue(moviesRepository.getDetailsTvShows(tvShowId));

        verify(remote, times(1)).getDetailTVShows(eq(tvShowId),any(RemoteRepository.LoadTVShowDetailsCallback.class));

        assertNotNull(result);
        assertNotNull(result.getTitle());
        assertEquals(tvShowDetailsResponse.getName(), result.getTitle());
    }*/
}