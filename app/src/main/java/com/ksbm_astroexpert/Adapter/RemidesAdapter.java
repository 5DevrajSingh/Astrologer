package com.ksbm_astroexpert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.RemidesDetails.RemidesDetails;
import com.ksbm_astroexpert.view.ImageData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RemidesAdapter extends RecyclerView.Adapter<RemidesAdapter.MyViewHolder> {

    private List<ImageData> moviesList;
    List<ImageData>search_after;

    public SharedPreferences.Editor editor;
    SharedPreferences sharedpreferences;
    private String PREFS_NAME = "auth_info";
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRemedies;
        TextView txtCaption;


        public MyViewHolder(View parent) {
            super(parent);
            imgRemedies = parent.findViewById(R.id.imgRemedies);
            txtCaption = parent.findViewById(R.id.txtCaption);
        }
    }


    public RemidesAdapter(Context context, List<ImageData> moviesList) {
        this.context=context;
        this.moviesList = moviesList;
        this.search_after =moviesList;
    }

    @Override
    public RemidesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.remidesadpater, parent, false);
        return new RemidesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RemidesAdapter.MyViewHolder holder, final int position) {
        final ImageData movie = moviesList.get(position);
        Picasso.get().load(moviesList.get(position).getImage())
                .placeholder(R.drawable.splash)
                .into(holder.imgRemedies);

        holder.txtCaption.setText(moviesList.get(position).getOwner_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = String.valueOf(Html.fromHtml(moviesList.get(position).getAvg_rating()));
                Intent intent = new Intent(context, RemidesDetails.class);
                intent.putExtra("astrotitle",moviesList.get(position).getOwner_name());
                intent.putExtra("remides_id",moviesList.get(position).getId());
                intent.putExtra("astrocontent",name);
                intent.putExtra("icon2",moviesList.get(position).getLanguage());
                context.startActivity(intent);

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
