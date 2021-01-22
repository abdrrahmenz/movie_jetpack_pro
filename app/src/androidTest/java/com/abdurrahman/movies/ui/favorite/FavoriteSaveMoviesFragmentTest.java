package com.abdurrahman.movies.ui.favorite;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.abdurrahman.movies.R;
import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.ui.detail.DetailMovieActivity;
import com.abdurrahman.movies.utils.DataDummyTest;
import com.abdurrahman.movies.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class FavoriteSaveMoviesFragmentTest {
    private MoviesEntity dummyMoviesEntity = DataDummyTest.generateDummyMovies().get(0);

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityTestRule = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context context = getInstrumentation().getTargetContext();
            Intent result = new Intent(context, DetailMovieActivity.class);
            result.putExtra(DetailMovieActivity.EXTRA_DETAILS_MOVIES, dummyMoviesEntity.getIdMovie());
            return result;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void saveFavoriteMovies(){
        onView(withContentDescription(R.string.favorite))
                .perform(click());
    }
}
