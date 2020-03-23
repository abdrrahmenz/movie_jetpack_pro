package com.abdurrahman.movies.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.abdurrahman.movies.R;
import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.utils.DataDummyTest;
import com.abdurrahman.movies.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailMoviesActivityTest {

    private MoviesEntity dummyMoviesEntity = DataDummyTest.generateDummyMovies().get(0);

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityTestRule = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
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
    public void loadMoviesDetails(){
        onView(withId(R.id.tvTitleDetails)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTitleDetails)).check(matches(withText(dummyMoviesEntity.getTitle())));
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()));
        onView(withId(R.id.tvReleaseDate)).check(matches(withText(dummyMoviesEntity.getReleaseDate())));
    }
}