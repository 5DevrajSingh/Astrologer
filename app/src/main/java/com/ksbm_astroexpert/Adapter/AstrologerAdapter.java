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
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.Constant.Constant;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.Astrodetails.Astrologerdetails;
import com.ksbm_astroexpert.ui.Astrodetails.SlidingAstroDetailsLIst;
import com.ksbm_astroexpert.view.BoldTextView;
import com.ksbm_astroexpert.view.ImageData;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class AstrologerAdapter extends RecyclerView.Adapter<AstrologerAdapter.MyViewHolder> {

    private List<ImageData> moviesList;
    List<ImageData>search_after;

    public SharedPreferences.Editor editor;
    SharedPreferences sharedpreferences;
    private String PREFS_NAME = "auth_info";
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView imgExpert;
        AppCompatRatingBar rating;

        BoldTextView txtExpertName,txtRate;
        TextView txtExpertLang;
        TextView exp,chatbar,chatbaroffline;

        public MyViewHolder(View parent) {
            super(parent);
            imgExpert = parent.findViewById(R.id.imgExpert);
            rating = parent.findViewById(R.id.rating);
            txtExpertName = parent.findViewById(R.id.txtExpertName);
            txtRate = parent.findViewById(R.id.txtRate);
            txtExpertLang = parent.findViewById(R.id.txtExpertLang);
            exp = parent.findViewById(R.id.exp);

            chatbar = parent.findViewById(R.id.chatbar);
            chatbaroffline = parent.findViewById(R.id.chatbaroffline);
        }
    }


    public AstrologerAdapter(Context context, List<ImageData> moviesList) {
        this.context=context;
        this.moviesList = moviesList;
        this.search_after =moviesList;
    }

    @Override
    public AstrologerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.astrohorizontal, parent, false);
        return new AstrologerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AstrologerAdapter.MyViewHolder holder, final int position) {
        final ImageData movie = moviesList.get(position);
        Picasso.get().load(moviesList.get(position).getImage())
                .placeholder(R.drawable.splash)
                .into(holder.imgExpert);
        float rating=1;
        try {
            rating= Float.parseFloat(moviesList.get(position).getAvg_rating());
        } catch (NumberFormatException e) {
            rating=1;
        }

        if (moviesList.get(position).getAvg_rating().isEmpty()){
            holder.rating.setRating(rating);
        }else {
            holder.rating.setRating(Float.parseFloat(moviesList.get(position).getAvg_rating()));
        }

        holder.txtExpertName.setText(moviesList.get(position).getOwner_name());
        holder.txtRate.setText(moviesList.get(position).getCall_price_m());


        if (moviesList.get(position).getCurrent_status().equals("Offline")){
            holder.chatbar.setVisibility(View.GONE);
            holder.chatbaroffline.setVisibility(View.VISIBLE);
            holder.chatbaroffline.setTextSize(10);
            holder.chatbaroffline.setText("Wait time : "+moviesList.get(position).getwait_time());
        }
        holder.txtExpertLang.setText((moviesList.get(position).getLanguage()).replaceAll(",",", "));

        holder.exp.setText("Exp "+moviesList.get(position).getWorking_ex()+" Year");
        int price=0;
        try {
            double  tprice= Double.parseDouble(moviesList.get(position).getCall_price_m());
            price = (int) tprice;
        } catch (NumberFormatException e) {
        }

        if (moviesList.get(position).getchat_commission().equals(null)){

            int a = 0;
            int b = Integer.parseInt(moviesList.get(position).getchat_commission());
            int c = a+b;
            holder.txtRate.setText("₹"+c+"/minute");

        }else {
            int a = Integer.parseInt(moviesList.get(position).getCall_price_m());
            int b = Integer.parseInt(moviesList.get(position).getchat_commission());
            int c = a+b;
            holder.txtRate.setText("₹"+c+"/minute");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context, "Cliked"+moviesList.get(position).getId(), Toast.LENGTH_SHORT).show();
                Log.d("atsroidisiis", "onClick: "+"="+moviesList.get(position).getId());
                Intent intent = new Intent(context, Astrologerdetails.class);
                intent.putExtra("astroid",moviesList.get(position).getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

//                Log.d("dswdwsdwdwd","swdwdwd"+astroModel.getId());
                //Intent intent = new Intent(mContext, Astrologerdetails.class);
//                Intent intent = new Intent(context, SlidingAstroDetailsLIst.class);
//                intent.putExtra("pos",position);
//                intent.putExtra("astroid",moviesList.get(position).getId());
////                intent.putExtra("astro_token",astroModel.getDeviceToken());
//                context.startActivity(intent);
//



            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    moviesList = search_after;
                } else {
                    List<ImageData> filteredList = new ArrayList<>();
                    for (ImageData row : search_after) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getOwner_name().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    moviesList= filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = moviesList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                moviesList = (ArrayList<ImageData>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }


}
