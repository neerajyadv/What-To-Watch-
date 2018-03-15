package com.example.nysil.showtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by nysil on 11-03-2018.
 */

public class ViewMoreMovies extends AppCompatActivity implements  LoaderManager.LoaderCallbacks<ArrayList<MoviesAndShowsData>> {

    String movieUrl;
    private RecyclerAdapterViewMoreMovies recyclerAdapterViewMoreMovies;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewmorerecyclerview);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerviewViewMore);

        Intent intent=getIntent();
        movieUrl=intent.getStringExtra("urlForShowsOrMovie");

        getSupportLoaderManager().initLoader(0,null, this);

    }
    @Override
    public Loader<ArrayList<MoviesAndShowsData>> onCreateLoader(int id, Bundle args) {
        MoviesAndShowLoader moviesAndShowLoader=new MoviesAndShowLoader(this, movieUrl);
        return moviesAndShowLoader;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MoviesAndShowsData>> loader, ArrayList<MoviesAndShowsData> data) {
        recyclerAdapterViewMoreMovies = new RecyclerAdapterViewMoreMovies(data, this);
        recyclerView.setAdapter(recyclerAdapterViewMoreMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MoviesAndShowsData>> loader) {
        loader.reset();

    }
}
