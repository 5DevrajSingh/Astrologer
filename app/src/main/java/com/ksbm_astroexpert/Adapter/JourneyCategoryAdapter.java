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
import com.ksbm_astroexpert.ui.ShareJourney;
import com.ksbm_astroexpert.ui.home.Testimonials;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_MULTIPLE_TASK;

public class JourneyCategoryAdapter extends RecyclerView.Adapter<JourneyCategoryAdapter.MyViewHolder> {

    private List<ImageDatad> moviesList;
    public SharedPreferences.Editor editor;
    SharedPreferences sharedpreferences;
    private String PREFS_NAME = "auth_info",astro_id="";
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView expername,txtExpertLang,txtExpertExp,txtRate;
        ImageView imgExpert;

        public MyViewHolder(View view) {
            super(view);
          //  imgExpert = (ImageView) view.findViewById(R.id.attachmentimg);
            expername = (TextView) view.findViewById(R.id.expername);
        }
    }


    public JourneyCategoryAdapter(Context context, List<ImageDatad> moviesList,String astro_id) {
        this.context=context;
        this.moviesList = moviesList;
        this.astro_id=astro_id;
    }

    @Override
    public JourneyCategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.supportchatdd, parent, false);
        return new JourneyCategoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JourneyCategoryAdapter.MyViewHolder holder, final int position) {
        final ImageDatad movie = moviesList.get(position);

        holder.expername.setText(moviesList.get(position).getOwner_name());

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intentViewAll = new Intent(context, ShareJourney.class);
                intentViewAll.putExtra("id", moviesList.get(position).getId());
                intentViewAll.putExtra("name", moviesList.get(position).getOwner_name());
                intentViewAll.putExtra("astroid", astro_id);
                intentViewAll.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|FLAG_ACTIVITY_MULTIPLE_TASK);
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
