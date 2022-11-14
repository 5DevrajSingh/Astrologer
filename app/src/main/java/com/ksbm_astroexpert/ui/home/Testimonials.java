package com.ksbm_astroexpert.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Adapter.TestimonialAdapter;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.Wallet.Offer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Testimonials extends AppCompatActivity {

    ImageView backarrow;
    RecyclerView testimonials;
    private List<Offer> imageData2=new ArrayList<>();
    String tstimonial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimonials);
        tstimonial = getIntent().getStringExtra("id");
        backarrow = findViewById(R.id.backarrow);
        testimonials = findViewById(R.id.testimonials);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        testimonials.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(Testimonials.this,1,GridLayoutManager.HORIZONTAL,false);
        testimonials.setLayoutManager(gridLayoutManager3);
        testimonials.setItemAnimator(new DefaultItemAnimator());
        testimonials();
    }

    private void testimonials() {
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("journey_id", tstimonial);
            //  json1.put("user_id",sharedpreferences.getString("id",""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.TESTIMONIALS;
        Log.i("gdfgvgbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, json1,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                             Log.d("ghdsdfsdfg", response.toString());

                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                            //    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if (jsonObject.getString("status").equals("1")){

                            /*    comments.setText(jsonObject.getString("comment")+"k mins");
                                reports.setText(jsonObject.getString("report")+" reports");
                                calls.setText(jsonObject.getString("call")+"k mins");*/

                                JSONArray jsonArray = jsonObject.getJSONArray("records");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    Offer id = new Offer();
                                    id.setname(obj.getString("name"));
                                    id.setscheduled(obj.getString("email"));
                                    id.setBannerSrc(obj.getString("description"));
                                    id.setscheduled_answer(obj.getString("journey"));
                                    id.setanswer(obj.getString("astrologer"));
                                    id.setuser_ans_verified(obj.getString("date_time"));
                                    imageData2.add(id);
                                }
                                TestimonialAdapter donationAdapter=new TestimonialAdapter(Testimonials.this,imageData2);
                                donationAdapter.notifyDataSetChanged();
                                testimonials.setAdapter(donationAdapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new com.android.volley.Response.ErrorListener() {
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

}