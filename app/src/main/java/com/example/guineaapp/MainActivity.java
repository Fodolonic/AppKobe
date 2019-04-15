package com.example.guineaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.AbsListView;
import android.widget.GridView;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    Integer numPage = 1;
    Integer totalPages = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AsyncListLoad Loadasync = new AsyncListLoad(this,findViewById(R.id.lista) ,numPage);
        Loadasync.execute();

        ((GridView)findViewById(R.id.lista)).setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                if(firstVisibleItem + visibleItemCount >= totalItemCount/2 && numPage<totalPages){

                    numPage++;
                    AsyncListLoad Loadasync = new AsyncListLoad(view.getContext(),findViewById(R.id.lista) ,numPage);
                    Loadasync.execute();
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState){

            }
        });


    }

    public void Gotomoviesoverview(View view){

        Intent intentl = new Intent(getApplicationContext(),MoviesOverview.class);
        startActivity(intentl);

    }

    public void setTotalPages(Integer totalPages){
        this.totalPages = totalPages;
    }
}
