package com.ksbm_astroexpert.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.ModelClass.WalletModel;
import com.ksbm_astroexpert.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

    public class RechargeAdapter extends RecyclerView.Adapter<RechargeAdapter.ViewHolder> {
        Context context;
        ArrayList<WalletModel.Response> arList;
        public RechargeListener rechargeListener;
        public int first_selectedItem;
        public RechargeAdapter(Context context, ArrayList<WalletModel.Response> arList,RechargeListener rechargeListener) {
            this.rechargeListener = rechargeListener;
            this.context = context;
            this.arList = arList;
            first_selectedItem=0;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recharge_custom_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            holder.txtBalance.setText("₹"+arList.get(position).rechargePlanAmount);
//            if (first_selectedItem == position) {
//                holder.reletive_bg.setBackgroundColor(R.drawable.relative_bg);
//                holder.txtBalance.setTextColor(Color.WHITE);
//            }

            if (arList.get(position).rechargePlanExtraPercent.equals("0")){
                holder.relGift.setVisibility(View.GONE);
            }else {
                Animation animZoomOut = AnimationUtils.loadAnimation(context, R.anim.pump);
                holder.relGift.startAnimation(animZoomOut);
                holder.txt_GiftPrice.setText("Gift ₹"+ arList.get(position).rechargePlanExtraPercent);
            }

        }

        @Override
        public int getItemCount() {
            return arList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView txtBalance, txt_GiftPrice;
            RelativeLayout relGift,reletive_bg;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                txtBalance = (TextView) itemView.findViewById(R.id.txt_Balance);
                txt_GiftPrice = (TextView) itemView.findViewById(R.id.txt_gift_price);
                relGift = (RelativeLayout) itemView.findViewById(R.id.rel_gift);
                reletive_bg = (RelativeLayout) itemView.findViewById(R.id.reletive_bg);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String amount=arList.get(getAdapterPosition()).rechargePlanAmount;
                        String ExtraPercentage=arList.get(getAdapterPosition()).rechargePlanExtraPercent;
                        rechargeListener.getAmount(amount,ExtraPercentage);
                    }
                });


            }
        }
        public interface RechargeListener{
            void getAmount(String amt,String ExtraPercentage);
        }
    }


