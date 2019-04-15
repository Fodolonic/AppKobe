package com.example.guineaapp;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class ResultMovie {
    //Variables declaration movies return
    Integer vote_count;
    Integer id;
    String title;
    String poster_path;
    String overview;
    String release_date;
    String backdrop_path;
    List <ResultGenres> genres;
    Bitmap bmPoster;
    Bitmap bmBackdrop;


    public Bitmap getBmPoster() {
        return bmPoster;
    }

    public void setBmPoster(Bitmap bmPoster) {
        this.bmPoster = bmPoster;
    }



    public ResultMovie(Integer Id, String Title, String Poster_Path, String Overview, String Release_Date, String Backdrop_image) {

        this.id = Id;
        this.title = Title;
        this.poster_path = Poster_Path;
        this.overview = Overview;
        this.release_date = Release_Date;
        this.backdrop_path = Backdrop_image;

    }

    /**USE ONLY IN ASYNC!!1111eleven */
    public void updateBitmaps(){
        bmPoster = loadBitmap(getPosterPath());
        bmBackdrop = loadBitmap(getBackdropPath());

    }
    //
    public String toString(){
        return "Movie: "+title;
    }

    public Integer getId(){
        return this.id;

    }

    public String getName(){
        return title;
    }

    public String getGenres(){
        return "Genres: "+this.genres.toString();
    }


    @Nullable
    public String getBackdropPath() {
        if (backdrop_path != null && !backdrop_path.isEmpty()) {

            if (!backdrop_path.toLowerCase().contains("http://")) {
                return "http://image.tmdb.org/t/p/w342" + backdrop_path;
            } else {
                return backdrop_path;
            }
        }   return null;
    }

    //Get poster for image_list
    @Nullable
    public String getPosterPath() {
        if (poster_path != null && !poster_path.isEmpty()) {

            if (!poster_path.toLowerCase().contains("http://")) {
                return "http://image.tmdb.org/t/p/w342" + poster_path;
            } else {
                return poster_path;
            }
        }   return null;
    }

    public String getDateText(){
        return "Release Date: "+release_date;
    }

    public Bitmap loadBitmap(String url)
    {
        Bitmap bm = null;
        if(url=="" || url==null)return bm;
        InputStream is = null;
        BufferedInputStream bis = null;
        try
        {
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 8192);
            bm = BitmapFactory.decodeStream(bis);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (bis != null)
            {
                try
                {
                    bis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return bm;
    }
}

