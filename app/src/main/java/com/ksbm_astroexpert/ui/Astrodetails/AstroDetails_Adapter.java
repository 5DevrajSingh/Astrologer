package com.ksbm_astroexpert.ui.Astrodetails;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.ListAstrodetailsBinding;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.ksbm_astroexpert.Constant.Constant.astroModelList;

public class AstroDetails_Adapter extends RecyclerView.Adapter<AstroDetails_Adapter.MyViewHolder> {
    private final Context context;
    List<ModelAstrologerList.Record> list = new ArrayList<>();
    ItemListener itemListener;
    int qty;

    public AstroDetails_Adapter(Context activity, List<ModelAstrologerList.Record> list, ItemListener itemListener) {
        this.context = activity;
        this.list = list;
        this.itemListener = itemListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ListAstrodetailsBinding binding;
        public MyViewHolder(@NonNull ListAstrodetailsBinding adapterCartBinding) {
            super(adapterCartBinding.getRoot());
            this.binding = adapterCartBinding;
        }
    }

    @Override
    public AstroDetails_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListAstrodetailsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_astrodetails, parent, false);
        return new AstroDetails_Adapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(AstroDetails_Adapter.MyViewHolder holder, int position) {

        list=astroModelList;

        ModelAstrologerList.Record model=list.get(position);
        RequestOptions options = new RequestOptions().placeholder(R.drawable.profile)
                .dontAnimate();
        Glide.with(context)
                .load(model.getImage())
                .apply(options)
                .into(holder.binding.imgExpert);

       holder.binding.astroname.setText(model.getOwnerName());
       if (model.getCommentRandval()!=null)
       {
           holder.binding.comments.setText(""+model.getCommentRandval()+"k mins");
       }
       else
       {
           holder.binding.comments.setText("0"+"k mins");
       }
        if (model.getCallRandval()!=null)
        {
            holder.binding.calls.setText(""+model.getCallRandval()+"k mins");
        }
        else
        {
            holder.binding.calls.setText("0"+"k mins");
        }

        if (model.getReportRandval()!=null)
        {
            holder.binding.reports.setText(""+model.getReportRandval()+"k mins");
        }
        else
        {
            holder.binding.reports.setText("0"+"k mins");
        }


        holder.binding.astroexp.setText(model.getExperience()+" years");
        holder.binding.contentprofile.longbio.setText(HtmlCompat.fromHtml(model.getLongBio(), 0));
       holder.binding.contentprofile.ratting.setText(model.getAvgRating());
       try
       {
           holder.binding.experties.setText(model.getSkill().toString().replaceAll(",",", "));
       }
       catch (Exception e)
       {
           holder.binding.experties.setVisibility(View.GONE);
       }

        try {
            float  rating= Float.parseFloat(model.getAvgRating());
            holder.binding.contentprofile.rat.setRating(rating);
        } catch (NumberFormatException e) {
        }

        holder.binding.astrolang.setText(model.getLanguage().replaceAll(",",", "));

        holder.binding.contentprofile.countRatting.setText("Reviews ("+model.getRating().size()+" rating)");

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
        for (int i=0;i<model.getRating().size();i++)
        {
            View itemView = inflater.inflate(R.layout.reviewadapter, null);
            TextView title = itemView.findViewById(R.id.name);
            RatingBar ratingBar=itemView.findViewById(R.id.ratinsg);
            TextView comment = itemView.findViewById(R.id.comments);
            title.setText(model.getRating().get(i).getUsername());
            comment.setText(model.getRating().get(i).getComments());
            ratingBar.setRating(Float.parseFloat(model.getRating().get(i).getStar()));
            holder.binding.contentprofile.llRating.addView(itemView);
        }




    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

}