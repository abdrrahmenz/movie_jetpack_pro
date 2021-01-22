package com.abdurrahman.movies.ui.movies;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.abdurrahman.movies.R;
import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.ui.detail.DetailMovieActivity;
import com.abdurrahman.movies.BuildConfig;
import com.abdurrahman.movies.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private final Activity activity;
    private final List<MoviesEntity> mMovies = new ArrayList<>();

    MoviesAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<MoviesEntity> getListMovies() {
        return mMovies;
    }

    void setListMovies(List<MoviesEntity> listMovies) {
        if (listMovies == null) return;
        this.mMovies.clear();
        this.mMovies.addAll(listMovies);
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoviesViewHolder holder, int position) {
        holder.tvTitle.setText(getListMovies().get(position).getTitle());
        holder.tvDescription.setText(getListMovies().get(position).getOverview());
        holder.tvDate.setText(String.format("Release %s", getListMovies().get(position).getReleaseDate()));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_DETAILS_MOVIES, getListMovies().get(position).getIdMovie());
            activity.startActivity(intent);
        });

        GlideApp.with(holder.itemView.getContext())
                .load(BuildConfig.BASE_URL_POSTER+getListMovies().get(position).getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListMovies().size();
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        MoviesViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
        }
    }
}