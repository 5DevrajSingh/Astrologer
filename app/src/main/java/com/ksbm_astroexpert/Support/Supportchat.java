package com.ksbm_astroexpert.Support;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Adapter.Supportchatadapter;
import com.ksbm_astroexpert.Astrologer.ImageDatad;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Supportchat extends AppCompatActivity {

    private RecyclerView recyclerView;
    ImageView chat_send_picture;
    RelativeLayout message_relative_left;
    private final int PICK_IMAGE = 12345;
    Bitmap bitmap;
    String uploadimg;
    EditText edt;
    private List<ImageDatad> imageData3=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supportchat);
        edt = findViewById(R.id.chat_message);
        chat_send_picture = findViewById(R.id.chat_send_picture);
        FloatingActionButton chat_send = findViewById(R.id.chat_send);
        recyclerView = findViewById(R.id.chat_recycler);
        message_relative_left = findViewById(R.id.message_relative_left);

        chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt.equals("")){
                    Toast.makeText(Supportchat.this, "Please enter your concern", Toast.LENGTH_SHORT).show();
                }else {
                    senddata(edt.getText().toString());
                }
            }
        });

        chat_send_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PICK_IMAGE);
            }
        });

        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(Supportchat.this,1,GridLayoutManager.VERTICAL,false);
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
        final String url = RootURL.SUPPORTCHAT;
        Log.i("gdfgvgerererbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json1,
                new com.android.volley.Response.Listener<JSONObject>() {
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
                                id.setOwner_name(obj.getString("description"));
                                id.setImage(obj.getString("attachment"));
                                imageData3.add(id);

                            }
                            Supportchatadapter donationAdapter=new Supportchatadapter(getApplicationContext(),imageData3);
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


    private void senddata(String toString) {
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        String currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

        final JSONObject json1 = new JSONObject();
        try {
            json1.put("user_id", sharedpreferences.getString("id", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.SUPPORT+"user_id="+sharedpreferences.getString("id", "")
                +"&title="+"AstroExpert Querry"
                +"&state="+"1"
                +"&amt=123"
                +"&city="+"475"
                +"&description="+edt.getText().toString()
                +"&mobile="+sharedpreferences.getString("phone", "")
                +"&for_which="+"education"
                +"&cat_id=1"
                +"&sub_cat_id=4"
                +"&date="+currentDateAndTime;
        Log.i("gdfgvgbnn", url.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST
                , url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ghsdsdasdasdsdag", response.toString());

                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            Intent privacyIntent = new Intent(Supportchat.this, Supportchat.class);
                            startActivity(privacyIntent);
                            Toast.makeText(Supportchat.this, "Query Submitted", Toast.LENGTH_SHORT).show();

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
                params.put("attachment", uploadimg);
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy( 200*30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    uploadimg=getStringImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));
                    getStringImage(bitmap);
                    //Firebase code

                    Uri url = data.getData();
                    //Uploading selected picture

                } catch (FileNotFoundException e) {
                    e.printStackTrace();

            }
        }
        }

    }
    public String getStringImage(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.d("abcd",encodedImage);
        return encodedImage;

    }

    public void backdd(View view) {
    onBackPressed();
    }
}