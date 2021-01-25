package com.abdurrahman.movies.ui.amovies;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.abdurrahman.movies.data.source.MoviesRepository;
import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.vo.Resource;
import com.abdurrahman.movies.utils.FakeDataDummy;
import com.abdurrahman.movies.viewmodel.MoviesViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MoviesViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MoviesViewModel viewModel;
    private MoviesRepository moviesRepository = mock(MoviesRepository.class);
    private MoviesEntity dummyMovies = FakeDataDummy.generateLocalDummyMovies().get(0);
    private int moviesId = dummyMovies.getIdMovie();

    @Before
    public void setUp(){
        viewModel = new MoviesViewModel(moviesRepository);
        viewModel.setMovieId(moviesId);
        viewModel.setMovieFavorite();
    }

    @Test
    public void getMovies() {
        Resource<List<MoviesEntity>> resource = Resource.success(FakeDataDummy.generateLocalDummyMovies());
        MutableLiveData<Resource<List<MoviesEntity>>> dummyCourses = new MutableLiveData<>();
        dummyCourses.setValue(resource);

        when(moviesRepository.getAllMovies()).thenReturn(dummyCourses);

        Observer<Resource<List<MoviesEntity>>> observer = mock(Observer.class);

        String username = "Abdurrahman";
        viewModel.setUsername(username);

        viewModel.movies.observeForever(observer);

        verify(observer).onChanged(resource);
    }

    @Test
    public void getDetailMovies() {
        Resource<MoviesEntity> resource = Resource.success(FakeDataDummy.getMovieDetail(dummyMovies, true));
        MutableLiveData<Resource<MoviesEntity>> courseEntities = new MutableLiveData<>();
        courseEntities.setValue(resource);

        when(moviesRepository.getDetailsMovies(moviesId)).thenReturn(courseEntities);

        Observer<Resource<MoviesEntity>> observer = mock(Observer.class);
        viewModel.detailMovies.observeForever(observer);

        verify(observer).onChanged(resource);
    }

    @Test
    public void getMovieFavorite() {
        MutableLiveData<Resource<PagedList<MoviesEntity>>> dummyCourse = new MutableLiveData<>();
        PagedList<MoviesEntity> pagedList = mock(PagedList.class);

        dummyCourse.setValue(Resource.success(pagedList));

        when(moviesRepository.getFavoriteMoviesPaged()).thenReturn(dummyCourse);

        Observer<Resource<PagedList<MoviesEntity>>> observer = mock(Observer.class);

        viewModel.getFavorite().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
    }
}