package com.example.guineaapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.GridView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


import javax.net.ssl.HttpsURLConnection;

//AsyncTask que busca listview de todos upcoming movies.

public class AsyncListLoad extends AsyncTask<ResultMovie,Void, List<ResultMovie>> {
    private final Context mContext;
    //@BindView (R.id.lista)
    GridView listmovie;

    String JsonTest = "";
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    Integer pageNum;
    private Integer total_pages;

    public AsyncListLoad(final Context mContext,View viewprincipal,Integer PageNum){
        if(PageNum == 0 || PageNum == null){ this.pageNum = 1;}else{this.pageNum = PageNum;}
        this.mContext = mContext;
        listmovie = (GridView) viewprincipal;
        listmovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(mContext,MoviesOverview.class);

                intent.putExtra("movieId","aa");
                intent.putExtra("itemId",""+((ResultMovie)parent.getItemAtPosition(position)).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    protected List<ResultMovie> doInBackground(ResultMovie... voids) {
        try {
            URL githubendpoint = new URL("https://api.themoviedb.org/3/movie/upcoming?api_key=c5850ed73901b8d268d0898a8a9d8bff&language=en-US&page="+pageNum);
            //cria conexão
            HttpsURLConnection myConnection = (HttpsURLConnection) githubendpoint.openConnection();
            InputStream responseBody = myConnection.getInputStream();
            InputStreamReader responsebodyreaReader = new InputStreamReader(responseBody, "UTF-8");
            JsonReader jsonreader = new JsonReader(responsebodyreaReader);
            Scanner scanner = new Scanner(responseBody,"UTF-8");
            JsonTest = scanner.useDelimiter("\\A").next();
            Log.d("JSON",JsonTest);
        } catch (Exception e) {
            Log.d("Erro",e.getMessage());
        }
        ListResult results;
        results = gson.fromJson(JsonTest, ListResult.class);
        total_pages = results.getTotalPages();
        results.updateBitmap();
        return results.results;
    }

    protected void onPostExecute( List<ResultMovie> s){
        super.onPostExecute(s);
        MovieAdapter adapter;
        if(listmovie.getAdapter() == null){
            adapter = new MovieAdapter(s,mContext);
            listmovie.setAdapter(adapter);

        }else{
            adapter = ((MovieAdapter)listmovie.getAdapter());
            adapter.appendList(s);
            adapter.notifyDataSetChanged();
        }

        ((MainActivity)mContext).setTotalPages(total_pages);


    }

}
