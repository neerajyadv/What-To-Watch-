package com.example.nysil.showtime;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MoviesAndShowsData>> {

    private RecyclerAdapterLatestMovies recyclerAdapterLatestMovies;
    private RecyclerAdapterPopularMovies recyclerAdapterPopularMovies;
    private RecyclerAdapterPopularTvShows recyclerAdapterPopularTvShows;
    private RecyclerAdapterLatestTvShows recyclerAdapterLatestTvShows;
    private RecyclerAdapterUpcomingMovies recyclerAdapterUpcomingMovies;

    private RecyclerView recyclerViewLatestMovies, recyclerViewPopularMovies, recyclerViewLatestShows, recyclerViewPopularShows, recyclerViewUpcomingMovies;

    private TextView textviewLatestMovies;
    private TextView textviewPopularMovies;
    private TextView textviewLatestTv;
    private TextView textviewPopularTv;

    MoviesAndShowLoader moviesAndShowsLoader = null;

    ConnectivityManager cm;








    private String URL_POPULAR_MOVIES="https://api.themoviedb.org/3/discover/movie?api_key=136ce8cf592cef45be32bb73474ec096&sort_by=popularity.desc&language=en-US";
    private String URL_LATEST_MOVIES="https://api.themoviedb.org/3/discover/movie?api_key=136ce8cf592cef45be32bb73474ec096&primary_release_date.gte=2018-03-01&primary_release_date.lte=2018-03-10";
    private String URL_LATEST_SHOWS="https://api.themoviedb.org/3/discover/tv?api_key=136ce8cf592cef45be32bb73474ec096&language=en-US&first_air_date_year=2018";
    private String URL_POPULAR_SHOWS="https://api.themoviedb.org/3/discover/tv?api_key=136ce8cf592cef45be32bb73474ec096&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
    private String URL_UPCOMING_MOVIES="https://api.themoviedb.org/3/movie/upcoming?api_key=136ce8cf592cef45be32bb73474ec096";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected) {
            //initialize loader in background
            getSupportLoaderManager().initLoader(0,null, this);
            getSupportLoaderManager().initLoader(1,null, this);
            getSupportLoaderManager().initLoader(2,null,this);
            getSupportLoaderManager().initLoader(3,null,this);
            getSupportLoaderManager().initLoader(4,null,this);

        }
        else
        {
            Log.d("Error No Connection", "No Internet Connection");
        }


        //initialize recyclerview
        recyclerViewLatestMovies=(RecyclerView)findViewById(R.id.recyclerViewLatestMovies);
        recyclerViewPopularMovies=(RecyclerView)findViewById(R.id.recyclerViewPopularMovies);
        recyclerViewPopularShows=(RecyclerView)findViewById(R.id.recyclerViewPopularShows);
        recyclerViewLatestShows=(RecyclerView)findViewById(R.id.recyclerViewLatestShows);
        recyclerViewUpcomingMovies=(RecyclerView)findViewById(R.id.recyclerViewUpcomingMovies);



        //initialize textviews
        textviewLatestMovies=(TextView)findViewById(R.id.latestMovieTextView);
        textviewPopularMovies=(TextView)findViewById(R.id.popularMovieTextView);
        textviewLatestTv=(TextView)findViewById(R.id.latestTvTextView);
        textviewPopularTv=(TextView)findViewById(R.id.popularTvTextView);


        textviewLatestMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this , ViewMoreMovies.class);
                intent.putExtra("urlForShowsOrMovie", URL_LATEST_MOVIES);
                startActivity(intent);
            }
        });

        textviewPopularMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this , ViewMoreMovies.class);
                intent.putExtra("urlForShowsOrMovie", URL_POPULAR_MOVIES);
                startActivity(intent);
            }
        });

        textviewLatestTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this , ViewMoreMovies.class);
                intent.putExtra("urlForShowsOrMovie", URL_LATEST_SHOWS);
                startActivity(intent);
            }
        });

        textviewPopularTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this , ViewMoreMovies.class);
                intent.putExtra("urlForShowsOrMovie", URL_POPULAR_SHOWS);
                startActivity(intent);
            }
        });

    }




    @Override
    public Loader<ArrayList<MoviesAndShowsData>> onCreateLoader(int id, Bundle args) {
        if (id == 0) {
            moviesAndShowsLoader = new MoviesAndShowLoader(this, URL_LATEST_MOVIES);
        }
         if(id==1)
        {
            moviesAndShowsLoader=new MoviesAndShowLoader(this, URL_POPULAR_MOVIES);
        }
        if(id==2)
        {
            moviesAndShowsLoader=new MoviesAndShowLoader(this, URL_LATEST_SHOWS);
        }
        if(id==3)
        {
            moviesAndShowsLoader=new MoviesAndShowLoader(this, URL_POPULAR_SHOWS);
        }
        if(id==4)
        {
            moviesAndShowsLoader=new MoviesAndShowLoader(this, URL_UPCOMING_MOVIES);
        }
        return moviesAndShowsLoader;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MoviesAndShowsData>> loader, ArrayList<MoviesAndShowsData> data) {

        if(loader.getId()==0) {
            recyclerAdapterLatestMovies = new RecyclerAdapterLatestMovies(data, this);
            recyclerViewLatestMovies.setAdapter(recyclerAdapterLatestMovies);
            recyclerViewLatestMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        }
         if(loader.getId()==1)
        {
            recyclerAdapterPopularMovies =new RecyclerAdapterPopularMovies(data, this);
            recyclerViewPopularMovies.setAdapter(recyclerAdapterPopularMovies);
            recyclerViewPopularMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        }

        if(loader.getId()==2)
        {
            recyclerAdapterLatestTvShows =new RecyclerAdapterLatestTvShows(data, this);
            recyclerViewLatestShows.setAdapter(recyclerAdapterLatestTvShows);
            recyclerViewLatestShows.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        }

        if(loader.getId()==3)
        {
            recyclerAdapterPopularTvShows =new RecyclerAdapterPopularTvShows(data, this);
            recyclerViewPopularShows.setAdapter(recyclerAdapterPopularTvShows);
            recyclerViewPopularShows.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        }
        if(loader.getId()==4)
        {
            recyclerAdapterUpcomingMovies=new RecyclerAdapterUpcomingMovies(data, this);
            recyclerViewUpcomingMovies.setAdapter(recyclerAdapterUpcomingMovies);
            recyclerViewUpcomingMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MoviesAndShowsData>> loader) {
        if(loader.getId()==0)
        loader.reset();

        if(loader.getId()==1)
            loader.reset();

        if(loader.getId()==2)
            loader.reset();

        if(loader.getId()==3)
            loader.reset();
        if(loader.getId()==4)
            loader.reset();
    }
}
