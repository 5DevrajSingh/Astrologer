package com.ksbm_astroexpert.ui.Astrodetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.tool.util.L;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ksbm_astroexpert.Adapter.RattingAdapter;
import com.ksbm_astroexpert.Adapter.ReviewAdapter;
import com.ksbm_astroexpert.Astrologer.Astrologer;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.ModelClass.RatingModels;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.Support.Supportchat;
import com.ksbm_astroexpert.ui.Wallet.Offer;
import com.ksbm_astroexpert.ui.Wallet.RechargeWallet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.ksbm_astroexpert.Constant.Constant.astroModelList;

public class Astrologerdetails extends AppCompatActivity implements View.OnClickListener {
    TextView astroname,astrolang,astroexp,experties,ratting,chatprice,callprice,online,txtBalance,count_ratting;

    List<RatingModels> list = new ArrayList<>();

    ImageView imgExpert,backpress;
    TextView longbio,comments,calls,reports;
    String id="";
    private final String TAG = "CA/WelcomeActivity";
    String chatid,chat_price_m,call_price_m,userbanlance,astroid="";
    RelativeLayout chatbtn,callbtn;
    TextView  btShowmore;
    String image;
    private ProgressBar progres;
    String astro_token="";
    private List<Offer> imageData2=new ArrayList<>();
    private RecyclerView recyclerviewplans,review;
    AppCompatRatingBar rat;
    ImageView support,imgSupport;
    TextView callme,chatme,permin,perminchat;
    ImageView callmed,chatmed;
    String chatoffline,calloffline,call_commission,chat_commission,user_mobile;
    int userid=0;

    RecyclerView recyclerviewratting;

    String WhatsAppNo= "+919991900369";

    LinearLayout viewWallet1;


    public static String chatid1,chat_price_m1,call_price_m1,astroid1,id1,image1,lang1,userbanlance1,astroname1,chat_commission1,call_commission1,astro_token1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_astrologerdetails);
        setContentView(R.layout.activity_astrologerdetails_backup_26_08_20);
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        userid = Integer.valueOf(sharedpreferences.getString("id", "0"));
        user_mobile=sharedpreferences.getString("number", "0");
       // Toast.makeText(Astrologerdetails.this,MyFinalValue, Toast.LENGTH_SHORT).show();


