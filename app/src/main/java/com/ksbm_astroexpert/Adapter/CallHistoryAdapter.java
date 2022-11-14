package com.ksbm_astroexpert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.Chat.activity.ChatActivity;
import com.ksbm_astroexpert.Chat.models.OrderHistroyModel;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.Astrodetails.Astrologerdetails;
import com.ksbm_astroexpert.ui.Astrodetails.SlidingAstroDetailsLIst;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CallHistoryAdapter extends RecyclerView.Adapter<CallHistoryAdapter.ViewHolder> {
    Context context;
    ArrayList<OrderHistroyModel.Result> arList;
    String Invoice_url= RootURL.Base_URL+"/Astroksbmadmin/api/api/taxinvoice/";

    public CallHistoryAdapter(Context context, ArrayList<OrderHistroyModel.Result> arList) {
        this.context = context;
        this.arList = arList;
    }

    @NonNull
    @Override
    public CallHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_call_layout, parent, false);
        return new CallHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CallHistoryAdapter.ViewHolder holder, final int position) {

        holder.textId.setText(arList.get(position).invoiceId);
        Log.d("checkrate", arList.get(position).callLogCallId);
        holder.txtName.setText(arList.get(position).astrologerName);
        holder.txtStatus.setText(arList.get(position).status);
        holder.txtDateTime.setText(arList.get(position).callLogCrDate);
        Picasso.get().load(RootURL.Base_URL+"/Astroksbmadmin/admin/uploads/vendor/" + arList.get(position).imgUrl).error(R.drawable.splash).into(holder.userIMG);
        holder.txtDuration.setText("Duration: " + arList.get(position).callLogDurationMin + " Minutes");
        holder.txtCallDeduction.setText("Deduction: ₹" + arList.get(position).callLogChargeAmount);
        if(arList.get(position).currentStatus!=null)
        {
            if (arList.get(position).currentStatus.equalsIgnoreCase("Online")) {
                holder.callBTN.setBackground(context.getResources().getDrawable(R.drawable.call_online_btn_border));
                holder.callBTN.setTextColor(Color.parseColor("#33cc33"));

            } else {
                holder.callBTN.setBackground(context.getResources().getDrawable(R.drawable.call_btn_border));
                holder.callBTN.setTextColor(Color.parseColor("#ee534f"));
            }
        }


        if (arList.get(position).callPriceM==null) {
            int a = 0;
            int b = 0;
            if(arList.get(position).callCommission!=null)
            {
                b = Integer.parseInt(arList.get(position).callCommission);
            }
            else
            {
                b = Integer.parseInt("0");
            }

            int c = a + b;
            holder.txtCallRate.setText("Call Rate ₹" + c + "/minute");
        } else {
            int a = Integer.parseInt(arList.get(position).callPriceM);
            int b = Integer.parseInt(arList.get(position).callCommission);
            int c = a + b;
            holder.txtCallRate.setText("Call Rate ₹" + c + "/minute");
           // Log.d("checkrate", String.valueOf(c));
        }
        holder.deletIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAt(position);
            }
        });

        holder.callBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ChatActivity.class)
                        .putExtra("astroid",arList.get(position).id)
                        .putExtra("astro_token",arList.get(position).deviceToken)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        holder.InvoiceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Working on it", Toast.LENGTH_SHORT).show();
                String url = Invoice_url+arList.get(position).invoiceId;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


        holder.InvoiceText.setVisibility(View.GONE);


    }

    public void removeAt(int position) {
        arList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, arList.size());
    }

    @Override
    public int getItemCount() {
        return arList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textId, txtName, txtStatus, txtDateTime, txtCallRate, txtDuration, txtCallDeduction, txtMinute,InvoiceText;
        ImageView userIMG, deletIMG;
        Button callBTN;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            InvoiceText = (TextView) itemView.findViewById(R.id.btn_order_invoice);
            textId = (TextView) itemView.findViewById(R.id.text_order_id);
            txtName = (TextView) itemView.findViewById(R.id.text_order_name);
            txtStatus = (TextView) itemView.findViewById(R.id.text_order_status);
            txtDateTime = (TextView) itemView.findViewById(R.id.text_order_datetime);
            txtCallRate = (TextView) itemView.findViewById(R.id.text_order_callrate);
            txtDuration = (TextView) itemView.findViewById(R.id.text_order_callduration);
            txtCallDeduction = (TextView) itemView.findViewById(R.id.text_order_deduction);
            txtMinute = (TextView) itemView.findViewById(R.id.text_order_min);
            userIMG = (ImageView) itemView.findViewById(R.id.image_order_user);
            deletIMG = (ImageView) itemView.findViewById(R.id.image_order_delete);
            callBTN = (Button) itemView.findViewById(R.id.btn_order_call);


        }
    }
}

