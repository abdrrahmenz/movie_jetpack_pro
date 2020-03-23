package com.abdurrahman.movies.ui.tvshows;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.abdurrahman.movies.BuildConfig;
import com.abdurrahman.movies.R;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;
import com.abdurrahman.movies.ui.detail.DetailMovieActivity;
import com.abdurrahman.movies.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import java.util.List;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder> {

    private final Activity activity;
    private List<TVShowEntity> mTvShows = new ArrayList<>();

    TvShowsAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<TVShowEntity> getListTvShows() {
        return mTvShows;
    }

    void setListTvShows(List<TVShowEntity> listTvShows) {
        if (listTvShows == null) return;
        this.mTvShows.clear();
        this.mTvShows.addAll(listTvShows);
    }

    @NonNull
    @Override
    public TvShowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list, parent, false);
        return new TvShowsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvShowsViewHolder holder, int position) {
        holder.tvTitle.setText(getListTvShows().get(position).getTitle());
        holder.tvDescription.setText(getListTvShows().get(position).getOverview());
        holder.tvDate.setText(String.format("Release %s", getListTvShows().get(position).getReleaseDate()));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_DETAILS_TVSHOW, getListTvShows().get(position).getTvShowId());
            activity.startActivity(intent);
        });

        GlideApp.with(holder.itemView.getContext())
                .load(BuildConfig.BASE_URL_POSTER+getListTvShows().get(position).getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListTvShows().size();
    }

    class TvShowsViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        TvShowsViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
        }
    }
}