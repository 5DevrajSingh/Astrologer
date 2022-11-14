package com.ksbm_astroexpert.ui.Wallet;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Chat.models.CustomerTransactionModel;
import com.ksbm_astroexpert.Chat.utils.ParseManager;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.home.HomeActivity;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Wallethistory extends AppCompatActivity {
    private List<Offer> imageData2=new ArrayList<>();
    private ArrayList<CustomerTransactionModel.Response> arrListTransHistory=new ArrayList<>();
    private RecyclerView recyclerviewplans;
    ImageView support;
    public Context context=this;
    int user_id;
    String from_Date="",to_Date="";
    TextView fromdate,todate,search,text_available_balance,textNoRecord;
    String name[]={"The Deduction balance +1000","The Deduction balance +1000","The Deduction balance +2000","The Deduction balance -100","The Deduction balance +10000","The Deduction balance +1100",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallethistory);
        recyclerviewplans = findViewById(R.id.recyclerviewplans);
        fromdate = findViewById(R.id.fromdate);
        todate = findViewById(R.id.todate);
        search = findViewById(R.id.search);
        textNoRecord = findViewById(R.id.textNoRecord);
        text_available_balance = findViewById(R.id.text_available_balance);
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        user_id = Integer.valueOf(sharedpreferences.getString("id", ""));

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        //fetchplan(date,date);
        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog picker;
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Wallethistory.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fromdate.setText( year+ "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog picker;
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Wallethistory.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                todate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               from_Date=fromdate.getText().toString();
               to_Date=todate.getText().toString();
               if (fromdate.getText().toString().equals("")&&todate.getText().toString().equals("")){
                   Toast.makeText(Wallethistory.this, "Please enter date", Toast.LENGTH_SHORT).show();

               }else {
                   hitCustomerTransation(user_id);
               }
            }
        });

//        recyclerviewplans.setHasFixedSize(true);
//        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(this,1, GridLayoutManager.VERTICAL,false);//
//        recyclerviewplans.setLayoutManager(gridLayoutManager3);
//        recyclerviewplans.setItemAnimator(new DefaultItemAnimator());

        hitCustomerTransation(user_id);
        walletbalnce();
    }

/*
    private void recycler() {
        imageData2.clear();
        for (int i = 0; i < name.length; i++) {
            Offer main_model = new Offer();
            main_model.setname(name[i]);
            imageData2.add(main_model);
        }

        recyclerviewplans.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerviewplans.setLayoutManager(layoutManager);
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.recyclerview_anim_layout);
        recyclerviewplans.setLayoutAnimation(layoutAnimationController);
        CustomerTransHistoryAdapter donationAdapter=new CustomerTransHistoryAdapter(getApplicationContext(),arrListTransHistory);
        recyclerviewplans.setAdapter(donationAdapter);

    }
*/
/*
    private void fetchplan(String date1,String date2) {
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, MODE_PRIVATE);
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("perpage", "50");
            json1.put("pageno","1");
            json1.put("user_id",sharedpreferences.getString("id",""));
            json1.put("start_date",date1);
            json1.put("end_date",date2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.USERHISTORY;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ghgswwdw", response.toString());
                            Log.i("gdfgvddfgbnn", url+json1.toString());

                            Toast.makeText(Wallethistory.this, "No Wallet history found", Toast.LENGTH_SHORT).show();

                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                          //  Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if (jsonObject.getString("status").equals("1")){
                                JSONArray jsonArray = jsonObject.getJSONArray("records");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    Offer id = new Offer();
                                    id.setBannerId(obj.getString("astro_name"));
                                    id.setname(obj.getString("chat_call"));
                                    id.setdescription(obj.getString("created_at"));
                                    id.settone(obj.getString("astro_phone"));
                                    id.setcost(obj.getString("amount"));
                                    id.setanswer(obj.getString("astro_email"));
                                    imageData2.add(id);
                                }
                                CustomerTransHistoryAdapter donationAdapter=new CustomerTransHistoryAdapter(getApplicationContext(),imageData2);
                                donationAdapter.notifyDataSetChanged();
                                LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(Wallethistory.this, R.anim.recyclerview_anim_layout);
                                recyclerviewplans.setLayoutAnimation(layoutAnimationController);
                                recyclerviewplans.setAdapter(donationAdapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                Log.i("Dfsdfsdf",error.toString());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //imageEncoded=getStringImage(bitmap);
                //Log.i("image", encodedImageList.toString());
                Map<String, String> params = new Hashtable<String, String>();
                //params.put("imagefile", encodedImageList.toString());
                // image = getStringImage(bitmap);
                //  System.out.print("Check" + image);
                //params.put("mobile", "9799569458");
                // params.put("",json1.toString());
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy( 200*30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);
    }
*/



    public void bebad(View view) {
        onBackPressed();
    }

    public void recharge(View view) {
        Intent blogDetailIntent = new Intent(Wallethistory.this, RechargeWallet.class);
        startActivity(blogDetailIntent);
    }

