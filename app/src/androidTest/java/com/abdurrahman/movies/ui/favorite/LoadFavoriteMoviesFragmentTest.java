package com.abdurrahman.movies.ui.favorite;

import android.text.format.DateUtils;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.rule.ActivityTestRule;

import com.abdurrahman.movies.R;
import com.abdurrahman.movies.testing.SingleFragmentActivity;
import com.abdurrahman.movies.ui.favorite.movies.FavoriteMovieFragment;
import com.abdurrahman.movies.utils.ElapsedTimeIdlingResource;
import com.abdurrahman.movies.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoadFavoriteMoviesFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private FavoriteMovieFragment favoriteMoviesFragment = new FavoriteMovieFragment();

    @After
    public void tearDown() {
    }

    @Before
    public void setUp(){
        activityRule.getActivity().setFragment(favoriteMoviesFragment);
    }

    @Test
    public void loadFavoriteMovies() throws Exception {
        // Make sure Espresso does not time out
        IdlingPolicies.setMasterPolicyTimeout(5 * 2, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(5 * 2, TimeUnit.SECONDS);

        IdlingResource idlingResource = new ElapsedTimeIdlingResource(DateUtils.SECOND_IN_MILLIS * 5);
        IdlingRegistry.getInstance().register(idlingResource);

        onView(withId(R.id.rv_movies_favorite)).check(matches(isDisplayed()));

        if (getRvCount() > 0) {
            onView(withId(R.id.rv_movies_favorite)).check(new RecyclerViewItemCountAssertion(1));
        } else {
            onView(withId(R.id.rv_movies_favorite)).check(new RecyclerViewItemCountAssertion(0));
        }

        IdlingRegistry.getInstance().unregister(idlingResource);
    }

    private int getRvCount() {
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.rv_movies_favorite);
        return Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();
    }
}