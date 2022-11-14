package com.ksbm_astroexpert.ui.account;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Astrologer.ProfileUpdate;
import com.ksbm_astroexpert.Chat.RegisterUser;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.HomeAccountFragmentBinding;
import com.ksbm_astroexpert.ui.Astrodetails.Astrologerdetails;
import com.ksbm_astroexpert.ui.Wallet.RechargeWallet;
import com.ksbm_astroexpert.ui.Wallet.Wallethistory;
import com.ksbm_astroexpert.ui.base.BaseFragment;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.ksbm_astroexpert.ui.home.UserProfile;
import com.ksbm_astroexpert.ui.home.ViewAllAstrologer;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

import static com.ksbm_astroexpert.ui.home.HomeActivity.ChanFrag;

public class AccountFragment extends BaseFragment {

    private HomeAccountFragmentBinding binding;
    private String mobile="";

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;


    @Override
    protected int getLayout() {
        return R.layout.home_account_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = (HomeAccountFragmentBinding) getBinding();
        init();

        ChanFrag="Account";

    }

    private void init() {

        SharedPreferences sharedpreferences =getActivity().getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

        if (!sharedpreferences.getString("username","").equals("null")){
            binding.tvUserName.setText(sharedpreferences.getString("username",""));
            binding.tvUserEmailId.setText(sharedpreferences.getString("email",""));
            mobile=sharedpreferences.getString("number","");
        }

      //  binding.tvUserEmailId.setText("-");
      //  binding.tvUserPhoneNember.setText(loginUser.getPhone());

        binding.lenMyAstroList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentViewAll = new Intent(mContext, ViewAllAstrologer.class);
                intentViewAll.putExtra("type", false);
                startActivity(intentViewAll);
            }
        });

        binding.lenMyConsultationAndPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentViewAll = new Intent(mContext, Wallethistory.class);
                intentViewAll.putExtra("type", false);
                startActivity(intentViewAll);
            }
        });

        binding.linevMyWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentViewAll = new Intent(mContext, RechargeWallet.class);
                intentViewAll.putExtra("type", false);
                startActivity(intentViewAll);
            }
        });

        binding.updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentViewAll = new Intent(mContext, ProfileUpdate.class);
                intentViewAll.putExtra("type", false);
                intentViewAll.putExtra("mobile",mobile);
                startActivity(intentViewAll);
            }
        });

        fetchastrologerdetails();
        fetchdata();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void fetchastrologerdetails()
    {
        SharedPreferences sharedpreferences =getActivity().getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("id", sharedpreferences.getString("id","0"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.ASTRODETAILS;
        Log.i("gdfgvgbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new com.android.volley.Response.Listener<JSONObject>()
                {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d("ghffffsfffg", response.toString());
                     /*   try {


                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                            if (jsonObject.getString("status").equals("1")){
                                JSONArray jsonArray = jsonObject.getJSONArray("records");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    astroid= obj.getString("id");
                                    astroname.setText(obj.getString("owner_name"));
                                    astrolang.setText(obj.getString("language"));
                                    astroexp.setText(obj.getString("experience")+" year");
                                    experties.setText(obj.getString("skill"));

                                    comments.setText(obj.getString("comment_randval")+"k mins");
                                    reports.setText(obj.getString("report_randval")+" reports");
                                    calls.setText(obj.getString("call_randval")+"k mins");

                                    longbio.setText(HtmlCompat.fromHtml(obj.getString("long_bio"), 0));
                                    ratting.setText(obj.getString("avg_rating"));

                                    float rating=1;
                                    try {
                                        rating= Float.parseFloat(obj.getString("avg_rating"));

                                    } catch (NumberFormatException e) {
                                    }
                                    rat.setRating(rating);
                                    online.setText(obj.getString("current_status"));
                                    if (obj.getString("current_status").equals("Online")){

                                    }else {

                                        chatoffline="1";
                                        chatmed.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
                                        chatbtn.setBackgroundResource(R.drawable.offlineme);
                                        chatme.setTextSize(12);
                                        chatprice.setTextSize(12);
                                        chatme.setTextColor(Color.parseColor("#ee534f"));
                                        chatprice.setTextColor(Color.parseColor("#ee534f"));
                                        perminchat.setTextColor(Color.parseColor("#ee534f"));
                                        if (obj.isNull("wait_time")){
                                            chatme.setText("Chat\nOffline");
                                        }else {
                                         *//*   String time = "2020-08-17'T'10:35:00";
                                            TimeAgo2 timeAgo2 = new TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);*//*
                                            String time = obj.getString("wait_time");
                                            Astrologerdetails.TimeAgo2 timeAgo2 = new Astrologerdetails.TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);
                                            chatme.setText("Chat\nWait Time\n"+MyFinalValue);

                                        }
                                    }
                                  *//*  if (obj.getString("current_status_call").equals("Online"))
                                    {

                                    }*//*
                                  *//*  else {
                                        calloffline="1";
                                        callmed.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
                                        callbtn.setBackgroundResource(R.drawable.offlineme);
                                        callme.setTextSize(12);
                                        permin.setTextColor(Color.parseColor("#ee534f"));
                                        callprice.setTextSize(12);
                                        callme.setTextColor(Color.parseColor("#ee534f"));
                                        callprice.setTextColor(Color.parseColor("#ee534f"));

                                        if (obj.isNull("wait_time_call")){
                                            callme.setText("Call\nOffline");
                                        }else {
                                            *//**//*String time = obj.getString("wait_time_call");
                                            TimeAgo2 timeAgo2 = new TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);*//**//*
                                            String time = obj.getString("wait_time_call");
                                            Astrologerdetails.TimeAgo2 timeAgo2 = new Astrologerdetails.TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);
                                            callme.setText("Call\nWait Time\n"+MyFinalValue);

                                        }

                                    }


                                    chatid = obj.getString("chat_token");
                                    chat_price_m = obj.getString("chat_price_m");
                                    call_price_m = obj.getString("call_price_m");

                                    chat_commission = obj.getString("chat_commission");
                                    call_commission = obj.getString("call_commission");

                                    if (obj.getString("chat_commission").equals(null)){

                                        int a = 0;
                                        int b = Integer.parseInt(chat_commission);
                                        int c = a+b;
                                        chatprice.setText("₹"+c);
                                    }else {
                                        int a = Integer.parseInt(obj.getString("chat_price_m"));
                                        int b = Integer.parseInt(obj.getString("chat_commission"));
                                        int c = a+b;
                                        chatprice.setText("₹"+c);
                                    }

                                    if (obj.getString("call_commission").equals(null)){

                                        int a = 0;
                                        int b = Integer.parseInt(call_commission);
                                        int c = a+b;
                                        callprice.setText("₹"+c);
                                    }else {
                                        int a = Integer.parseInt(obj.getString("call_price_m"));
                                        int b = Integer.parseInt(obj.getString("call_commission"));
                                        int c = a+b;
                                        callprice.setText("₹"+c);
                                    }

                                    image = obj.getString("image");

                                    Picasso.get().load(obj.getString("image"))
                                            .error(R.drawable.splash)
                                            .into(imgExpert);
                                }*//*

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
*/
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

                Log.i("Dfsdfsdf",error.toString());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy( 200*30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getActivity()).add(jsonObjectRequest);
    }


    private void fetchdata() {

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(getActivity());
        String url= RootURL.FETCHCALLINTAKEDORM+"?user_id="+sharedpreferences.getString("id", "");

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    Log.d("ghsdsdasdasdsdag", response.toString());

                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    JSONArray jsonArray = jsonObject.getJSONArray("records");

                    if(!String.valueOf(jsonArray.length()).equals("0"))
                    {

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            binding.tvUserName.setText(obj.getString("firstname"));
                            binding.lastname.setText(obj.getString("lastname"));
                            binding.mobile.setText(obj.getString("phone"));
                            binding.dob.setText(obj.getString("dob"));
                            binding.timeofbith.setText(obj.getString("tob"));
                            binding.placeofbirth.setText(obj.getString("city"));
                            binding.tvUserEmailId.setText(obj.getString("email"));
                            String Gender = obj.getString("gender");
                            if(Gender.equals("Male"))
                            {
                                binding.male.setChecked(true);
                                binding.male.setSelected(true);
                                binding.female.setChecked(false);
                                binding.female.setSelected(false);
                            }
                            else if(Gender.equals("Female"))
                            {
                                binding.male.setChecked(false);
                                binding.male.setSelected(false);
                                binding.female.setChecked(true);
                                binding.female.setSelected(true);
                            }


                        }

                    }
                    else {

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
//                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getActivity()).add(mStringRequest);
        mRequestQueue.add(mStringRequest);
    }


}
