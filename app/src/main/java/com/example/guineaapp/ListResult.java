package com.example.guineaapp;

import java.util.List;

public class ListResult {
    List<ResultMovie> results;
    private Integer total_pages;

    /**USE ONLY IN ASYNC!!1111eleven */
    public void updateBitmap(){

        for (ResultMovie temp : results){
            if(temp!=null){
                temp.updateBitmaps();
            }

        }
    }

    public Integer getTotalPages(){
        return total_pages;
    }
}