//        Toast.makeText(this, "check", Toast.LENGTH_SHORT).show();




        chatoffline = "0";
        calloffline = "0";
        final Intent i = getIntent();
        id = i.getStringExtra("astroid");
        astro_token=i.getStringExtra("astro_token");
        Log.d("astro_token",astro_token+"    " +id);


        recyclerviewratting = findViewById(R.id.recyclerviewratting);
        astroname = findViewById(R.id.astroname);
        astrolang = findViewById(R.id.astrolang);
        astroexp = findViewById(R.id.astroexp);
        experties = findViewById(R.id.experties);
        longbio = findViewById(R.id.longbio);
        ratting = findViewById(R.id.ratting);
        count_ratting=findViewById(R.id.count_ratting);
        backpress=findViewById(R.id.backpress);
        backpress.setOnClickListener(this);
        chatme = findViewById(R.id.chatme);
        callme = findViewById(R.id.callme);
        progres=findViewById(R.id.progres);
        callmed = findViewById(R.id.callmed);
        chatmed = findViewById(R.id.chatmed);

        permin = findViewById(R.id.permin);
        perminchat = findViewById(R.id.perminchat);

        imgExpert = findViewById(R.id.imgExpert);
        chatprice = findViewById(R.id.chatprice);
        callprice = findViewById(R.id.callprice);
        online = findViewById(R.id.online);
        viewWallet1 = findViewById(R.id.viewWallet1);
        txtBalance = findViewById(R.id.txtBalance);
        rat = findViewById(R.id.rat);
        btShowmore= findViewById(R.id.btShowmore);

        comments= findViewById(R.id.comments);
        calls= findViewById(R.id.calls);
        reports= findViewById(R.id.reports);

        callbtn= findViewById(R.id.callbtn);
        chatbtn= findViewById(R.id.chatbtn);




        fetchastrologerdetails();
        walletbalnce();

        // For using character length
        //number of line you want to short

        longbio.setMaxLines(3);
        btShowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btShowmore.getText().toString().equalsIgnoreCase("Read more"))
                {
                    longbio.setMaxLines(Integer.MAX_VALUE);//your TextView
                    btShowmore.setText("Read less");
                }
                else
                {
                    longbio.setMaxLines(3);//your TextView
                    btShowmore.setText("Read more");
                }
            }
        });
        viewWallet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RechargeWallet.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (calloffline=="1")
                {
                    Toast.makeText(Astrologerdetails.this, "Please wait for astrologer to come online", Toast.LENGTH_SHORT).show();
                } else if (calloffline=="2")
                {
                    Toast.makeText(Astrologerdetails.this, "Astrologer seems busy, Please try after some time.", Toast.LENGTH_SHORT).show();
                }
                else {
                    //==============commented  by abhinav shukla date is 27_08_20  ==========


                    chatid1=chatid;
                    chat_price_m1=chat_price_m;
                    call_price_m1=call_price_m;
                    astroid1=astroid;
                    id1=id;
                    image1=image;
                    lang1=(astrolang.getText().toString()).replaceAll(", ",",");
                    userbanlance1=userbanlance;
                    astroname1=astroname.getText().toString();
                    chat_commission1=chat_commission;
                    call_commission1=call_commission;
                    astro_token1=astro_token;



                    Intent chatIntent = new Intent(Astrologerdetails.this, Astrologer.class);
                    chatIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    chatIntent.putExtra("userid", chatid);
                    chatIntent.putExtra("chatprice", chat_price_m);
                    chatIntent.putExtra("astroid", astroid);
                    chatIntent.putExtra("callprice", call_price_m);
                    chatIntent.putExtra("image", image);
                    chatIntent.putExtra("userbalance", userbanlance);
                    chatIntent.putExtra("astroname", astroname.getText().toString());
                    chatIntent.putExtra("id", id);
                    chatIntent.putExtra("lang", astrolang.getText().toString());
                    chatIntent.putExtra("type", "call");
                    chatIntent.putExtra("chat_commission", chat_commission);
                    chatIntent.putExtra("call_commission", call_commission);
                    chatIntent.putExtra("userbalance", userbanlance);
                    chatIntent.putExtra("type", "call");
                    startActivity(chatIntent);

//                    alertDialog(id,userid,user_mobile);


                }

            }
        });

        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chatoffline=="1"){
                    Toast.makeText(Astrologerdetails.this, "Please wait for astrologer to come online", Toast.LENGTH_SHORT).show();
                }else if (chatoffline=="2")
                {
                    Toast.makeText(Astrologerdetails.this, "Astrologer seems busy, Please try after some time.", Toast.LENGTH_SHORT).show();
                } else {

                    chatid1=chatid;
                    chat_price_m1=chat_price_m;
                    call_price_m1=call_price_m;
                    astroid1=astroid;
                    id1=id;
                    image1=image;
                    lang1=(astrolang.getText().toString()).replaceAll(", ",",");
                    userbanlance1=userbanlance;
                    astroname1=astroname.getText().toString();
                    chat_commission1=chat_commission;
                    call_commission1=call_commission;
                    astro_token1=astro_token;

                    SharedPreferences sharedPreferences = getSharedPreferences("RequestDetail", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("IsAdded",true);
                    editor.putString("chatid",chatid);
                    editor.putString("chat_price_m",chat_price_m);
                    editor.putString("call_price_m",call_price_m);
                    editor.putString("astroid",astroid);
                    editor.putString("id1",id);
                    editor.putString("image",image);
                    editor.putString("lang",lang1);
                    editor.putString("userbanlance",userbanlance);
                    editor.putString("astroname",astroname1);
                    editor.putString("chat_commission",chat_commission);
                    editor.putString("call_commission",call_commission);
                    editor.putString("astro_token",astro_token);
                    editor.commit();


                    Intent chatIntent = new Intent(Astrologerdetails.this, Astrologer.class);
                    chatIntent.putExtra("userid", chatid);
                    chatIntent.putExtra("chatprice", chat_price_m);
                    chatIntent.putExtra("callprice", call_price_m);
                    chatIntent.putExtra("astroid", astroid);
                    chatIntent.putExtra("id", id);
                    chatIntent.putExtra("image", image);
                    chatIntent.putExtra("lang", astrolang.getText().toString());
                    chatIntent.putExtra("userbalance", userbanlance);
                    chatIntent.putExtra("astroname", astroname.getText().toString());
                    chatIntent.putExtra("type", "chat");
                    chatIntent.putExtra("chat_commission", chat_commission);
                    chatIntent.putExtra("call_commission", call_commission);
                    chatIntent.putExtra("astro_token", astro_token);
                    startActivity(chatIntent);
                }
                /*Intent chatIntent = new Intent(Astrologerdetails.this, ChatActivity.class);
                chatIntent.putExtra("userid", chatid);
                chatIntent.putExtra("chatprice", chat_price_m);
                chatIntent.putExtra("callprice", call_price_m);
                chatIntent.putExtra("userbalance", userbanlance);
                startActivity(chatIntent);*/
            }
        });


        imgSupport = findViewById(R.id.imgSupport);

        imgSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geotowhapp();
            }
        });

        recyclerviewplans = findViewById(R.id.recyclerviewplans);
        recyclerviewplans.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(this,1,GridLayoutManager.HORIZONTAL,false);
        recyclerviewplans.setLayoutManager(gridLayoutManager3);
        recyclerviewplans.setItemAnimator(new DefaultItemAnimator());
        fetchplan();


    /*    review = findViewById(R.id.review);
        review.setHasFixedSize(false);
        GridLayoutManager gridLayoutManager4 = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        review.setLayoutManager(gridLayoutManager4);
        gridLayoutManager4.setReverseLayout(true);
        review.setItemAnimator(new DefaultItemAnimator());

        fetchreview();*/
    }




    private void geotowhapp() {

        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
        Uri uri;
        if (isWhatsappInstalled) {

//            Intent sendIntent = new Intent(Intent.ACTION_SENDTO,   uri = Uri.parse("smsto:" + "9560393886"));
//            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hai Good Morning");
//            sendIntent.setType("text/plain");
//            sendIntent.setPackage("com.whatsapp");
//            startActivity(sendIntent);
            PackageManager packageManager = this.getPackageManager();
            Intent i = new Intent(Intent.ACTION_VIEW);

            try {
                String url = "https://api.whatsapp.com/send?phone="+ WhatsAppNo +"&text=" + URLEncoder.encode("Hello,\n", "UTF-8");
                i.setPackage("com.whatsapp");
                i.setData(Uri.parse(url));
                if (i.resolveActivity(packageManager) != null) {
                    this.startActivity(i);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "WhatsApp not Installed",
                    Toast.LENGTH_SHORT).show();
            uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goToMarket);

        }
    }
    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }


    private void fetchreview() {
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, MODE_PRIVATE);
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("astro_id",astroid +"");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        final String url = RootURL.REVIEW+"type="+"astrologer"+"&id="+id;
        Log.d("checkjsdj",url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ghg", response.toString());
                            Log.i("gdfgvgbnn", url+json1.toString());
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            //    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if (jsonObject.getString("success").equals("200")){

                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    Offer id = new Offer();
                                    id.setBannerId(obj.getString("star"));
                                    id.setname(obj.getString("username"));
                                    id.setdescription(obj.getString("rating_comment"));
                                    imageData2.add(id);
                                }
                                count_ratting.setText("Reviews ("+imageData2.size()+" ratings)");
                                ReviewAdapter donationAdapter=new ReviewAdapter(getApplicationContext(),imageData2);
                                donationAdapter.notifyDataSetChanged();
                                review.setAdapter(donationAdapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             //   Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

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

    private void fetchplan() {
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, MODE_PRIVATE);
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("astro_id","45" +
                    "");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.ASTROLOGERREMIDES+"astro_id="+id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ghdfdfdg", response.toString());
                            Log.i("gdfdfdfdffgvgbnn", url+json1.toString());


                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                        //    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if (jsonObject.getString("success").equals("200")){

                            /*    comments.setText(jsonObject.getString("comment")+"k mins");
                                reports.setText(jsonObject.getString("report")+" reports");
                                calls.setText(jsonObject.getString("call")+"k mins");*/

                                imageData2.clear();
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    Offer id = new Offer();
                                    id.setBannerId(obj.getString("remedies_name"));
                                    imageData2.add(id);
                                }
                                RemidesAdapter donationAdapter=new RemidesAdapter(getApplicationContext(),imageData2);
                                donationAdapter.notifyDataSetChanged();
                                recyclerviewplans.setAdapter(donationAdapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

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


    private void fetchastrologerdetails()
    {
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.ASTRODETAILS;
        Log.d("gdfgvgbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new Response.Listener<JSONObject>()
                {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                             Log.d("ghffffdffffg", response.toString());

                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                            if (jsonObject.getString("status").equals("1")){

                                JSONArray jsonArray = jsonObject.getJSONArray("records");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    astroid= obj.getString("id");
                                    astroname.setText(obj.getString("owner_name"));
                                    astrolang.setText((obj.getString("language")).replaceAll(",",", "));
                                    astroexp.setText(obj.getString("experience")+" year");

                                    try
                                    {
                                        experties.setText(obj.getString("skill").toString().replaceAll(",",", "));
                                    }
                                    catch (Exception e)
                                    {
                                        experties.setText("");
                                    }

                                    comments.setText(obj.getString("comment_randval")+"k mins");
                                    reports.setText(obj.getString("report_randval")+" reports");
                                    calls.setText(obj.getString("call_randval")+"k mins");

                                    longbio.setText(HtmlCompat.fromHtml(obj.getString("long_bio"), 0));
                                    ratting.setText(obj.getString("avg_rating"));

                                    float rating=1;
                                    try {
                                        rating= Float.parseFloat(obj.getString("avg_rating"));

                                    } catch (NumberFormatException e) {
                                        rating=1;
                                    }
                                    rat.setRating(rating);


                                    ////////////Ratting

                                    try {
                                        JSONArray rattingarray = obj.getJSONArray("rating");

                                        Log.e("rattingArray",rattingarray.toString());

                                        for(int j=0;j<rattingarray.length();j++)
                                        {
                                            JSONObject obj1 = rattingarray.getJSONObject(i);
                                            RatingModels ratt = new RatingModels();
                                            //ratt.setChildCatId(obj1.getString("ChildCatId"));
                                            ratt.setComments("");

                                            ratt.setComments(obj1.getString("comments"));
                                            //ratt.setListingId(obj1.getString("ListingId"));
                                            //ratt.setRatingId(obj1.getString("RatingId"));
                                            ratt.setStar(obj1.getString("star"));
                                            ratt.setUsername(obj1.getString("username"));
                                            list.add(ratt);
                                        }

                                        Toast.makeText(Astrologerdetails.this, String.valueOf(list.size()), Toast.LENGTH_SHORT).show();

                                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                                        recyclerviewratting.setLayoutManager(layoutManager);
                                        recyclerviewratting.setHasFixedSize(true);

                                        RattingAdapter relatedProductAdapter = new RattingAdapter(getApplicationContext(), list);
                                        recyclerviewratting.setAdapter(relatedProductAdapter);
                                        recyclerviewratting.setVisibility(View.VISIBLE);

                                    }
                                    catch (Exception e)
                                    {
                                        Toast.makeText(Astrologerdetails.this, e.toString(), Toast.LENGTH_SHORT).show();
                                    }

                                    online.setText(obj.getString("current_status"));

                                    if (obj.getString("current_status").equals("Online")){

                                        chatbtn.setBackgroundResource(R.color.orange);
                                        chatme.setTextSize(12);
                                        chatprice.setTextSize(12);
//                                        chatme.setTextColor(Color.parseColor("#ee534f"));
//                                        chatprice.setTextColor(Color.parseColor("#ee534f"));
//                                        perminchat.setTextColor(Color.parseColor("#ee534f"));
                                        chatme.setText("Chat");

                                    }else if (obj.getString("current_status").equals("Busy"))
                                    {
                                        chatoffline="2";
                                        //chatmed.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                                        chatbtn.setBackgroundResource(R.color.orange);
                                        chatme.setTextSize(12);
                                        chatprice.setTextSize(12);
//                                        chatme.setTextColor(Color.parseColor("#ee534f"));
//                                        chatprice.setTextColor(Color.parseColor("#ee534f"));
//                                        perminchat.setTextColor(Color.parseColor("#ee534f"));
                                        chatme.setText("Busy");
                                        if (obj.isNull("wait_time")){

                                        }else {
                                         /*   String time = "2020-08-17'T'10:35:00";
                                            TimeAgo2 timeAgo2 = new TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);*/
                                            String time = obj.getString("wait_time");
                                            TimeAgo2 timeAgo2 = new TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);
                                            //chatme.setText("Chat Wait Time "+MyFinalValue);

                                        }
                                    } else {

                                        chatoffline="1";
                                        //chatmed.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                                        chatbtn.setBackgroundResource(R.color.gray_btn_bg_color);
                                        chatme.setTextSize(12);
                                        chatprice.setTextSize(12);
//                                        chatme.setTextColor(Color.parseColor("#ee534f"));
//                                        chatprice.setTextColor(Color.parseColor("#ee534f"));
//                                        perminchat.setTextColor(Color.parseColor("#ee534f"));
                                        chatme.setText("Offline");
                                        if (obj.isNull("wait_time")){

                                        }else {
                                         /*   String time = "2020-08-17'T'10:35:00";
                                            TimeAgo2 timeAgo2 = new TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);*/
                                            String time = obj.getString("wait_time");
                                            TimeAgo2 timeAgo2 = new TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);
                                            //chatme.setText("Chat\nWait Time\n"+MyFinalValue);

                                        }
                                    }

                                    if (obj.getString("current_status_call").equals("Online"))
                                    {
                                        callbtn.setBackgroundResource(R.color.orange);
                                        callme.setTextSize(12);
                                        //permin.setTextColor(Color.parseColor("#ee534f"));
                                        callprice.setTextSize(12);
                                        //callme.setTextColor(Color.parseColor("#ee534f"));
                                        //callprice.setTextColor(Color.parseColor("#ee534f"));
                                        callme.setText("Call");
                                    }
                                    else if (obj.getString("current_status_call").equals("Busy"))
                                    {
                                        calloffline="2";
                                        //callmed.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                                        callbtn.setBackgroundResource(R.color.orange);
                                        callme.setTextSize(12);
                                        //permin.setTextColor(Color.parseColor("#ee534f"));
                                        callprice.setTextSize(12);
                                        //callme.setTextColor(Color.parseColor("#ee534f"));
                                        //callprice.setTextColor(Color.parseColor("#ee534f"));
                                        callme.setText("Busy");
                                        if (obj.isNull("wait_time_call")){

                                        }else {
                                            /*String time = obj.getString("wait_time_call");
                                            TimeAgo2 timeAgo2 = new TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);*/
                                            String time = obj.getString("wait_time_call");
                                            TimeAgo2 timeAgo2 = new TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);
                                            //callme.setText("Call\nWait Time\n"+MyFinalValue);

                                        }
                                    }
                                    else {
                                        calloffline="1";
                                        //callmed.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                                        callbtn.setBackgroundResource(R.color.gray_btn_bg_color);
                                        callme.setTextSize(12);
                                        //permin.setTextColor(Color.parseColor("#ee534f"));
                                        callprice.setTextSize(12);
//                                        callme.setTextColor(Color.parseColor("#ee534f"));
//                                        callprice.setTextColor(Color.parseColor("#ee534f"));
                                        callme.setText("Offline");
                                        if (obj.isNull("wait_time_call")){

                                        }else {
                                            /*String time = obj.getString("wait_time_call");
                                            TimeAgo2 timeAgo2 = new TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);*/
                                            String time = obj.getString("wait_time_call");
                                            TimeAgo2 timeAgo2 = new TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);
                                            //callme.setText("Call\nWait Time\n"+MyFinalValue);

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
                                        chatprice.setText("₹"+c+"/min");
                                    }else {
                                        int a = Integer.parseInt(obj.getString("chat_price_m"));
                                        int b = Integer.parseInt(obj.getString("chat_commission"));
                                        int c = a+b;
                                        chatprice.setText("₹"+c+"/min");
                                    }

                                    if (obj.getString("call_commission").equals(null)){

                                        int a = 0;
                                        int b = Integer.parseInt(call_commission);
                                        int c = a+b;
                                        callprice.setText("₹"+c+"/min");
                                    }else {
                                        int a = Integer.parseInt(obj.getString("call_price_m"));
                                        int b = Integer.parseInt(obj.getString("call_commission"));
                                        int c = a+b;
                                        callprice.setText("₹"+c+"/min");
                                    }

                                    image = obj.getString("image");

                                    Picasso.get().load(obj.getString("image"))
                                            .error(R.drawable.splash)
                                            .into(imgExpert);




                                }








                            }
                            else
                            {
                                Toast.makeText(Astrologerdetails.this, "Server not responding. Try after some time.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Astrologerdetails.this, "Server not responding. Try after some time.", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             //   Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(Astrologerdetails.this, "Server not responding. Try after some time.", Toast.LENGTH_SHORT).show();
                finish();
                Log.d("Dfsdfsdf",error.toString());

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //imageEncoded=getStringImage(bitmap);
                //Log.i("image", encodedImageList.toString());
                Map<String, String> params = new Hashtable<String, String>();
                //params.put("imagefile", encodedImageList.toString());
                //image = getStringImage(bitmap);
                //System.out.print("Check" + image);
                //params.put("mobile", "9799569458");
                //params.put("",json1.toString());
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

                                userbanlance = jsonObject1.getString("wallet");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

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


    private void login_user() {
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

        FirebaseAuth.getInstance().signInWithEmailAndPassword(sharedpreferences.getString("email",""),"12345678").addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    String token = FirebaseInstanceId.getInstance().getToken();
                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Log.d("adjkskjl",userid);
                    // Updating user device token

                    FirebaseDatabase.getInstance().getReference().child("Users").child(userid).child("token").setValue(token).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {


                                // intent.putExtra("users_mobile",phone);
                              /*  if(sharedpreferences.getString("gender","").equals("null") && sharedpreferences.getString("dob","").equals("null") && sharedpreferences.getString("location","").equals("null"))
                                {
                                    Intent intent=new Intent(Login.this,UpdateProfile.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Intent intent=new Intent(Login.this, HomeMainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }*/
                                //  if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified())
                                /*{
                                    // Show animation and start activity

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Your email is not verified, we have sent you a new one.", Toast.LENGTH_LONG).show();
                                    // FirebaseAuth.getInstance().signOut();


                                }*/
                            }
                            else
                            {
                                Log.d(TAG, "uploadToken failed: " + task.getException().getMessage());
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    Log.d(TAG, "signIn failed: " + task.getException().getMessage());


                }
            }
        });
    }






    public void mem(View view) {
        Intent intent=new Intent(Astrologerdetails.this, Supportchat.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.backpress)
        {
            onBackPressed();
        }
    }

    public class TimeAgo2 {

        public String covertTimeToText(String dataDate) {

            String convTime = null;

            String prefix = "";
            String suffix = "";

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date pasTime = dateFormat.parse(dataDate);

                Date nowTime = new Date();

                long dateDiff = nowTime.getTime() - pasTime.getTime();

                long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
                long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
                long hour   = TimeUnit.MILLISECONDS.toHours(dateDiff);
                long day  = TimeUnit.MILLISECONDS.toDays(dateDiff);

                if (second < 60) {
                    convTime = second + " Sec " + suffix;
                } else if (minute < 60) {
                    convTime = minute + " Min "+suffix;
                } else if (hour < 24) {
                    convTime = hour + " Hours "+suffix;
                } else if (day >= 7) {
                    if (day > 360)
                    {
                     convTime = (day / 360) + " Years " + suffix;
                    }
                    else if (day > 30) {
                        convTime = (day / 30) + " Months " + suffix;
                    }
                    else
                     {
                     convTime = (day / 7) + " Week " + suffix;
                     }
                } else if (day < 7) {
                    convTime = day+" Days "+suffix;
                }

            } catch (ParseException e) {
                e.printStackTrace();
                Log.e("ConvTimeE", e.getMessage());
            }

            return convTime;
        }

    }



    private void  alertDialog(String id, int userid, String user_mobile)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(Astrologerdetails.this);
        builder.setTitle("Confirm to call");
        builder.setMessage("Do you want to call Astrologer ??");
        builder.create();
        builder.setPositiveButton("Call Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                callAstrolger(id,userid,user_mobile);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }


    private void callAstrolger(String id, int userid, String user_mobile)
    {
        progres.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, RootURL.callAstrolger, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                progres.setVisibility(View.GONE);
                Log.d("responsis",response);
                try {
                    JSONObject jsonpObject=new JSONObject(response);
                    Toast.makeText(Astrologerdetails.this, ""+jsonpObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(Astrologerdetails.this, "", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progres.setVisibility(View.GONE);
                Log.d("error",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                map.put("user_id", String.valueOf(userid));
                map.put("astrologer_user_id",id);
                map.put("moblie_no",user_mobile);
                Log.d("mapiss",map.toString());
                return map;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy( 200*30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }

}
