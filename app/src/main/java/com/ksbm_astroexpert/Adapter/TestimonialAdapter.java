package com.ksbm_astroexpert.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.Wallet.Offer;

import java.util.List;


public class TestimonialAdapter extends RecyclerView.Adapter<TestimonialAdapter.MyViewHolder> {

        private List<Offer> moviesList;
        public SharedPreferences.Editor editor;
        SharedPreferences sharedpreferences;
        private String PREFS_NAME = "auth_info";
        Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView  named,email,desc,astronname,hourneyname;
            ImageView imgview;

            public MyViewHolder(View view) {
                super(view);
                named = (TextView) view.findViewById(R.id.named);
                email = (TextView) view.findViewById(R.id.email);
                desc = (TextView) view.findViewById(R.id.desc);
                astronname = (TextView) view.findViewById(R.id.astronname);
                hourneyname = (TextView) view.findViewById(R.id.hourneyname);


            }
        }


        public TestimonialAdapter(Context context, List<Offer> moviesList) {
            this.context=context;
            this.moviesList = moviesList;
        }

        @Override
        public TestimonialAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.callintakeadpaterd, parent, false);
            return new TestimonialAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(TestimonialAdapter.MyViewHolder holder, final int position) {
            final Offer movie = moviesList.get(position);
            holder.named.setText(moviesList.get(position).getname());
            holder.email.setText(moviesList.get(position).getscheduled());
            holder.desc.setText(moviesList.get(position).getBannerSrc());
            holder.astronname.setText(moviesList.get(position).getanswer());
            holder.hourneyname.setText(moviesList.get(position).getscheduled_answer());
            //linearLayout.setBackgroundResource(imageData.get(position));



        }

        @Override
        public int getItemCount() {
            return moviesList.size();
        }

    }
