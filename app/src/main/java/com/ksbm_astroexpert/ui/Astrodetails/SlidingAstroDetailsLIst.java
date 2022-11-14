package com.ksbm_astroexpert.ui.Astrodetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import android.view.View;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ksbm_astroexpert.Astrologer.Astrologer;
import com.ksbm_astroexpert.Constant.Constant;
import com.ksbm_astroexpert.Constant.LocalDataBase;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.ActivitySlidingAstroDetailsLIstBinding;
import com.ksbm_astroexpert.network.APIClient;
import com.ksbm_astroexpert.network.APIInterface;
import com.ksbm_astroexpert.ui.Wallet.RechargeWallet;
import com.ksbm_astroexpert.ui.utils.UserConstants;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.ksbm_astroexpert.Constant.Constant.astroModelList;

public class SlidingAstroDetailsLIst extends AppCompatActivity implements ItemListener, View.OnClickListener {

    int pos = 0;
    List<ModelAstrologerList.Record> modelList;
    private LinearLayoutManager layoutManager;
    private ActivitySlidingAstroDetailsLIstBinding binding;
    int currentPage = -1;
    private String calloffline;
    private String chatoffline = "", call_commission = "", chat_commission = "", user_mobile = "", image = "", astroname,
            chatid = "", chat_price_m = "", call_price_m = "", userbanlance = "", astroid = "", id = "", astro_token = "",
            astrolang, astrolong;

    boolean ishit=false;

    private CompositeDisposable compositeDisposable;


