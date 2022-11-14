package com.ksbm_astroexpert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ksbm_astroexpert.ModelClass.RatingModels;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.ListAstrodetailsBinding;

import com.ksbm_astroexpert.ui.Astrodetails.ItemListener;
import com.ksbm_astroexpert.ui.Astrodetails.ModelAstrologerList;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.ksbm_astroexpert.Constant.Constant.astroModelList;

public class RattingAdapter extends RecyclerView.Adapter<RattingAdapter.MyViewHolder> {

    private List<RatingModels> moviesList;
    public SharedPreferences.Editor editor;
    
    
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  name,comments;
        RatingBar ratinsg;

        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            ratinsg = (RatingBar) view.findViewById(R.id.ratinsg);
            comments = (TextView) view.findViewById(R.id.comments);
        }
    }


    public RattingAdapter(Context context, List<RatingModels> moviesList) {
        this.context=context;
        this.moviesList = moviesList;
    }

    @Override
    public RattingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ratting_view, parent, false);
        return new RattingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RattingAdapter.MyViewHolder holder, final int position) {
        final RatingModels movie = moviesList.get(position);
        holder.name.setText(movie.getUsername());
        holder.ratinsg.setRating(Float.parseFloat(movie.getStar()));
        holder.comments.setText(movie.getComments());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
