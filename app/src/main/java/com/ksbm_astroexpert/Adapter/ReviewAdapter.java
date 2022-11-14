package com.ksbm_astroexpert.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.Wallet.Offer;

import java.util.List;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

        private List<Offer> moviesList;
        public SharedPreferences.Editor editor;
        SharedPreferences sharedpreferences;
        private String PREFS_NAME = "auth_info";
        Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView  name,comments;
            RatingBar ratinsg;


            public MyViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.name);
                ratinsg = view.findViewById(R.id.ratinsg);
                comments = (TextView) view.findViewById(R.id.comments);

            }
        }


        public ReviewAdapter(Context context, List<Offer> moviesList) {
            this.context=context;
            this.moviesList = moviesList;
        }

        @Override
        public ReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.reviewadapter, parent, false);
            return new ReviewAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ReviewAdapter.MyViewHolder holder, final int position) {
            final Offer movie = moviesList.get(position);
            float rating=1;
            try {
                rating= Float.parseFloat(moviesList.get(position).getBannerId());

            } catch (NumberFormatException e) {
            }
            holder.ratinsg.setRating(rating);
            holder.name.setText(moviesList.get(position).getname());
            holder.comments.setText(moviesList.get(position).getdescription());
            //linearLayout.setBackgroundResource(imageData.get(position));



        }

        @Override
        public int getItemCount() {
            return moviesList.size();
        }

    }
