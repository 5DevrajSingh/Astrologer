package com.ksbm_astroexpert.ui.home;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.Constant.Constant;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.Astrodetails.Astrologerdetails;
import com.ksbm_astroexpert.ui.Astrodetails.ModelAstrologerList;
import com.ksbm_astroexpert.ui.Astrodetails.SlidingAstroDetailsLIst;
import com.ksbm_astroexpert.view.BoldTextView;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AstroAdapter extends RecyclerView.Adapter
{
    public Context mContext;
    public Activity mActivity;
    public List<ModelAstrologerList.Record> mAList;

    public OnLoadMoreListener loadMoreListener;


    public final int VIEW_TYPE_ITEM = 0;
    public final int VIEW_TYPE_LOADING = 1;

    public boolean isLoading;
    public int visibleThreshold = 1;
    public int lastVisibleItem, totalItemCount;

    public int lastPosition = -1;



    public AstroAdapter(Context mContext, List<ModelAstrologerList.Record> mAList,
                        RecyclerView mRecyclerView)
    {
        this.mContext = mContext;
        this.mAList = mAList;
        mActivity = (Activity) mContext;


        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (loadMoreListener != null) {
                        loadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.astro_menu, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_menu, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }




    // "Loading item" ViewHolder
    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressBar1);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ShapeableImageView imgExpert;
        AppCompatRatingBar rating;

        BoldTextView txtExpertName,txtRate;
        ImageView status;
        TextView exp,txtExpertLang;


        public MyViewHolder(View parent)
        {
            super(parent);
            imgExpert = parent.findViewById(R.id.imgExpert);
            rating = parent.findViewById(R.id.rating);
            txtExpertName = parent.findViewById(R.id.txtExpertName);
            txtRate = parent.findViewById(R.id.txtRate);
            status = parent.findViewById(R.id.status);
            exp = parent.findViewById(R.id.exp);
            txtExpertLang = parent.findViewById(R.id.txtExpertLang);


        }
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {

        if (holder instanceof MyViewHolder) {

            final MyViewHolder myViewHolder = (MyViewHolder) holder;
            final ModelAstrologerList.Record astroModel = mAList.get(position);

            Picasso.get().load(astroModel.getImage()).error(R.drawable.splash).into(myViewHolder.imgExpert);
            Log.d("cehckkds",astroModel.getImage());

            float rating=1;
            try {
                rating= Float.parseFloat(astroModel.getAvgRating());
            } catch (NumberFormatException e) {
                rating=1;
            }

            if (astroModel.getRating().isEmpty()){
                myViewHolder.rating.setRating(rating);
            }else {
                myViewHolder.rating.setRating(Float.parseFloat(astroModel.getRating().get(0).getStar()));
            }

            myViewHolder.txtExpertName.setText(astroModel.getOwnerName());
            myViewHolder.txtExpertLang.setText(astroModel.getLanguage().replaceAll(",",", "));
            if (astroModel.getCurrentStatus().equals("Online")){
                Picasso.get().load(R.drawable.online).into(myViewHolder.status);
            }else  if(astroModel.getCurrentStatus().equals("Busy"))
            {
                Picasso.get().load(R.drawable.online).into(myViewHolder.status);
            }
            else
                {
                Picasso.get().load(R.drawable.offline).into(myViewHolder.status);
                }
            //myViewHolder.exp.setText("Exp "+astroModel.getAddress()+" Year");
            myViewHolder.exp.setText("Exp "+astroModel.getExperience()+" Year");
            int price=0;
            try {
                double  tprice= Double.parseDouble(astroModel.getCallPriceM());
                price = (int) tprice;
            } catch (NumberFormatException e) {
            }


            if (astroModel.getChatCommission().equals(null)){

                int a = 0;
                int b = Integer.parseInt(astroModel.getChatCommission());
                int c = a+b;
                myViewHolder.txtRate.setText("₹"+c+"/Min.");
            }else {
                int a = Integer.parseInt(astroModel.getChatPriceM());
                int b = Integer.parseInt(astroModel.getChatCommission());
                int c = a+b;
                myViewHolder.txtRate.setText("₹"+c+"/Min.");
            }




            Log.e("Dayyyysyydyddy","::"+astroModel.getId());

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Constant.astroModelList=mAList;
                    Log.d("dswdwsdwdwd","swdwdwd"+astroModel.getId());
                    //Intent intent = new Intent(mContext, Astrologerdetails.class);
                    Intent intent = new Intent(mContext, SlidingAstroDetailsLIst.class);
                    intent.putExtra("pos",position);
                    intent.putExtra("astroid",astroModel.getId());
                    intent.putExtra("astro_token",astroModel.getDeviceToken());
                    mContext.startActivity(intent);
                }
            });


        }
        /*Load more item*/
        else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }



    }



    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.loadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return mAList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return mAList == null ? 0 : mAList.size();
    }

    public void setLoaded() {
        isLoading = false;
    }



}
