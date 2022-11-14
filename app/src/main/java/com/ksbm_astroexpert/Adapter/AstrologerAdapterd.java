package com.ksbm_astroexpert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.Astrologer.ImageDatad;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.home.ViewAllAstrologer;

import java.util.List;

public class AstrologerAdapterd extends RecyclerView.Adapter<AstrologerAdapterd.MyViewHolder> {

    private List<ImageDatad> moviesList;
    public SharedPreferences.Editor editor;
    SharedPreferences sharedpreferences;
    private String PREFS_NAME = "auth_info";
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView expername,txtExpertLang,txtExpertExp,txtRate;
        ImageView imgExpert;

        public MyViewHolder(View view) {
            super(view);
            //imgExpert = (ImageView) view.findViewById(R.id.imgExpert);
            expername = (TextView) view.findViewById(R.id.expername);
            /*txtExpertLang = (TextView) view.findViewById(R.id.txtExpertLang);
            txtExpertExp = (TextView) view.findViewById(R.id.txtExpertExp);
            txtRate = (TextView) view.findViewById(R.id.txtRate);*/

        }
    }


    public AstrologerAdapterd(Context context, List<ImageDatad> moviesList) {
        this.context=context;
        this.moviesList = moviesList;
    }

    @Override
    public AstrologerAdapterd.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plansadapterd, parent, false);
        return new AstrologerAdapterd.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AstrologerAdapterd.MyViewHolder holder, final int position) {
        final ImageDatad movie = moviesList.get(position);
       /* Picasso.get().load(moviesList.get(position).getImage())
                .into(holder.imgExpert);*/
        holder.expername.setText(moviesList.get(position).getOwner_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentViewAll = new Intent(context, ViewAllAstrologer.class);
                intentViewAll.putExtra("type", false);
                context.startActivity(intentViewAll);
            }
        });

        //linearLayout.setBackgroundResource(imageData.get(position));



        /*holder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PaymentAlertoption.class);
                intent.putExtra("planid",moviesList.get(position).getBannerId());
                intent.putExtra("enddate",moviesList.get(position).getend_date());
                intent.putExtra("startdate",moviesList.get(position).getstart_date());
                intent.putExtra("title",moviesList.get(position).getname());
                intent.putExtra("desc",moviesList.get(position).getdescription());
                intent.putExtra("cost",moviesList.get(position).getcost());
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
