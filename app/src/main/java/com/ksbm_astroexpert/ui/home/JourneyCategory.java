package com.ksbm_astroexpert.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Adapter.EnquirymeAdapter;
import com.ksbm_astroexpert.Adapter.JourneyCategoryAdapter;
import com.ksbm_astroexpert.Astrologer.ImageDatad;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class JourneyCategory extends AppCompatActivity {
    private RecyclerView recyclerView;
    String astro_id="";
    private List<ImageDatad> imageData3=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_m_e);
        astro_id=getIntent().getStringExtra("astroid");
        recyclerView = findViewById(R.id.chat_recycler);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(JourneyCategory.this,1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager3);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        fetchastrologerexp();
    }

    private void fetchastrologerexp() {
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        final JSONObject json1 = new JSONObject();
        try {

            json1.put("user_id",sharedpreferences.getString("id",""));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.ENQUIRYTESTIMONIAL;
        Log.i("gdfgvgerererbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json1,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("gdsdddsdhg", response.toString());
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            //  if (jsonObject.getString("success").equals(200)){
                            JSONArray jsonArray = jsonObject.getJSONArray("records");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                ImageDatad id = new ImageDatad();
                                id.setId(obj.getString("id"));
                                id.setOwner_name(obj.getString("name"));
                                imageData3.add(id);
                            }

                            JourneyCategoryAdapter donationAdapter=new JourneyCategoryAdapter(getApplicationContext(),imageData3,astro_id);
                            donationAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(donationAdapter);

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

    public void backdd(View view) {
        onBackPressed();
    }
}