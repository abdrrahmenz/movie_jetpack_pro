package com.abdurrahman.movies.data.source.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies")
public class MoviesEntity {

    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    private String rating;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String releaseDate;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overview;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String posterPath;

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int idMovie;

    @ColumnInfo(name = "favorite")
    private boolean favorite = false;

    public MoviesEntity() {
    }

    public MoviesEntity(int idMovie, String rating, String title, String dateRelease, String overview, String posterPath, Boolean favorite) {
        this.idMovie = idMovie;
        this.rating = rating;
        this.title = title;
        this.releaseDate = dateRelease;
        this.overview = overview;
        this.posterPath = posterPath;
        if (favorite != null) {
            this.favorite = favorite;
        }
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateRelease(String dateRelease) {
        this.releaseDate = dateRelease;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
