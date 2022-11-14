package com.ksbm_astroexpert.Chat.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Adapter.CallHistoryAdapter;
import com.ksbm_astroexpert.Adapter.RechargeAdapter;
import com.ksbm_astroexpert.Chat.models.OrderHistroyModel;
import com.ksbm_astroexpert.Chat.utils.ParseManager;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.ModelClass.AcceptModel;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.Wallet.RechargeWallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Call_History_Fragment extends Fragment {

    RecyclerView recyclerOrderHistory;
    TextView textNoRecord;
    Integer user_id;
     ArrayList<OrderHistroyModel.Result> arrListOrderHistory=new ArrayList<>();
    public Call_History_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_call_, container, false);

        recyclerOrderHistory=view.findViewById(R.id.recycler_orderhistroy);
        textNoRecord=view.findViewById(R.id.text_norecord);

        SharedPreferences sharedpreferences =getActivity().getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        user_id = Integer.valueOf(sharedpreferences.getString("id", ""));
       // Log.d("checkidddd", String.valueOf(user_id));

        hitOrderHistoryApi(String.valueOf(user_id));
        return view;
    }


    private void hitOrderHistoryApi(String userId) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RootURL.ORDER_HISTROY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("checkressponseee", response);
                try {
                    OrderHistroyModel model = ParseManager.getInstance().fromJSON(response, OrderHistroyModel.class);
                    if (model != null) {
                        if (model.status.equals("200")){
                            arrListOrderHistory=model.result;
                            Log.d("chearrlist", String.valueOf(arrListOrderHistory.size()));
                            if (!arrListOrderHistory.isEmpty()){
                                setRecycler();
                            }else {
                                textNoRecord.setVisibility(View.VISIBLE);
                            }

                        }else {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("dsdjld",volleyError.getMessage());
                // progressbar.hideProgress();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("user_id", userId);
                hashMap.put("type", "1");
                Log.d("fffffff", String.valueOf(hashMap));
                return hashMap;
            }
        };
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }
    void setRecycler(){
        recyclerOrderHistory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerOrderHistory.setLayoutManager(layoutManager);
        CallHistoryAdapter historyAdapter = new CallHistoryAdapter(getActivity(), arrListOrderHistory);
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.recyclerview_anim_layout);
        recyclerOrderHistory.setLayoutAnimation(layoutAnimationController);
        recyclerOrderHistory.setAdapter(historyAdapter);
    }

}