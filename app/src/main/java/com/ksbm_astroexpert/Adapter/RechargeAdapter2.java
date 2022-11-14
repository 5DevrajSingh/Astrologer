package com.ksbm_astroexpert.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.ModelClass.WalletModel;
import com.ksbm_astroexpert.R;

import java.util.ArrayList;

public class RechargeAdapter2  extends RecyclerView.Adapter<RechargeAdapter2.ViewHolder> {
    Context context;
    ArrayList<WalletModel.Response2> arList;
    public RechargeAdapter2.RechargeListener rechargeListener;
    public int first_selectedItem;
    public RechargeAdapter2(Context context, ArrayList<WalletModel.Response2> arList, RechargeAdapter2.RechargeListener rechargeListener) {
        this.rechargeListener = rechargeListener;
        this.context = context;
        this.arList = arList;
        first_selectedItem=0;
    }

    @NonNull
    @Override
    public RechargeAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recharge_custom_layout, parent, false);
        return new RechargeAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RechargeAdapter2.ViewHolder holder, final int position) {
        holder.txtBalance.setText("₹"+arList.get(position).rechargePlanAmount);

        if (arList.get(position).rechargePlanExtraPercent.equals("0")){
            holder.relGift.setVisibility(View.GONE);
        }else {
            Animation animZoomOut = AnimationUtils.loadAnimation(context, R.anim.pump);
            holder.relGift.startAnimation(animZoomOut);
            holder.txt_GiftPrice.setText("First Recharge Gift ₹"+ arList.get(position).rechargePlanExtraPercent);

            holder.reletive_bg.setBackgroundColor(R.drawable.relative_bg);
            holder.txtBalance.setTextColor(Color.WHITE);

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


