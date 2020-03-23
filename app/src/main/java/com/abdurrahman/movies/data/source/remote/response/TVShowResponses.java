package com.abdurrahman.movies.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TVShowResponses implements Parcelable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("overview")
    private String overview;

    public TVShowResponses() {
    }

    public TVShowResponses(Integer id, String name, Double voteAverage, String firstAirDate, String posterPath, String overview) {
        this.id = id;
        this.name = name;
        this.voteAverage = voteAverage;
        this.firstAirDate = firstAirDate;
        this.posterPath = posterPath;
        this.overview = overview;
    }

    protected TVShowResponses(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readDouble();
        }
        firstAirDate = in.readString();
        posterPath = in.readString();
        overview = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        if (voteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(voteAverage);
        }
        dest.writeString(firstAirDate);
        dest.writeString(posterPath);
        dest.writeString(overview);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TVShowResponses> CREATOR = new Creator<TVShowResponses>() {
        @Override
        public TVShowResponses createFromParcel(Parcel in) {
            return new TVShowResponses(in);
        }

        @Override
        public TVShowResponses[] newArray(int size) {
            return new TVShowResponses[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
