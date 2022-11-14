package com.ksbm_astroexpert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.ksbm_astroexpert.ModelClass.NotificationModel;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.Astrodetails.Astrologerdetails;
import com.ksbm_astroexpert.view.BoldTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter  extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private List<NotificationModel> moviesList;
    List<NotificationModel>search_after;

    public SharedPreferences.Editor editor;
    SharedPreferences sharedpreferences;
    private String PREFS_NAME = "auth_info";
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNotiCaption,txtNotiMessage,txtNotiDate;


        public MyViewHolder(View parent) {
            super(parent);
            txtNotiCaption = parent.findViewById(R.id.txtNotiCaption);
            txtNotiMessage = parent.findViewById(R.id.txtNotiMessage);
            txtNotiDate = parent.findViewById(R.id.txtNotiDate);
        }
    }


    public NotificationAdapter(Context context, List<NotificationModel> moviesList) {
        this.context=context;
        this.moviesList = moviesList;
        this.search_after =moviesList;
    }

    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);
        return new NotificationAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.MyViewHolder holder, final int position) {

        if(moviesList.get(position).gettitle()!=null)
        {
            holder.txtNotiCaption.setText(moviesList.get(position).gettitle());
        }

        if(moviesList.get(position).getDate()!=null)
        {
            holder.txtNotiDate.setText(moviesList.get(position).getDate());
        }

        if(moviesList.get(position).getMessage()!=null)
        {
            holder.txtNotiMessage.setText(moviesList.get(position).getMessage());
        }



        holder.txtNotiMessage.setOnClickListener(new View.OnClickListener() {
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
