package com.example.nysil.showtime;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

class QueryData {

    public static ArrayList<MoviesAndShowsData> getData(String url)
    {
        //creating a url instance from the url provided
        URL getURL=makeUrl(url);

        //Create a connection on url and get Json raw type from that
        String jsonResponse=null;
        jsonResponse=getJsonResponse(getURL);

        //extract the required data from the json which we fetched
        ArrayList<MoviesAndShowsData> arrayList=null;
        arrayList=getMoviesAndShowData(jsonResponse);


        return arrayList;
    }

    private static ArrayList<MoviesAndShowsData> getMoviesAndShowData(String jsonResponse) {
        if(jsonResponse==null)
        {
            return null;
        }
        else
        {
            // MoviesAndShowsData moviesAndShowsData = null;
            ArrayList<MoviesAndShowsData> arrayList=new ArrayList<>();
          //  ArrayList<MoviesAndShowsData> arrayListShows=new ArrayList<>();

            try
            {
                JSONObject jsonRootObject= new JSONObject(jsonResponse);
                JSONArray jsonArray=jsonRootObject.getJSONArray("results");

                //get data from each index because one movie details are present at one index
                for(int i=0; i<jsonArray.length(); i++)
                {
                    JSONObject jsonMovieAndShowDetailsObject= jsonArray.getJSONObject(i);

 //                   String movieOrShowName= jsonMovieAndShowDetailsObject.getString("name");
//                    if(movieOrShowName==null)
//                    {
//                        movieOrShowName=jsonMovieAndShowDetailsObject.getString("name");
//                    }
                    //String showName=jsonMovieAndShowDetailsObject.getString("name");
          //         String releaseDate= jsonMovieAndShowDetailsObject.getString("release_date");
                    String ratings= jsonMovieAndShowDetailsObject.getString("vote_average");
                    String infoAbout= jsonMovieAndShowDetailsObject.getString("overview");
                    String imagePoster= jsonMovieAndShowDetailsObject.getString("poster_path");
                    String imageBackCover= jsonMovieAndShowDetailsObject.getString("backdrop_path");
                    String fullLinkForPoster= "https://image.tmdb.org/t/p/w185"+imagePoster;
                    String fullBackCover="https://image.tmdb.org/t/p/w500"+imageBackCover;

                   MoviesAndShowsData moviesAndShowsData = new MoviesAndShowsData( ratings, infoAbout, fullLinkForPoster, fullBackCover);
                   // MoviesAndShowsData moviesAndShowsData = new MoviesAndShowsData(fullLinkForPoster);
                   arrayList.add(moviesAndShowsData);
                }

            }catch (JSONException e){e.printStackTrace();}


            return arrayList;
        }
    }

    //method to get json response from the url we just created
    private static String getJsonResponse(URL getURL) {
        String jsonFromUrl;

        if(getURL==null)
        {
            return null;
        }
        else
        {
          jsonFromUrl=null;
          InputStream inputStream=null;
          HttpURLConnection httpURLConnection=null;

          try
          {
              httpURLConnection =(HttpURLConnection)getURL.openConnection();
              httpURLConnection.setRequestMethod("GET");
              httpURLConnection.setConnectTimeout(25000);
              httpURLConnection.setReadTimeout(20000);
              httpURLConnection.connect();
              //connection has been sent to made

              //now check it successfully connected or not and proceed further
              if(httpURLConnection.getResponseCode()== httpURLConnection.HTTP_OK)
              {
                  inputStream=httpURLConnection.getInputStream();
                  jsonFromUrl=readFromInputStream(inputStream);
              }
              else
              {
                  Log.d("Error", "not connected");
              }
          }catch (IOException e)
          {
              System.out.println("Error during making connection and getting json respinse");
          }
        }

        return jsonFromUrl;

    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder getJson=new StringBuilder();

        if(inputStream==null)
            return null;

        else
        {
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader=new BufferedReader((inputStreamReader));
            String buffer= bufferedReader.readLine();

            while(buffer != null)
            {
                getJson.append(buffer);
                buffer=bufferedReader.readLine();
            }
        }
        return getJson.toString();

    }


    //method to create the URl from the given url string provided
    private static URL makeUrl(String url) {
        if(url==null)
        {
            return null;
        }
        else
        {
            URL makeUrl=null;
            try
            {
                makeUrl=new URL(url);
            }catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            return makeUrl;
        }
    }

}
