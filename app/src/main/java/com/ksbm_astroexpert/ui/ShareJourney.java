package com.ksbm_astroexpert.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Chat.RegisterUser;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.ActivityShareJourneyBinding;
import com.ksbm_astroexpert.ui.home.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ShareJourney extends AppCompatActivity {

    String j_id="",journey_name="",astro_id="";
    private ActivityShareJourneyBinding binding;
    private String email="",name="",user_id="";
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_share_journey);
       setSupportActionBar(binding.toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        email =sharedpreferences.getString("email", "N/A");
        name =sharedpreferences.getString("username", "N/A");
        user_id =sharedpreferences.getString("id", "0");
        j_id=getIntent().getStringExtra("id");
        journey_name=getIntent().getStringExtra("name");
        astro_id=getIntent().getStringExtra("astroid");
        requestQueue= Volley.newRequestQueue(this);
        binding.journeyname.setText(journey_name);
        binding.email.setText(email);
        binding.name.setText(name);
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.desc.getText().toString().equals(""))
                {
                    Toast.makeText(ShareJourney.this, "Enter Journet here..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    sharejpounry();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return false;
    }

    private void sharejpounry()
    {
        final ProgressDialog progress = new ProgressDialog(ShareJourney.this);
        progress.setMessage("Uploading,please wait...");
        progress.setCancelable(false);
        progress.show();
        String url= RootURL.share_journey;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progress.dismiss();
                Log.d("dsdwdwdwdw",response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getInt("status")==200)
                    {
                        Toast.makeText(ShareJourney.this, "Journey shared!", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                Log.d("ssdsddwsdwsd",error.getMessage());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                map.put("user_id",user_id);
                map.put("astro",astro_id);
                map.put("journey_id",j_id);
                map.put("message",binding.desc.getText().toString());
                Log.d("mapisis","="+map.toString());
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

    }

}