    String WhatsAppNo= "+919991900369";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sliding_astro_details_l_ist);
        pos = getIntent().getIntExtra("pos", 0);
        Log.d("checkposintin", String.valueOf(pos));
        astroid = getIntent().getStringExtra("astroid");

        modelList = Constant.astroModelList;
        binding.callbtn.setOnClickListener(this);
        binding.chatbtn.setOnClickListener(this);
        binding.backBtn.setOnClickListener(this);

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setHasFixedSize(false);

        layoutManager.scrollToPositionWithOffset(pos, 0);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerview);
        binding.recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //here we find the current item number
                //  final int scrollOffset = recyclerView.computeVerticalScrollOffset();
                final int scrollOffset = recyclerView.computeHorizontalScrollOffset();
                final int height = recyclerView.getWidth();
                int page_no = scrollOffset / height;
                Log.d("page_no", String.valueOf(page_no));
                if (page_no != currentPage) {
                    currentPage = page_no;
                    setCurrentDetails(currentPage);
                }
                Log.d("possos", String.valueOf(currentPage));
            }
        });

        AstroDetails_Adapter relatedProductAdapter = new AstroDetails_Adapter(getApplicationContext(), modelList, this);
        binding.recyclerview.setAdapter(relatedProductAdapter);

        walletbalnce();


        binding.imgSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geotowhapp();
            }
        });



        binding.llwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(Astrologer.this, "You have to Recharge your account", Toast.LENGTH_SHORT).show();
                Intent chatIntent = new Intent(SlidingAstroDetailsLIst.this, RechargeWallet.class);
                startActivity(chatIntent);
            }
        });



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 2 second
                        refresh();
                    }
                }, 2000);
            }
        });

    }

    public void refresh()
    {

        if(!ishit)
        {
            getAstrologerList();
        }
        else
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 2 second
                            refresh();
                        }
                    }, 2000);
                }
            });
        }

    }


    private void getAstrologerList() {

        ishit=true;
        //Log.d("sqqss", "doLogin: "+"login dara start here");
        compositeDisposable = new CompositeDisposable();
        APIInterface apiInterface = getApiInterface();
        Single<ModelAstrologerList> signInResponseModelObservable = apiInterface.astrodetails_list();
        signInResponseModelObservable.subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Object o) {

                        ishit=false;
                        ModelAstrologerList signInResponseModel = (ModelAstrologerList) o;
                        astroModelList=signInResponseModel.getRecords();
                        modelList=astroModelList;

//                        AstroDetails_Adapter relatedProductAdapter = new AstroDetails_Adapter(getApplicationContext(), modelList, SlidingAstroDetailsLIst.this);
//                        binding.recyclerview.setAdapter(relatedProductAdapter);
//                        relatedProductAdapter.notifyDataSetChanged();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Do something after 4 second
                                        refresh();
                                    }
                                }, 4000);
                            }
                        });

                    }

                    @Override
                    public void onError(Throwable e) {
                        ishit=false;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Do something after 2 second
                                        refresh();
                                    }
                                }, 4000);
                            }
                        });
                    }
                });

    }

    public APIInterface getApiInterface() {
        return APIClient.getClient(UserConstants.BASE_URL2).create(APIInterface.class);
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

    public void setCurrentDetails(final int currentPage) {

        final ModelAstrologerList.Record model = modelList.get(currentPage);
        //=================call======================
        if (model.getCurrentStatusCall().equals("Offline")) {
            Log.d("checkonline", "dfskljkf");
            calloffline = "1";
            //binding.callmed.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            binding.callbtn.setBackgroundResource(R.color.gray_btn_bg_color);
            binding.callme.setTextSize(14);
            //binding.permin.setTextColor(Color.parseColor("#ee534f"));
            binding.callprice.setTextSize(12);
            //binding.callme.setTextColor(Color.parseColor("#ee534f"));
            //binding.callprice.setTextColor(Color.parseColor("#ee534f"));
            binding.callme.setText("Offline");
            if (model.getWaitTimeCall() == null) {

            } else {
                String time = model.getWaitTimeCall() + "";
                TimeAgo2 timeAgo2 = new TimeAgo2();
                String MyFinalValue = timeAgo2.covertTimeToText(time);
                //binding.callme.setText("Call Wait Time " + MyFinalValue);

            }

        } else if(model.getCurrentStatusCall().equals("Busy"))
        {
            Log.d("checkonline", "dfskljkf");
            calloffline = "2";
            //binding.callmed.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            binding.callbtn.setBackgroundResource(R.color.colorAccent);
            binding.callme.setTextSize(14);

            //binding.permin.setTextColor(Color.parseColor("#ee534f"));
            binding.callprice.setTextSize(12);
            //binding.callme.setTextColor(Color.parseColor("#ee534f"));
            //binding.callprice.setTextColor(Color.parseColor("#ee534f"));
            binding.callme.setText("Busy");

            if (model.getWaitTimeCall() == null) {

            } else {
                String time = model.getWaitTimeCall() + "";
                TimeAgo2 timeAgo2 = new TimeAgo2();
                String MyFinalValue = timeAgo2.covertTimeToText(time);
                //binding.callme.setText("Call Wait Time " + MyFinalValue);
            }
        }
        else  if(model.getCurrentStatusCall().equals("Online"))
        {
            calloffline = "";
            binding.callbtn.setBackgroundResource(R.color.colorAccent);
            binding.callme.setTextSize(14);
            //binding.permin.setTextColor(Color.parseColor("#ee534f"));
            binding.callprice.setTextSize(12);
            //binding.callme.setTextColor(Color.parseColor("#ee534f"));
            //binding.callprice.setTextColor(Color.parseColor("#ee534f"));
            binding.callme.setText("Call");

        }//===============End call==========================


//===============Chat Start==========================
        if (model.getCurrentStatus().equals("Offline")) {
            Log.d("checkonline", "ccccccc");
            chatoffline = "1";
            //binding.chatmed.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            binding.chatbtn.setBackgroundResource(R.color.gray_btn_bg_color);
            binding.chatme.setTextSize(14);
            //binding.perminchat.setTextColor(Color.parseColor("#ee534f"));
            binding.chatprice.setTextSize(12);
            //binding.chatme.setTextColor(Color.parseColor("#ee534f"));
            //binding.chatprice.setTextColor(Color.parseColor("#ee534f"));
            binding.chatme.setText("Offline");
            if (model.getWaitTime() == null) {

                Log.d("dfsjj", "dddddd");
            } else {
                                            /*String time = obj.getString("wait_time_call");
                                            TimeAgo2 timeAgo2 = new TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);*/
                Log.d("dfsjj", "ggggggg");
                String time = model.getWaitTime() + "";
                TimeAgo2 timeAgo2 = new TimeAgo2();
                String MyFinalValue = timeAgo2.covertTimeToText(time);
                //binding.chatme.setText("Chat Wait Time " + MyFinalValue);
            }
        } else if (model.getCurrentStatus().equals("Busy")) {
            Log.d("checkonline", "ccccccc");
            chatoffline = "2";
            //binding.chatmed.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            binding.chatbtn.setBackgroundResource(R.color.colorAccent);
            binding.chatme.setTextSize(14);
            //binding.perminchat.setTextColor(Color.parseColor("#ee534f"));
            binding.chatprice.setTextSize(12);
            //binding.chatme.setTextColor(Color.parseColor("#ee534f"));
            //binding.chatprice.setTextColor(Color.parseColor("#ee534f"));
            binding.chatme.setText("Busy");



            if (model.getWaitTime() == null) {

                Log.d("dfsjj", "dddddd");
            } else {
                                            /*String time = obj.getString("wait_time_call");
                                            TimeAgo2 timeAgo2 = new TimeAgo2();
                                            String MyFinalValue = timeAgo2.covertTimeToText(time);*/
                Log.d("dfsjj", "ggggggg");
                String time = model.getWaitTime() + "";
                TimeAgo2 timeAgo2 = new TimeAgo2();
                String MyFinalValue = timeAgo2.covertTimeToText(time);
                //binding.chatme.setText("Chat Wait Time " + MyFinalValue);
            }
        }
        else if (model.getCurrentStatus().equals("Online"))
        {

            chatoffline = "";
            binding.chatbtn.setBackgroundResource(R.color.colorAccent);
            binding.chatme.setTextSize(14);
            //binding.perminchat.setTextColor(Color.parseColor("#ee534f"));
            binding.chatprice.setTextSize(12);
            //binding.chatme.setTextColor(Color.parseColor("#ee534f"));
            //binding.chatprice.setTextColor(Color.parseColor("#ee534f"));
            binding.chatme.setText("Chat");

        }

        //===============End Chat=========================

        chatid = String.valueOf(model.getChatToken());
        chat_price_m = model.getChatPriceM();
        call_price_m = model.getCallPriceM();
        id = model.getId();
        astro_token = model.getChatToken() + "";
        chat_commission = model.getChatCommission();
        call_commission = model.getCallCommission();
        astroname = model.getOwnerName();
        astrolang = model.getLanguage();
        astrolong = model.getLon();


        if (model.getChatCommission()==null) {
            int a = 0;
            int b = Integer.parseInt(chat_commission);
            int c = a + b;
            binding.chatprice.setText("₹" + c+"/min");
        } else {
            int a = Integer.parseInt(model.getChatPriceM());
            int b = Integer.parseInt(model.getChatCommission());
            int c = a + b;
            binding.chatprice.setText("₹" + c+"/min");
        }



        if (model.getCallCommission()==null) {
            int a = 0;
            int b = Integer.parseInt(call_commission);
            int c = a + b;
            binding.callprice.setText("₹" + c+"/min");
        } else {
            int a = Integer.parseInt(model.getCallPriceM());
            int b = Integer.parseInt(model.getCallCommission());
            int c = a + b;
            binding.callprice.setText("₹" + c+"/min");
        }




        image = model.getImage();


    }


    @Override
    public void onItemClickListener(Object model, String s) {

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
                long hour = TimeUnit.MILLISECONDS.toHours(dateDiff);
                long day = TimeUnit.MILLISECONDS.toDays(dateDiff);

                if (second < 60) {
                    convTime = second + " Sec " + suffix;
                } else if (minute < 60) {
                    convTime = minute + " Min " + suffix;
                } else if (hour < 24) {
                    convTime = hour + " Hours " + suffix;
                } else if (day >= 7) {
                    if (day > 360) {
                        convTime = (day / 360) + " Years " + suffix;
                    } else if (day > 30) {
                        convTime = (day / 30) + " Months " + suffix;
                    } else {
                        convTime = (day / 7) + " Week " + suffix;
                    }
                } else if (day < 7) {
                    convTime = day + " Days " + suffix;
                }

            } catch (ParseException e) {
                e.printStackTrace();
                Log.e("ConvTimeE", e.getMessage());
            }
            return convTime;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.callbtn) {
            callAstrologer();
        } else if (view.getId() == R.id.chatbtn) {
            chatAstrologer();
        } else if (view.getId() == R.id.back_btn) {
            onBackPressed();
        }
    }




    private void callAstrologer() {
        if (calloffline.equals("1")) {
            Toast.makeText(getApplicationContext(), "Please wait for astrologer to come online", Toast.LENGTH_SHORT).show();
        } else {
            //==============commented  by abhinav shukla date is 27_08_20  ==========


            if (calloffline.equals("2"))
            {
                Toast.makeText(getApplicationContext(), "Astrologer seems busy, Please try after some time.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                LocalDataBase.chatid1=chatid;
                LocalDataBase.chat_price_m1=chat_price_m;
                LocalDataBase.call_price_m1=call_price_m;
                LocalDataBase.astroid1=astroid;
                LocalDataBase.id1=id;
                LocalDataBase.image1=image;
                LocalDataBase.lang1=astrolang;
                LocalDataBase.userbanlance1=userbanlance;
                LocalDataBase.astroname1=astroname;
                LocalDataBase.chat_commission1=chat_commission;
                LocalDataBase.call_commission1=call_commission;
                LocalDataBase.astro_token1=astro_token;




                Intent chatIntent = new Intent(getApplicationContext(), Astrologer.class);
                chatIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                chatIntent.putExtra("userid", chatid);
                chatIntent.putExtra("chatprice", chat_price_m);
                chatIntent.putExtra("astroid", astroid);
                chatIntent.putExtra("callprice", call_price_m);
                chatIntent.putExtra("image", image);
                chatIntent.putExtra("userbalance", userbanlance);
                chatIntent.putExtra("astroname", astroname);
                chatIntent.putExtra("id", id);
                chatIntent.putExtra("lang", astrolang);
                chatIntent.putExtra("type", "call");
                chatIntent.putExtra("chat_commission", chat_commission);
                chatIntent.putExtra("call_commission", call_commission);
                chatIntent.putExtra("userbalance", userbanlance);
                chatIntent.putExtra("type", "call");
                chatIntent.putExtra("position",pos);
                startActivity(chatIntent);
            }




        }
    }


    private void chatAstrologer() {
        if (chatoffline.equals("1")) {
            Toast.makeText(getApplicationContext(), "Please wait for astrologer to come online", Toast.LENGTH_SHORT).show();
        } else {

            if (chatoffline.equals("2"))
            {
                Toast.makeText(getApplicationContext(), "Astrologer seems busy, Please try after some time.", Toast.LENGTH_SHORT).show();
            }
            else {

                LocalDataBase.chatid1=chatid;
                LocalDataBase.chat_price_m1=chat_price_m;
                LocalDataBase.call_price_m1=call_price_m;
                LocalDataBase.astroid1=astroid;
                LocalDataBase.id1=id;
                LocalDataBase.image1=image;
                LocalDataBase.lang1=astrolang;
                LocalDataBase.userbanlance1=userbanlance;
                LocalDataBase.astroname1=astroname;
                LocalDataBase.chat_commission1=chat_commission;
                LocalDataBase.call_commission1=call_commission;
                LocalDataBase.astro_token1=astro_token;

                SharedPreferences sharedPreferences = getSharedPreferences("RequestDetail", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("IsAdded",true);
                editor.putString("chatid",chatid);
                editor.putString("chat_price_m",chat_price_m);
                editor.putString("call_price_m",call_price_m);
                editor.putString("astroid",astroid);
                editor.putString("id1",id);
                editor.putString("image",image);
                editor.putString("lang",astrolang);
                editor.putString("userbanlance",userbanlance);
                editor.putString("astroname",astroname);
                editor.putString("chat_commission",chat_commission);
                editor.putString("call_commission",call_commission);
                editor.putString("astro_token",astro_token);
                editor.commit();
                editor.apply();

                Intent chatIntent = new Intent(getApplicationContext(), Astrologer.class);
                chatIntent.putExtra("userid", chatid);
                chatIntent.putExtra("chatprice", chat_price_m);
                chatIntent.putExtra("callprice", call_price_m);
                chatIntent.putExtra("astroid", id);
                chatIntent.putExtra("id", id);
                chatIntent.putExtra("image", image);
                chatIntent.putExtra("lang", astrolang);
                chatIntent.putExtra("userbalance", userbanlance);
                chatIntent.putExtra("astroname", astroname);
                chatIntent.putExtra("type", "chat");
                chatIntent.putExtra("chat_commission", chat_commission);
                chatIntent.putExtra("call_commission", call_commission);
                chatIntent.putExtra("astro_token", astro_token);
                chatIntent.putExtra("position",pos);
                startActivity(chatIntent);
            }

        }
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
                                binding.txtBalance.setText(jsonObject1.getString("wallet"));
                                Log.i("dfsdsd", jsonObject1.getString("wallet"));
                                userbanlance = jsonObject1.getString("wallet");

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


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onPause() {
        super.onPause();
        if(!ishit)
        {
            getAstrologerList();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(!ishit)
        {
            getAstrologerList();
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(SlidingAstroDetailsLIst.this, SlidingAstroDetailsLIst.class);
        intent.putExtra("pos",pos);
        intent.putExtra("astroid",astroModelList.get(pos).getId());
        intent.putExtra("astro_token",astroModelList.get(pos).getDeviceToken());
        startActivity(intent);
        finish();
    }
}