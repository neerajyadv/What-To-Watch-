package com.example.nysil.showtime;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;

/**
 * Created by nysil on 11-03-2018.
 */

public class RecyclerAdapterViewMoreMovies extends RecyclerView.Adapter<RecyclerAdapterViewMoreMovies.DataViewHolder>
{
    private ArrayList<MoviesAndShowsData> arrayList;
    private Context context;

    RecyclerAdapterViewMoreMovies(ArrayList<MoviesAndShowsData> arrayList, Context context)
    {
        this.arrayList=arrayList;
        this.context=context;
    }


    @Override
    public RecyclerAdapterViewMoreMovies.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_more, parent, false);
        DataViewHolder dataViewHolder=new DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterViewMoreMovies.DataViewHolder holder, final int position) {
        final MoviesAndShowsData moviesAndShowsData =arrayList.get(position);
        final String posterImage= moviesAndShowsData.getImageUrl();

        String infoAbout=moviesAndShowsData.getInfoAbout();

        holder.textView.setText(infoAbout);

        //Picasso.with(context).load(posterImage).into(holder.imageView);
        Picasso.with(context).load(posterImage).fit().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class DataViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;

        public DataViewHolder(View itemView) {
            super(itemView);

            imageView=(ImageView)itemView.findViewById(R.id.imageViewForViewMore);
            textView=(TextView)itemView.findViewById(R.id.textViewForMore);
        }
    }
}
