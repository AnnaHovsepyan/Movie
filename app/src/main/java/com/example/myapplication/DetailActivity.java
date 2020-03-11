package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView movieTitle, movieOverview,movieDataRelease,voteAverage;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        movieTitle = findViewById(R.id.tv_title);
        movieOverview = findViewById(R.id.tv_overview);
        movieDataRelease=findViewById(R.id.tv_release_date);
        voteAverage=findViewById(R.id.tv_vote);
        img = findViewById(R.id.img_poster);
        String title = getIntent().getExtras().getString("title");
        String overview = getIntent().getExtras().getString("overview");
        String dataRelease = getIntent().getExtras().getString("data_release");
        String path=getIntent().getExtras().getString("image");
        Double vote_average=getIntent().getExtras().getDouble("vote_average");
        double userRating = vote_average / 2;
        int integerPart = (int) userRating;
        RatingBar ratingBar = findViewById(R.id.rating);
        ratingBar.setRating(integerPart);



        movieTitle.setText(title);
        movieOverview.setText(overview);
        movieDataRelease.setText(dataRelease);
        voteAverage.setText(String.valueOf(vote_average));
        String poster = "https://image.tmdb.org/t/p/w500" + path;

        Picasso.get().load(poster).into(img);




    }
}


