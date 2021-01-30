package com.example.moviex.activities;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviex.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MoviesActivity extends AppCompatActivity {


    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // action gizle
        getSupportActionBar().hide();

        // Data okuma

        mediaController = new MediaController(this);

        String name  = getIntent().getExtras().getString("anime_name");
        String description = getIntent().getExtras().getString("anime_description");
        String studio = getIntent().getExtras().getString("anime_studio");
        String category = getIntent().getExtras().getString("anime_category");
        int nb_episode = getIntent().getExtras().getInt("anime_nb_episode");
        String rating = getIntent().getExtras().getString("anime_rating");
        String image_url = getIntent().getExtras().getString("anime_img");
        String video_url = getIntent().getExtras().getString("mov_url");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        // Detaylar bölümü
        TextView tv_name = findViewById(R.id.aa_anime_name);
        TextView tv_studio = findViewById(R.id.aa_studio);
        TextView tv_categorie = findViewById(R.id.aa_categorie) ;
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating  = findViewById(R.id.aa_rating);
        ImageView img = findViewById(R.id.aa_thumbnail);
        VideoView moveVideos = findViewById(R.id.VideoViews);
        Glide.with(this).asBitmap().load(video_url).into(img);

        //Set işlemleri
        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(studio);
        Uri kaynak = Uri.parse(video_url);
        moveVideos.setVideoURI(kaynak);
        moveVideos.start();
        moveVideos.setMediaController(mediaController);

        collapsingToolbarLayout.setTitle(name);


        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // set image using Glide
        //Glide.with(this).load(image_url).apply(requestOptions).into(img);

    }


}