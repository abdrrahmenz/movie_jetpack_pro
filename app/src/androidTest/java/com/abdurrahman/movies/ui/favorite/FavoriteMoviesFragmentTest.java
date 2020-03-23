package com.abdurrahman.movies.ui.favorite;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.abdurrahman.movies.R;
import com.abdurrahman.movies.testing.SingleFragmentActivity;
import com.abdurrahman.movies.ui.favorite.movies.FavoriteMovieFragment;
import com.abdurrahman.movies.ui.movies.MoviesFragment;
import com.abdurrahman.movies.utils.EspressoIdlingResource;
import com.abdurrahman.movies.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FavoriteMoviesFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private FavoriteMovieFragment favoriteMoviesFragment = new FavoriteMovieFragment();

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Before
    public void setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(favoriteMoviesFragment);
    }

    @Test
    public void loadMovies(){
//        onView(withId(R.id.rv_movies_favorite)).check(matches(isDisplayed()));
//        onView(withId(R.id.rv_movies_favorite)).check(new RecyclerViewItemCountAssertion(0));
    }

}