package com.bilgeadam.edu.vkfmobil.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilgeadam.edu.vkfmobil.R;
import com.bilgeadam.edu.vkfmobil.common.LoadHttpImage;
import com.bilgeadam.edu.vkfmobil.model.TiviBuModel;

import java.util.List;

/**
 * Created by umutboz on 26,January,2018
 */

public class TopFilmsRecyclerAdapter extends RecyclerView.Adapter<TopFilmsRecyclerAdapter.TopFilmsViewHolder> {


    Context context;
    List<TiviBuModel> films;
    LayoutInflater inflater;

    public  TopFilmsRecyclerAdapter(List<TiviBuModel> allFilms, Context mContex)
    {
        this.films = allFilms;
        inflater = LayoutInflater.from(mContex);
        this.context = mContex;
    }
    @Override
    public TopFilmsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.top_films_list_item,parent,false);
        TopFilmsViewHolder viewHolder = new TopFilmsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TopFilmsViewHolder holder, int position) {

        TiviBuModel tiviBuModel = films.get(position);
        holder.setData(tiviBuModel);

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    class TopFilmsViewHolder extends RecyclerView.ViewHolder {

        ImageView posterImg;
        TextView titleTv, subTitleTv;
        public TopFilmsViewHolder(View view)
        {
            super(view);

            posterImg = (ImageView)view.findViewById(R.id.top_films_list_item_poster_img);
            titleTv = (TextView)view.findViewById(R.id.top_films_list_item_title_tv);
            subTitleTv = (TextView)view.findViewById(R.id.top_films_list_item_subtitle_tv);
        }

        public void setData(TiviBuModel data)
        {
            titleTv.setText(data.getTitle());
            subTitleTv.setText(data.getDescription());

            new LoadHttpImage(this.posterImg).execute(data.getPosterUrl());


        }
    }

}
