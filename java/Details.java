package com.example.nysil.showtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by nysil on 11-03-2018.
 */

public class Details extends AppCompatActivity {

    private String imageUrl;
    private String coverPic;
    private String overview;
    private String rating;
    private ImageView coverImage, posterImage;
    private TextView textview, ratingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        Intent intent = getIntent();
        imageUrl= intent.getStringExtra("posterImage");
        coverPic=intent.getStringExtra("backImage");
        overview=intent.getStringExtra("overview");
        rating=intent.getStringExtra("rating");


        coverImage=(ImageView)findViewById(R.id.coverPic);
        posterImage=(ImageView)findViewById(R.id.posterImage);

        ratingTextView=(TextView)findViewById(R.id.ratingTextView);
        textview=(TextView)findViewById(R.id.overViewTextView);


        Picasso.with(Details.this).load(imageUrl).into(posterImage);
        Picasso.with(Details.this).load(coverPic).into(coverImage);

        textview.setText(overview);
        ratingTextView.setText(rating);

    }
}
