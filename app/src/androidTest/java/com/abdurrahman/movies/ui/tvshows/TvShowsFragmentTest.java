package com.abdurrahman.movies.ui.tvshows;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.abdurrahman.movies.R;
import com.abdurrahman.movies.data.source.remote.BaseUrl;
import com.abdurrahman.movies.testing.SingleFragmentActivity;
import com.abdurrahman.movies.utils.EspressoIdlingResource;
import com.abdurrahman.movies.utils.RecyclerViewItemCountAssertion;
import com.abdurrahman.movies.utils.RestServiceTestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class TvShowsFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private TvShowsFragment tvShowsFragment = new TvShowsFragment();
    private MockWebServer webServer;

    @After
    public void tearDown() throws Exception {
        webServer.shutdown();
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Before
    public void setUp() throws Exception {
        webServer = new MockWebServer();
        webServer.start();
        BaseUrl.BASE_URL = webServer.url("/").toString();
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(tvShowsFragment);
    }

    @Test
    public void loadTVShows() throws Exception {
        String fileName = "list_movie.json";
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_shows)).check(new RecyclerViewItemCountAssertion(20));
    }
}