package com.example.nysil.showtime;


import android.content.Context;

import java.util.ArrayList;

class MoviesAndShowLoader extends android.support.v4.content.AsyncTaskLoader<ArrayList<MoviesAndShowsData>> {
    private String URL;
    public MoviesAndShowLoader(Context context, String URL) {
        super(context);
        this.URL=URL;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<MoviesAndShowsData> loadInBackground() {
        ArrayList<MoviesAndShowsData> arrayList= QueryData.getData(URL);
        return arrayList;
    }
}
