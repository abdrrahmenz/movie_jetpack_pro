package com.abdurrahman.movies.ui.amovies;

import android.text.format.DateUtils;

import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.rule.ActivityTestRule;

import com.abdurrahman.movies.R;
import com.abdurrahman.movies.data.source.remote.BaseUrl;
import com.abdurrahman.movies.testing.SingleFragmentActivity;
import com.abdurrahman.movies.utils.ElapsedTimeIdlingResource;
import com.abdurrahman.movies.utils.RecyclerViewItemCountAssertion;
import com.abdurrahman.movies.utils.RestServiceTestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class MoviesFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private MoviesFragment moviesFragment = new MoviesFragment();
    private MockWebServer webServer;

    @After
    public void tearDown() throws Exception {
        webServer.shutdown();
    }

    @Before
    public void setUp() throws Exception {
        webServer = new MockWebServer();
        webServer.start();
        BaseUrl.BASE_URL = webServer.url("/").toString();
        activityRule.getActivity().setFragment(moviesFragment);
    }

    @Test
    public void loadMovies() throws Exception {
        // Make sure Espresso does not time out
        IdlingPolicies.setMasterPolicyTimeout(5 * 2, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(5 * 2, TimeUnit.SECONDS);

        IdlingResource idlingResource = new ElapsedTimeIdlingResource(DateUtils.SECOND_IN_MILLIS * 5);
        IdlingRegistry.getInstance().register(idlingResource);

        String fileName = "list_movie.json";
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movies)).check(new RecyclerViewItemCountAssertion(20));

        IdlingRegistry.getInstance().unregister(idlingResource);
    }

}