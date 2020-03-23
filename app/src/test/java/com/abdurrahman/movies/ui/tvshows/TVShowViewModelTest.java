package com.abdurrahman.movies.ui.tvshows;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.abdurrahman.movies.data.source.MoviesRepository;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;
import com.abdurrahman.movies.data.vo.Resource;
import com.abdurrahman.movies.utils.FakeDataDummy;
import com.abdurrahman.movies.viewmodel.TVShowsViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TVShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TVShowsViewModel viewModel;
    private MoviesRepository moviesRepository = mock(MoviesRepository.class);
    private TVShowEntity dummyTvShow = FakeDataDummy.generateLocalDummyTVShows().get(0);
    private int tvShowId = dummyTvShow.getTvShowId();

    @Before
    public void setUp(){
        viewModel = new TVShowsViewModel(moviesRepository);
        viewModel.setTvShowId(tvShowId);
        viewModel.setFavorite();
    }

    @Test
    public void getTVShows() {
        /*
        ArrayList<TVShowEntity> dummyTVShows = FakeDataDummy.generateDummyTVShows();

        MutableLiveData<List<TVShowEntity>> tvShows = new MutableLiveData<>();
        tvShows.setValue(dummyTVShows);

        when(moviesRepository.getAllTVShows()).thenReturn(tvShows);

        Observer<List<TVShowEntity>> observer = mock(Observer.class);

        viewModel.getTVShows().observeForever(observer);

        verify(observer).onChanged(dummyTVShows);*/
        Resource<List<TVShowEntity>> resource = Resource.success(FakeDataDummy.generateLocalDummyTVShows());
        MutableLiveData<Resource<List<TVShowEntity>>> dummyCourses = new MutableLiveData<>();
        dummyCourses.setValue(resource);

        when(moviesRepository.getAllTVShows()).thenReturn(dummyCourses);

        Observer<Resource<List<TVShowEntity>>> observer = mock(Observer.class);

        String username = "Dicoding";
        viewModel.setUsername(username);

        viewModel.tvShow.observeForever(observer);

        verify(observer).onChanged(resource);
    }

    @Test
    public void getDetailTVShow() {
        Resource<TVShowEntity> resource = Resource.success(FakeDataDummy.getTvShowsDetail(dummyTvShow, true));
        MutableLiveData<Resource<TVShowEntity>> courseEntities = new MutableLiveData<>();
        courseEntities.setValue(resource);

        when(moviesRepository.getDetailsTvShows(tvShowId)).thenReturn(courseEntities);

        Observer<Resource<TVShowEntity>> observer = mock(Observer.class);
        viewModel.detailTvShow.observeForever(observer);

        verify(observer).onChanged(resource);
    }

    @Test
    public void getFavorite() {
        MutableLiveData<Resource<PagedList<TVShowEntity>>> dummyCourse = new MutableLiveData<>();
        PagedList<TVShowEntity> pagedList = mock(PagedList.class);

        dummyCourse.setValue(Resource.success(pagedList));

        when(moviesRepository.getFavoriteTvShowPaged()).thenReturn(dummyCourse);

        Observer<Resource<PagedList<TVShowEntity>>> observer = mock(Observer.class);

        viewModel.getFavorite().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
    }
}