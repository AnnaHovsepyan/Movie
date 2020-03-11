package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class MoviesModel implements Serializable {

    @SerializedName("poster_path")

    private String posterPath;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("image")
    private String image;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_count")
    private String voteCount;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("id")
    private int id;


    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
    @Override
    public String toString() {
        return
                "ItemResult{" +
                        "overview = '" + overview + '\'' +
                        ",title = '" + title + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",release_date = '" + releaseDate + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",vote_count = '" + voteCount + '\'' +
                        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoviesModel that = (MoviesModel) o;

        if (Double.compare(that.voteAverage, voteAverage) != 0) return false;
        if (id != that.id) return false;
        if (posterPath != null ? !posterPath.equals(that.posterPath) : that.posterPath != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (overview != null ? !overview.equals(that.overview) : that.overview != null)
            return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null)
            return false;
        return voteCount != null ? voteCount.equals(that.voteCount) : that.voteCount == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = posterPath != null ? posterPath.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (overview != null ? overview.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (voteCount != null ? voteCount.hashCode() : 0);
        temp = Double.doubleToLongBits(voteAverage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + id;
        return result;
    }
}
