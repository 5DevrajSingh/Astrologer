package com.ksbm_astroexpert.ui.Review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ADDRatting extends AppCompatActivity {

    String productimage,IdHolder,productid;
    RatingBar ratebar;
    TextView tvrate,name;
    EditText etreview;
    Button btnreview;
    String review="";
    String rateing,id,chat_id;
    ImageView imageViewback;
    CircleImageView ivProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_d_d_ratting);
        id = getIntent().getStringExtra("id");
        chat_id = getIntent().getStringExtra("chat_id");
        imageViewback=findViewById(R.id.back);
        name = findViewById(R.id.name);
        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        chathistory();
        ivProfile = findViewById(R.id.ivProfile);
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
          try {
              Picasso.get().load(sharedpreferences.getString("user_profile_image", "0"))
                      .placeholder(R.drawable.splash)
                      .into(ivProfile);
          }
          catch ( Exception e)
          {

          }

        name.setText(sharedpreferences.getString("username",""));
        etreview=findViewById(R.id.txtreview);
        btnreview=findViewById(R.id.btnreview);
        ratebar= findViewById(R.id.rate1);
        tvrate= findViewById(R.id.tvrate1);
        btnreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();

            }
        });

    }
    public void getdata(){
        review=etreview.getText().toString();
        Float ratingvalue = ratebar.getRating() ;
        tvrate.setText(""+ ratingvalue);
        rateing=tvrate.getText().toString();
        review();
    }

    private void review() {

        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

        final JSONObject json1 = new JSONObject();
        try {
            json1.put("user_id",sharedpreferences.getString("id", ""));
            json1.put("vendor_id",id);
            json1.put("listing_id","1");
            json1.put("child_cat_id","1");
            json1.put("star",rateing);
            json1.put("comments",review);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.ADDREVIEW;
        Log.i("gdfgvgbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ghsdsdasdasdsdag", response.toString());

                            JSONObject jsonObject = new JSONObject(String.valueOf(response));


                            Intent chatIntent = new Intent(ADDRatting.this, HomeActivity.class);
                            chatIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            chatIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(chatIntent);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
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

    @Override
    public void onBackPressed() {
        Intent chatIntent = new Intent(ADDRatting.this, HomeActivity.class);
        chatIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        chatIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(chatIntent);
        finish();

    }

    private void chathistory() {
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

        String currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        final String URLALL=RootURL.Base_URL+"/Astroksbmadmin/user/chat_out.php?chat_id="+chat_id+"&out_time="+currentDateAndTime;

        Log.d("ABCsds",URLALL);
        StringRequest request=new StringRequest(Request.Method.GET,URLALL.replaceAll(" ","%20"),
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        Log.d("ABddsdsCsds",response.toString());

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(String.valueOf(response));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "No internet connection"+error, Toast.LENGTH_LONG).show();

                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //image=getStringImage(bitmap);

                //Log.i("image", image);

                Map<String, String> params = new Hashtable<String, String>();


                return params;
            }
        };
        requestQueue.add(request);

    }


}