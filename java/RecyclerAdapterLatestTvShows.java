package com.example.nysil.showtime;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterLatestTvShows extends RecyclerView.Adapter<RecyclerAdapterLatestTvShows.DataViewHolder>
{
    private ArrayList<MoviesAndShowsData> arrayList;
    private Context context;

    RecyclerAdapterLatestTvShows(ArrayList<MoviesAndShowsData> arrayList, Context context)
    {
        this.arrayList=arrayList;
        this.context=context;
    }


    @Override
    public RecyclerAdapterLatestTvShows.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        DataViewHolder dataViewHolder=new DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterLatestTvShows.DataViewHolder holder, final int position) {
        final MoviesAndShowsData moviesAndShowsData =arrayList.get(position);

        final String posterImage= moviesAndShowsData.getImageUrl();

        //Picasso.with(context).load(posterImage).into(holder.imageView);
        Picasso.with(context).load(posterImage).fit().into(holder.imageView);


        holder.imageView.setOnClickListener(new View.OnClickListener()
                                            {
                                                @Override
                                                public void onClick(View view)
                                                {
                                                    MoviesAndShowsData currentShowMovie=arrayList.get(position);

                                                    String overview = currentShowMovie.getInfoAbout();
                                                    String posterImage=currentShowMovie.getImageUrl();
                                                    String coverPic=currentShowMovie.getCoverImageUrl();
                                                    String rating=currentShowMovie.getRating();

                                                    Intent intent=new Intent(view.getContext(), Details.class);
                                                    intent.putExtra("overview", overview);
                                                    intent.putExtra("posterImage", posterImage);
                                                    intent.putExtra("backImage", coverPic);
                                                    intent.putExtra("rating", rating);

                                                    context.startActivity(intent);

                                                }
                                            }


        );
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class DataViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;

        public DataViewHolder(View itemView) {
            super(itemView);

            imageView=(ImageView)itemView.findViewById(R.id.imageViewForMoviesAndShows);
        }
    }
}
