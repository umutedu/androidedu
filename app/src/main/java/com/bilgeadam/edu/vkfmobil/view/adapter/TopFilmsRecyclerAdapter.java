package com.bilgeadam.edu.vkfmobil.view.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilgeadam.edu.vkfmobil.R;
import com.bilgeadam.edu.vkfmobil.common.LoadHttpImage;
import com.bilgeadam.edu.vkfmobil.model.TiviBuModel;
import com.bilgeadam.edu.vkfmobil.view.activity.FilmDetailActivity;
import com.bilgeadam.edu.vkfmobil.view.activity.MainActivity;
import com.squareup.picasso.Picasso;

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

        TiviBuModel tiviBuModel;
        ImageView posterImg;
        CardView cardView;
        TextView titleTv, subTitleTv;
        public TopFilmsViewHolder(View view)
        {
            super(view);

            cardView = (CardView)view.findViewById(R.id.top_films_list_item_poster_cv);
            posterImg = (ImageView)view.findViewById(R.id.top_films_list_item_poster_img);
            titleTv = (TextView)view.findViewById(R.id.top_films_list_item_title_tv);
            subTitleTv = (TextView)view.findViewById(R.id.top_films_list_item_subtitle_tv);
            cardView.setOnClickListener(onClickListener);
        }

        public void setData(TiviBuModel data)
        {
            titleTv.setText(data.getTitle());
            subTitleTv.setText(data.getDescription());
            tiviBuModel = data;

            //new LoadHttpImage(this.posterImg).execute(data.getPosterUrl());
            Picasso.with(context).load(data.getPosterUrl()).into(this.posterImg);

        }

        View.OnClickListener onClickListener = new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Toast.makeText(context,"clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent((AppCompatActivity)context, FilmDetailActivity.class);
                intent.putExtra("poster",tiviBuModel.getPosterUrl());

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View,String>(posterImg,"poster_anime");

                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((MainActivity)context,pairs);
                    context.startActivity(intent,activityOptions.toBundle());

                }
                else
                {
                    context.startActivity(intent);
                }

            }
        };
    }

}
