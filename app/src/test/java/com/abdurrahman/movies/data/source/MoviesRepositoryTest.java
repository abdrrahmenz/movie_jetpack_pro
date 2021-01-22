package com.abdurrahman.movies.data.source;

import android.os.Handler;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.abdurrahman.movies.data.source.local.LocalDataSource;
import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;
import com.abdurrahman.movies.data.source.remote.RemoteRepository;
import com.abdurrahman.movies.data.source.remote.response.MoviesResponses;
import com.abdurrahman.movies.data.source.remote.response.TVShowResponses;
import com.abdurrahman.movies.data.vo.Resource;
import com.abdurrahman.movies.utils.AppExecutors;
import com.abdurrahman.movies.utils.FakeDataDummy;
import com.abdurrahman.movies.utils.LiveDataTestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoviesRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remote = Mockito.mock(RemoteRepository.class);
    private LocalDataSource local = mock(LocalDataSource.class);
    private AppExecutors appExecutors = mock(AppExecutors.class);
    private FakeMoviesRepository moviesRepository = new FakeMoviesRepository(remote, local, appExecutors);

    private ArrayList<MoviesResponses> moviesResponses = FakeDataDummy.generateRemoteDummyMovies();
    private ArrayList<MoviesEntity> moviesEntities = FakeDataDummy.generateLocalDummyMovies();
    private int movieId = moviesResponses.get(0).getId();

    private ArrayList<TVShowResponses> tvShowResponses = FakeDataDummy.generateRemoteDummyTVShows();
    private ArrayList<TVShowEntity> tvShowEntities = FakeDataDummy.generateLocalDummyTVShows();
    private int tvShowId = tvShowResponses.get(0).getId();

    @Test
    public void getAllMovies() {
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

    @Test
    public void setTvShowFavorite(){
        TVShowEntity dummyTvShow = FakeDataDummy.generateLocalDummyTVShows().get(0);
        boolean state = !dummyTvShow.isFavorite();
        Runnable runnable = () -> moviesRepository.setTvShowsFavorite(dummyTvShow, state);
        Handler handler = mock(Handler.class);
        handler.postDelayed(runnable,100);
    }

    @Test
    public void setMovieFavorite() throws NullPointerException {
        MoviesEntity dummyMovies = FakeDataDummy.generateLocalDummyMovies().get(0);
        boolean state = !dummyMovies.isFavorite();
        Runnable run = () -> moviesRepository.setMoviesFavorite(dummyMovies, state);
        Handler handler = mock(Handler.class);
        handler.postDelayed(run,100);
    }
}