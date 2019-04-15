package com.example.guineaapp;
import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

//AsyncTask que retornará valores especificos de cada filme


public class AsyncListMovie extends AsyncTask<String,Void, ResultMovie> {

    Context mContext;
    View varName;
    View varOverview;
    View varRelease;
    View varGenre;
    View varImagePoster;
    View varImageBackdrop;
    String movieId;
    String JsonResult;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    public AsyncListMovie(final Context mContext,View inPrincipal,View inRelease,View inOverview,View inGenre, View inPoster,View inBackdrop,String movieId){
        this.mContext = mContext;
        this.movieId = movieId;
        this.varName = inPrincipal;
        this.varOverview = inOverview;
        this.varRelease = inRelease;
        this.varGenre = inGenre;
        this.varImagePoster = inPoster;
        this.varImageBackdrop = inBackdrop;
    }


    @Override
    protected ResultMovie doInBackground(String... voids) {
        try {
            URL githubendpoint = new URL("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=c5850ed73901b8d268d0898a8a9d8bff&language=en-US&page=1");
            //create connection
            HttpsURLConnection myConnection = (HttpsURLConnection) githubendpoint.openConnection();
            InputStream responseBody = myConnection.getInputStream();
            InputStreamReader responsebodyreaReader = new InputStreamReader(responseBody, "UTF-8");
            JsonReader jsonreader = new JsonReader(responsebodyreaReader);
            Scanner scanner = new Scanner(responseBody,"UTF-8");
            JsonResult = scanner.useDelimiter("\\A").next();
            Log.d("JSON",JsonResult);
        } catch (Exception e) {
            Log.d("Erro",e.getMessage());
        }
        ResultMovie moviedetail;
        moviedetail = gson.fromJson(JsonResult, ResultMovie.class);
        moviedetail.updateBitmaps();
        //ListView listmovie;
        return moviedetail;
    }

    protected void onPostExecute( ResultMovie s){
        super.onPostExecute(s);
        Log.d ("ERRÃO", "1");
        TextView textView = (TextView) varName;
        TextView textviewrelease = (TextView)varRelease;
        TextView textviewover = (TextView)varOverview;
        TextView textviewgenre = (TextView)varGenre;
        ImageView imageViewPoster = (ImageView) varImagePoster;
        ImageView imageViewBackdrop = (ImageView) varImageBackdrop;

        textView.setText("Title: "+(s.getName()));
        textviewrelease.setText(s.getDateText());
        textviewover.setText(s.overview);
        textviewgenre.setText(s.getGenres());
        imageViewPoster.setImageBitmap(s.bmPoster);
        imageViewBackdrop.setImageBitmap(s.bmBackdrop);

    }

}
