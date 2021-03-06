package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    String posterPath;
    String title;
    String overview;
    String backDropPath;
    int movieID;
    double rating;

    public Movie(){
    }

    Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backDropPath = jsonObject.getString("backdrop_path");
        rating = jsonObject.getDouble("vote_average");
        movieID = jsonObject.getInt("id");

    }

    public static List<Movie> fromjsonArray(JSONArray moviejsonArray) throws JSONException {

        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < moviejsonArray.length(); i++) {
            movies.add(new Movie(moviejsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackDropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", backDropPath);
    }

    public double getRating() {
        return rating;
    }

    public int getMovieID() {
        return movieID;
    }
}
