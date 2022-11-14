package com.ksbm_astroexpert.ui.Wallet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ksbm_astroexpert.Chat.models.CustomerTransactionModel;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;

import java.util.ArrayList;


public class CustomerTransHistoryAdapter extends RecyclerView.Adapter<CustomerTransHistoryAdapter.MyViewHolder> {
        private ArrayList<CustomerTransactionModel.Response> arrlist;
        public SharedPreferences.Editor editor;
        Context context;

    String Invoice_url= RootURL.Base_URL+"/Astroksbmadmin/api/api/reciept/";


    public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView  txtAmt,textName,txtDate,txtDuration,btn_order_receipt;

            public MyViewHolder(View view) {
                super(view);

                txtAmt = (TextView) view.findViewById(R.id.text_history_amt);
                txtDate = (TextView) view.findViewById(R.id.text_history_datetime);
                txtDuration = (TextView) view.findViewById(R.id.text_history_duration);
                textName = (TextView) view.findViewById(R.id.text_history_name);
                btn_order_receipt = (TextView) view.findViewById(R.id.btn_order_receipt);
            }
        }


        public CustomerTransHistoryAdapter(Context context, ArrayList<CustomerTransactionModel.Response> arrlist) {
            this.context=context;
            this.arrlist = arrlist;
        }

        @Override
        public CustomerTransHistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.wallet_history_custom_layout, parent, false);
            return new CustomerTransHistoryAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(CustomerTransHistoryAdapter.MyViewHolder holder, final int position) {
            holder.textName.setText(arrlist.get(position).description);
            holder.txtDate.setText(arrlist.get(position).transcationDate);
            if (arrlist.get(position).dramount.equals("0.00")){
                holder.txtAmt.setTextColor(Color.GREEN);
                holder.txtAmt.setTextColor(context.getResources().getColor(R.color.green));
                holder.txtAmt.setText("+ "+arrlist.get(position).cramount);
            }else {
                holder.txtAmt.setTextColor(Color.RED);
                holder.txtAmt.setText("- "+arrlist.get(position).dramount);
            }

            if (!arrlist.get(position).cramount.equals("0.00") && arrlist.get(position).bal_type.equals("5") && !arrlist.get(position).trans_id.equals("")){
                holder.btn_order_receipt.setVisibility(View.VISIBLE);
            }else {
                holder.btn_order_receipt.setVisibility(View.GONE);
            }


            holder.btn_order_receipt.setVisibility(View.GONE);

            holder.btn_order_receipt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = Invoice_url+arrlist.get(position).trans_id;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });



        }

        @Override
        public int getItemCount() {
            return arrlist.size();
        }

    }
