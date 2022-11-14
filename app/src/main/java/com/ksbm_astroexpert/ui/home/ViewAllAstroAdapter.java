package com.ksbm_astroexpert.ui.home;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.Astrodetails.Astrologerdetails;
import com.ksbm_astroexpert.view.BoldTextView;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ViewAllAstroAdapter extends RecyclerView.Adapter
{
    public Context mContext;
    public Activity mActivity;
    public List<AstroModel> mAList;

    public OnLoadMoreListener loadMoreListener;


    public final int VIEW_TYPE_ITEM = 0;
    public final int VIEW_TYPE_LOADING = 1;

    public boolean isLoading;
    public int visibleThreshold = 1;
    public int lastVisibleItem, totalItemCount;

    public int lastPosition = -1;



    public ViewAllAstroAdapter(Context mContext, List<AstroModel> mAList,
                        RecyclerView mRecyclerView)
    {
        this.mContext = mContext;
        this.mAList = mAList;
      //  mActivity = (Activity) mContext;


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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.astrohorizontal, parent, false);
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

        BoldTextView txtExpertName,txtRate,status;
        TextView exp,chatbar,chatbaroffline;
        TextView txtExpertLang;


        public MyViewHolder(View parent)
        {
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


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {

        if (holder instanceof MyViewHolder) {

            final MyViewHolder myViewHolder = (MyViewHolder) holder;
            final AstroModel astroModel = mAList.get(position);

            Picasso.get().load(astroModel.getAstroImage()).error(R.drawable.splash).into(myViewHolder.imgExpert);

            float rating=1;
            try {
                rating= Float.parseFloat(astroModel.getAstroRating());

            } catch (NumberFormatException e) {
            }
            myViewHolder.rating.setRating(rating);
            myViewHolder.txtExpertName.setText(astroModel.getAstroName());
            myViewHolder.txtExpertLang.setText(astroModel.getAstroEmail());
            myViewHolder.exp.setText("Exp "+astroModel.getAstroAddress()+" Year");
            int price=0;
            try {
                double  tprice= Double.parseDouble(astroModel.getAstroCallPrice());
                price = (int) tprice;
            } catch (NumberFormatException e) {
            }

            if (astroModel.getchat_commission().equals(null)){

                int a = 0;
                int b = Integer.parseInt(astroModel.getchat_commission());
                int c = a+b;
                myViewHolder.txtRate.setText("₹"+c+"/minute");
            }else {
                int a = Integer.parseInt(astroModel.getAstroChatPrice());
                int b = Integer.parseInt(astroModel.getchat_commission());
                int c = a+b;
                myViewHolder.txtRate.setText("₹"+c+"/minute");
            }

            Log.e("Dayyyysyydyddy","::"+astroModel.getAstroId());


            if (astroModel.getAstroStatus().equals("Offline") && astroModel.getAstroStatus_call().equals("Offline")){
                myViewHolder.chatbar.setVisibility(View.GONE);
                myViewHolder.chatbaroffline.setVisibility(View.VISIBLE);
                myViewHolder.chatbaroffline.setTextSize(10);
                myViewHolder.chatbaroffline.setText("Wait time : "+astroModel.getwait_time());
            }

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, Astrologerdetails.class);
                    intent.putExtra("astroid",astroModel.getAstroId());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
    public int getItemCount() {
        return mAList == null ? 0 : mAList.size();
    }

    public void setLoaded() {
        isLoading = false;
    }



}

