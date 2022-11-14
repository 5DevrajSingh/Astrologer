package com.ksbm_astroexpert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ksbm_astroexpert.BuildConfig;
import com.ksbm_astroexpert.ModelClass.AllRemidesModel;
import com.ksbm_astroexpert.ModelClass.WalletModel;
import com.ksbm_astroexpert.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AllRemediesAdapter extends RecyclerView.Adapter<AllRemediesAdapter.ViewHolder> {
    Context context;
    ArrayList<AllRemidesModel.Response> arList;

    public AllRemediesAdapter(Context context, ArrayList<AllRemidesModel.Response> arList) {
        this.context = context;
        this.arList = arList;
    }

    @NonNull
    @Override
    public AllRemediesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_remedies_layout, parent, false);
        return new AllRemediesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AllRemediesAdapter.ViewHolder holder, final int position) {
        Picasso.get().load(arList.get(position).iconImgUrl).error(R.drawable.my_astrologer).into(holder.imgRemides);


        holder.floatBtn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,arList.get(position).iconImgUrl);
                    sendIntent.setType("text/*");
                    sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(sendIntent);

                }
                catch (Exception e)
                {

                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return arList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgRemides;
        FloatingActionButton floatBtn_share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgRemides = (ImageView) itemView.findViewById(R.id.img_remides);
            floatBtn_share = (FloatingActionButton) itemView.findViewById(R.id.floatBtn_share);

        }
    }
}


