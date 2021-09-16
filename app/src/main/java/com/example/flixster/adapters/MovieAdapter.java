package com.example.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.Log;
//import android.support.v4.util.Pair;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster.MainActivity;
import com.example.flixster.R;
import com.example.flixster.detailView.DetailActivity;
import com.example.flixster.models.Movie;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.example.flixster.MainActivity.TAG;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;
    ImageView youtubePlayIcon;


    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // This method will inflate the layout and will return the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.irem_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // This method will populate data into items through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position );
        Movie movie = movies.get(position);
        if(movie.getRating() > 5.0){
            youtubePlayIcon.setVisibility(View.VISIBLE);
        }
        else{
            youtubePlayIcon.setVisibility(View.INVISIBLE);
        }
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
            youtubePlayIcon = itemView.findViewById(R.id.playButton);

        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;

            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackDropPath();

            }
            else{
                imageUrl = movie.getPosterPath();
            }
            int radius = 40;
            int margin = 10;
            Glide.with(context).load(imageUrl).fitCenter().transform(new RoundedCornersTransformation(radius, margin)).into(ivPoster);

            // Want to click on the whole container
            // open new activity

            container.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    Log.d(TAG, "onClick: Inside onClickListener");

                    Intent intent = new Intent(context, com.example.flixster.detailView.DetailActivity.class);

                    intent.putExtra("movie", Parcels.wrap(movie));

                    Log.d(TAG, "onClick: before starting activity");

//                    Pair<View, String> p1 = Pair.create((View)tvTitle, "movieTitle");
//                    Pair<View, String> p2 = Pair.create((View)tvOverview, "movieOverview");

//                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, p1, p2);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,(View)tvTitle, "movieTitle");
                    context.startActivity(intent, options.toBundle());
                    Log.d(TAG, "onClick: after starting activity");

                    //Toast.makeText(context, container.getId(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
