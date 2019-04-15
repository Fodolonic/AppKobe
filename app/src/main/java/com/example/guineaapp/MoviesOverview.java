package com.example.guineaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;



public class MoviesOverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_overview);
        Intent intent = getIntent();
        Log.d("EEEEEEEEEE",intent.getStringExtra("movieId") );
        Log.d("PARENT",""+intent.getStringExtra("itemId") );

        TextView textView = (TextView) findViewById(R.id.Name);
        TextView textRelease = (TextView) findViewById(R.id.Release);
        TextView textOverview = (TextView) findViewById(R.id.Overview);
        TextView textGenre = (TextView) findViewById(R.id.Genre);
        textView.setText(intent.getStringExtra("itemId"));



        AsyncListMovie LoadDetail = new AsyncListMovie(this,findViewById(R.id.Name),findViewById(R.id.Release),findViewById(R.id.Overview),findViewById(R.id.Genre),findViewById(R.id.movie_poster),findViewById(R.id.movie_backdrop),intent.getStringExtra("itemId"));

        LoadDetail.execute();
    }


}
