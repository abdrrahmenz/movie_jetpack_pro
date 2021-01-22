package com.abdurrahman.movies.ui.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.abdurrahman.movies.BuildConfig;
import com.abdurrahman.movies.R;
import com.abdurrahman.movies.utils.GlideApp;
import com.abdurrahman.movies.viewmodel.MoviesViewModel;
import com.abdurrahman.movies.viewmodel.TVShowsViewModel;
import com.abdurrahman.movies.viewmodel.ViewModelFactory;
import com.bumptech.glide.request.RequestOptions;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_DETAILS_MOVIES = "extra_id_movies";
    public static final String EXTRA_DETAILS_TVSHOW = "extra_id_tvshow";
    private int movieId, tvShowId;
    private TextView textTitle;
    private TextView textDesc;
    private ImageView imagePoster;
    private TextView textDate;
    private TextView textRating;
    private RatingBar ratingBar;
    private Menu menu;
    private ProgressBar progressBar;
    private MoviesViewModel movieViewModel;
    private TVShowsViewModel tvShowViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        progressBar = findViewById(R.id.progress_bar);
        textTitle = findViewById(R.id.tvTitleDetails);
        textDesc = findViewById(R.id.tvDesc);
        imagePoster = findViewById(R.id.imgDetails);
        textDate = findViewById(R.id.tvReleaseDate);
        textRating = findViewById(R.id.tvRatings);
        ratingBar = findViewById(R.id.ratingBar);

        movieViewModel = obtainViewModelMovies(this);
        tvShowViewModel = obtainViewModelTvShow(this);

        movieId = getIntent().getExtras().getInt(EXTRA_DETAILS_MOVIES);
        tvShowId = getIntent().getExtras().getInt(EXTRA_DETAILS_TVSHOW);

        progressBar.setVisibility(View.VISIBLE);

        if (movieId != 0) {
            movieViewModel.setMovieId(movieId);
            movieViewModel.detailMovies.observe(this, movies -> {
                if (movies != null) {
                    switch (movies.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            if (movies.data != null) {
                                progressBar.setVisibility(View.GONE);
                                textTitle.setText(movies.data.getTitle());
                                textDate.setText(movies.data.getReleaseDate());
                                textDesc.setText(movies.data.getOverview());
                                textRating.setText(String.valueOf(movies.data.getRating()));
                                ratingBar.setRating(Float.parseFloat(movies.data.getRating()));
                                GlideApp.with(getApplicationContext())
                                        .load(BuildConfig.BASE_URL_POSTER + movies.data.getPosterPath())
                                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                                        .into(imagePoster);
                            }
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                            break;
                    }

                }
            });
        } else if (tvShowId != 0) {
            tvShowViewModel.setTvShowId(tvShowId);
            tvShowViewModel.detailTvShow.observe(this, tvShow -> {
                if (tvShow != null) {

                    switch (tvShow.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            if (tvShow.data != null) {
                                progressBar.setVisibility(View.GONE);
                                textTitle.setText(tvShow.data.getTitle());
                                textDate.setText(tvShow.data.getReleaseDate());
                                textDesc.setText(tvShow.data.getOverview());
                                textRating.setText(String.valueOf(tvShow.data.getRating()));
                                ratingBar.setRating(Float.parseFloat(tvShow.data.getRating()));
                                GlideApp.with(getApplicationContext())
                                        .load(BuildConfig.BASE_URL_POSTER + tvShow.data.getPosterPath())
                                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                                        .into(imagePoster);
                            }
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                            break;
                    }

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fav_detail, menu);
        this.menu = menu;
        if (movieId != 0) {
            movieViewModel.detailMovies.observe(this, courseWithModule -> {
                if (courseWithModule != null) {
                    switch (courseWithModule.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            if (courseWithModule.data != null) {
                                progressBar.setVisibility(View.GONE);
                                boolean state = courseWithModule.data.isFavorite();
                                setFavoriteState(state);
                            }
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        } else if (tvShowId != 0) {
            tvShowViewModel.detailTvShow.observe(this, courseWithModule -> {
                if (courseWithModule != null) {
                    switch (courseWithModule.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            if (courseWithModule.data != null) {
                                progressBar.setVisibility(View.GONE);
                                boolean state = courseWithModule.data.isFavorite();
                                setFavoriteState(state);
                            }
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        } else if (item.getItemId() == R.id.action_bookmark) {
            if (movieId != 0) {
                movieViewModel.setMovieFavorite();
            } else if (tvShowId != 0) {
                tvShowViewModel.setTvShowFavorite();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFavoriteState(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_bookmark);
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border));
        }
    }

    @NonNull
    private static MoviesViewModel obtainViewModelMovies(AppCompatActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(MoviesViewModel.class);
    }

    @NonNull
    private static TVShowsViewModel obtainViewModelTvShow(AppCompatActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TVShowsViewModel.class);
    }
}
