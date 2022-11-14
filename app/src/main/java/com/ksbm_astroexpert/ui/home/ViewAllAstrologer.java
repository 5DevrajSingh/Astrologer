package com.ksbm_astroexpert.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.procode.helper.JSONParserVolley;
import com.ksbm_astroexpert.ui.Wallet.RechargeWallet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ViewAllAstrologer extends AppCompatActivity {
    RecyclerView rvAstroList;
    TextView txtBalance;
    ImageView backarrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_astrologer);
        rvAstroList = findViewById(R.id.rvAstroList);
        txtBalance = findViewById(R.id.txtBalance);
        backarrow = findViewById(R.id.backarrow);
        astroDetails();
        walletbalnce();
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RechargeWallet.class);
                startActivity(intent);
            }
        });

    }
    List<AstroModel> mList = new ArrayList<>();
    private void astroDetails()
    {
        // mList = new ArrayList<>();
        String Api = RootURL.Base_URL+"/Astroksbmadmin/api/api/astrolist";
        final JSONObject parseJson = new JSONObject();
        try {
            parseJson.put("perpage","10");
            parseJson.put("pageno","1");


        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, Api, parseJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("fsfsdfsdfs",response.toString());
                        JSONParserVolley jVolley = new JSONParserVolley(response.toString());
                        JSONObject    json = jVolley.JSONParseVolley();
                        try
                        {


                            if(json.has("status")&&json.getString("status").equals("1"))
                            {
                                JSONArray jArray = json.getJSONArray("records");
                                Log.e("DtatCall",":2Game:"+jArray.toString());

                                for (int kk=0;kk<jArray.length();kk++)
                                {
                                    /*
                                    id": "45",
            "owner_name": " ankit jasiwal",
            "shop_name": " ankit jasiwal",
            "email": "ankitjaiswal228@gmail.com",
            "address": "test fsd fsdf",
            "phone": "9691930221",
            "current_status": "Online",
            "language": "language",
            "working_ex": "no",
            "call_price_m": "dsfsdfsdf22call_price_m",
            "chat_price_m": "10",
            "avg_rating": "0",
            "image": RootURL.Base_URL+"/Astroksbmadmin/admin/uploads/vendor/no_image.png"
                                     */
                                    JSONObject jsonData = jArray.getJSONObject(kk);
                                    mList.add(new AstroModel(
                                            jsonData.getString("id"),
                                            jsonData.getString("owner_name"),
                                            jsonData.getString("image"),
                                            jsonData.getString("shop_name"),
                                            jsonData.getString("language"),
                                            jsonData.getString("experience"),
                                            jsonData.getString("phone"),
                                            jsonData.getString("avg_rating"),
                                            jsonData.getString("call_price_m"),
                                            jsonData.getString("chat_price_m"),
                                            jsonData.getString("current_status"),
                                            jsonData.getString("current_status_call"),
                                            jsonData.getString("wait_time"),
                                            jsonData.getString("chat_commission"),
                                            jsonData.getString("call_commission"),
                                            jsonData.getString("device_token")
                                    ));
                                    Log.e("DttattaS","::"+jsonData.getString("owner_name"));

                                }

                                GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
                                rvAstroList.setLayoutManager(manager);
                                rvAstroList.setNestedScrollingEnabled(false);
                                rvAstroList.setAdapter(new ViewAllAstroAdapter(getApplicationContext(),mList,rvAstroList));
                                //loaderOurExperts.setVisibility(View.GONE);

                            }

                        } catch (JSONException e)
                        {
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


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
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);


    }


    private void walletbalnce() {

        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        Integer id = Integer.valueOf(sharedpreferences.getString("id", ""));
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("user_id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.WALLETBALANCE;
        Log.i("gdfgvgbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Log.d("ghg", response.toString());

                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                            if (jsonObject.getString("status").equals("1")){
                                // JSONArray jsonArray = jsonObject.getJSONArray("records");
                                JSONObject jsonObject1 = jsonObject.getJSONObject("records");
                                txtBalance.setText(jsonObject1.getString("wallet"));
                                Log.i("dfsdsd",jsonObject1.getString("wallet"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "There is seem to be network issue, check internet connection and try again", Toast.LENGTH_LONG).show();
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

}