/*
    private void hitRemidesApi() {
     SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, MODE_PRIVATE);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RootURL.CUSUMER_HISTORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("checkressponseee", response);
                try {
                    AllRemidesModel model = ParseManager.getInstance().fromJSON(response, AllRemidesModel.class);
                    if (model != null) {
                        if (model.status.equals("1")){
                            arrListRemides=model.response;
                            Log.d("chearrlist", String.valueOf(arrListRemides.size()));
                            if (!arrListRemides.isEmpty()){
                                setRecyclerRemedies();
                            }else {
                                textNoRecord.setVisibility(View.VISIBLE);
                            }

                        }else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
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
                hashMap.put("customer_id", sharedpreferences.getString("id",""));
                Log.d("ddddfsad", String.valueOf(hashMap));
                return hashMap;
            }
        };
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }
*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Wallethistory.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void hitCustomerTransation(int user_id) {
    RequestQueue requestQueue = Volley.newRequestQueue(context);
    StringRequest stringRequest = new StringRequest(Request.Method.POST, RootURL.CUSUMER_HISTORY, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d("resssss", response);
            try {
                CustomerTransactionModel model = ParseManager.getInstance().fromJSON(response, CustomerTransactionModel.class);
                if (model != null) {
                    if (model.status.equals("1")){

                        arrListTransHistory=model.response;
                        Log.d("chearrlist", String.valueOf(arrListTransHistory.size()));
                        if (!arrListTransHistory.isEmpty()){
                            setRecycler();
                        }else {
                            textNoRecord.setVisibility(View.VISIBLE);
                        }

                    }else {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
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
                hashMap.put("customer_id", String.valueOf(user_id));
                hashMap.put("startDate",to_Date);
                hashMap.put("endDate", from_Date);
            Log.d("sddflkjs", String.valueOf(hashMap));
            return hashMap;
        }
    };
    requestQueue.getCache().clear();
    requestQueue.add(stringRequest);
}
public void setRecycler(){
    recyclerviewplans.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
    recyclerviewplans.setLayoutManager(layoutManager);
    LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.recyclerview_anim_layout);
    recyclerviewplans.setLayoutAnimation(layoutAnimationController);
    CustomerTransHistoryAdapter donationAdapter=new CustomerTransHistoryAdapter(getApplicationContext(),arrListTransHistory);
    recyclerviewplans.setAdapter(donationAdapter);
}

    private void walletbalnce() {
    SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
    Integer id = Integer.valueOf(sharedpreferences.getString("id", ""));
    final JSONObject json1 = new JSONObject();
    try {
        json1.put("user_id", id);
    } catch (JSONException e) {
        e.printStackTrace();
    }
    final String url = RootURL.WALLETBALANCE;
    Log.i("gdfgvgbnn", url + json1.toString());
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
            new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        // Log.d("ghg", response.toString());

                        JSONObject jsonObject = new JSONObject(String.valueOf(response));

                        if (jsonObject.getString("status").equals("1")) {
                            // JSONArray jsonArray = jsonObject.getJSONArray("records");
                            JSONObject jsonObject1 = jsonObject.getJSONObject("records");
                            text_available_balance.setText("Available Balance is " + "₹" + jsonObject1.getString("wallet"));
                            Log.i("dfsdsd", jsonObject1.getString("wallet"));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            Log.i("Dfsdfsdf", error.toString());
        }
    }) {

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            //imageEncoded=getStringImage(bitmap);
            //Log.i("image", encodedImageList.toString());
            Map<String, String> params = new Hashtable<String, String>();
            //params.put("imagefile", encodedImageList.toString());
            // image = getStringImage(bitmap);
            //  System.out.print("Check" + image);
            //params.put("mobile", "9799569458");
            // params.put("",json1.toString());
            return params;
        }
    };
    jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);
}



}