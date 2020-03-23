package com.abdurrahman.movies.ui.favorite.tvshows;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.abdurrahman.movies.BuildConfig;
import com.abdurrahman.movies.R;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;
import com.abdurrahman.movies.ui.detail.DetailMovieActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class FavoriteTVShowAdapter extends PagedListAdapter<TVShowEntity, FavoriteTVShowAdapter.FavMovieViewHolder> {

    private final Activity activity;

    public FavoriteTVShowAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    private static DiffUtil.ItemCallback<TVShowEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TVShowEntity>() {
                @Override
                public boolean areItemsTheSame(TVShowEntity oldMovie, TVShowEntity newMovie) {
                    return oldMovie.getTitle().equals(newMovie.getTitle());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(TVShowEntity oldMovie, @NonNull TVShowEntity newMovie) {
                    return oldMovie.equals(newMovie);
                }
            };

    public TVShowEntity getItemById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    @NonNull
    @Override
    public FavMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list, parent, false);
        return new FavMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMovieViewHolder holder, int position) {
        TVShowEntity data = getItem(position);
        if (data != null) {
            holder.tvTitle.setText(data.getTitle());
            holder.tvDescription.setText(data.getOverview());
            holder.tvDate.setText(String.format("Release %s",data.getReleaseDate()));
            Glide.with(holder.itemView.getContext())
                    .load(BuildConfig.BASE_URL_POSTER + data.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                            .error(R.drawable.ic_error)
                    .into(holder.imgPoster);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_DETAILS_TVSHOW, data.getTvShowId());
                activity.startActivity(intent);
            });
        }


    }

    class FavMovieViewHolder extends RecyclerView.ViewHolder {

        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        FavMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
        }
    }
}