package com.ksbm_astroexpert.ui.Astrodetails;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.Wallet.Offer;

import java.util.List;


public class RemidesAdapter extends RecyclerView.Adapter<RemidesAdapter.MyViewHolder> {

        private List<Offer> moviesList;
        public SharedPreferences.Editor editor;
        SharedPreferences sharedpreferences;
        private String PREFS_NAME = "auth_info";
        Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView  experties;
            ImageView imgview;

            public MyViewHolder(View view) {
                super(view);
                experties = (TextView) view.findViewById(R.id.experties);

            }
        }


        public RemidesAdapter(Context context, List<Offer> moviesList) {
            this.context=context;
            this.moviesList = moviesList;
        }

        @Override
        public RemidesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.callintakeadpater, parent, false);
            return new RemidesAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RemidesAdapter.MyViewHolder holder, final int position) {
            final Offer movie = moviesList.get(position);
            holder.experties.setText(moviesList.get(position).getBannerId());
            Log.i("dfsdfsdfsdf",moviesList.get(position).getBannerId());
            //linearLayout.setBackgroundResource(imageData.get(position));



        }

        @Override
        public int getItemCount() {
            return moviesList.size();
        }

    }
