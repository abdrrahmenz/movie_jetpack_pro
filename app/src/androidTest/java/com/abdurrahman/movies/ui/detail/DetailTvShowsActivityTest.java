package com.abdurrahman.movies.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;

import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.rule.ActivityTestRule;

import com.abdurrahman.movies.R;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;
import com.abdurrahman.movies.data.source.remote.BaseUrl;
import com.abdurrahman.movies.utils.DataDummyTest;
import com.abdurrahman.movies.utils.ElapsedTimeIdlingResource;
import com.abdurrahman.movies.utils.EspressoIdlingResource;
import com.abdurrahman.movies.utils.RestServiceTestHelper;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class DetailTvShowsActivityTest {

    private TVShowEntity dummyTvShowEntity = DataDummyTest.generateDummyTVShows().get(0);
    private MockWebServer webServer;

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityTestRule = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class,false, false);

    @Before
    public void setUp() throws Exception {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        webServer = new MockWebServer();
        webServer.start();
        BaseUrl.BASE_URL = webServer.url("/").toString();
    }

    @After
    public void tearDown() throws Exception {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
        webServer.shutdown();
    }

    @Test
    public void loadTVShowsDetails() throws Exception {
        // Make sure Espresso does not time out
        IdlingPolicies.setMasterPolicyTimeout(5 * 2, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(5 * 2, TimeUnit.SECONDS);

        IdlingResource idlingResource = new ElapsedTimeIdlingResource(DateUtils.SECOND_IN_MILLIS * 5);
        IdlingRegistry.getInstance().register(idlingResource);

        String fileName = "200_tv_show.json";
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));

        Context context = getInstrumentation().getTargetContext();
        Intent intent = new Intent(context, DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.EXTRA_DETAILS_TVSHOW, dummyTvShowEntity.getTvShowId());
        activityTestRule.launchActivity(intent);

        onView(withId(R.id.tvTitleDetails)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTitleDetails)).check(matches(withText(dummyTvShowEntity.getTitle())));
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()));
        onView(withId(R.id.tvReleaseDate)).check(matches(withText(dummyTvShowEntity.getReleaseDate())));
        onView(withId(R.id.tvDesc)).check(matches(isDisplayed()));
        onView(withId(R.id.tvDesc)).check(matches(withText(dummyTvShowEntity.getOverview())));
        onView(withId(R.id.tvRatings)).check(matches(isDisplayed()));
        onView(withId(R.id.tvRatings)).check(matches(withText(dummyTvShowEntity.getRating())));
        onView(withId(R.id.ratingBar)).check(matches(isDisplayed()));
        onView(withId(R.id.ratingBar)).perform(setRating(dummyTvShowEntity.getRating()));

        IdlingRegistry.getInstance().unregister(idlingResource);
    }

    private static ViewAction setRating(final String rating) {
        Float rate = Float.parseFloat(rating);

        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(RatingBar.class);
            }

            @Override
            public String getDescription() {
                return "Set rating on RatingBar in 0.5f increments";
            }

            @Override
            public void perform(UiController uiController, View view) {
                GeneralClickAction viewAction = new GeneralClickAction(
                        Tap.SINGLE,
                        view1 -> {
                            int[] locationOnScreen = new int[2];
                            view1.getLocationOnScreen(locationOnScreen);
                            int screenX = locationOnScreen[0];
                            int screenY = locationOnScreen[1];
                            int numStars = ((RatingBar) view1).getNumStars();
                            float widthPerStar = 1f * view1.getWidth() / numStars;
                            float percent = rate / numStars;
                            float x = screenX + view1.getWidth() * percent;
                            float y = screenY + view1.getHeight() * 0.5f;
                            return new float[]{x - widthPerStar * 0.5f, y};
                        },
                        Press.FINGER,
                        InputDevice.SOURCE_UNKNOWN,
                        MotionEvent.BUTTON_PRIMARY
                );
                viewAction.perform(uiController, view);
            }
        };
    }
}