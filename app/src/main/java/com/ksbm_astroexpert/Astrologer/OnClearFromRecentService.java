package com.ksbm_astroexpert.Astrologer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Chat.activity.ChatActivity;
import com.ksbm_astroexpert.Constant.LocalDataBase;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.ui.Wallet.RechargeWallet;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class OnClearFromRecentService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ClearFromRecentService", "Service Started");
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ClearFromRecentService", "Service Destroyed");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("ClearFromRecentService", "END");
        //Code here

        if(ChatActivity.ChatStarted.equals("No"))
        {

        }
        else if(ChatActivity.ChatStarted.equals("Yes"))
        {
            Toast.makeText(this, "Chat not ended by you , so we closing chat fromm our end.", Toast.LENGTH_SHORT).show();
            walletdeduct();
        }


        stopSelf();
    }




    private void walletdeduct() {

        LocalDataBase.currentDateTimeL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        int a = Integer.parseInt(LocalDataBase.chat_commissionL);
        int b = Integer.parseInt(LocalDataBase.chat_price_mL);
        int c = a + b;
        // Toast.makeText(this, "rajput", Toast.LENGTH_SHORT).show();
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("user_id", LocalDataBase.UserIdL);
            json1.put("astro_id", LocalDataBase.idL);
            json1.put("amount", c);
            json1.put("commission", LocalDataBase.chat_commissionL);
            json1.put("astro_amount", LocalDataBase.chat_price_mL);
            json1.put("chat_id", LocalDataBase.chatidL);
            json1.put("chat_call", "1");
            json1.put("end_time", LocalDataBase.currentDateTimeL);
            json1.put("transid", LocalDataBase.transidL);
            Log.d("swwdwdwd", json1.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.CHATWALLETDEDUCT;

        //Toast.makeText(this, url+" $$$$ " + json1.toString(), Toast.LENGTH_LONG).show();
        Log.i("gdsdasdasdfgvgbnn", url + json1.toString());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            LocalDataBase.UserIdL="";
                            LocalDataBase.chat_commissionL="";
                            LocalDataBase.chat_price_mL="";
                            LocalDataBase.idL="";
                            LocalDataBase.userbanlanceL="";
                            LocalDataBase.chatidL="";
                            LocalDataBase.currentDateTimeL="";
                            LocalDataBase.transidL="";

                            Log.d("swwdwdwd12213", response.toString());

                            //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                            Log.d("ghsdsdaserererdasdsdag", response.toString());
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            if (jsonObject.getString("status").equals("1")) {

                                String Wallet = jsonObject.getString("updated_wallet").toString();
                                Wallet=Wallet.substring(0, Wallet.length()-3);
                                int av = Integer.parseInt(Wallet);
                                Log.d("checkupdatewalet", String.valueOf(av));
                                if (av <= 0) {
                                    Toast.makeText(OnClearFromRecentService.this, "You have to recharge your wallet", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(OnClearFromRecentService.this, "Your AstroExperts Wallet Balance left : " + jsonObject.getString("updated_wallet"), Toast.LENGTH_SHORT).show();

                                }
                            } else {

                                Toast.makeText(OnClearFromRecentService.this, "Your Have not Balance Now: " + jsonObject.getString("updated_wallet"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                Log.i("Dfsdfsdf", error.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //imageEncoded=getStringImage(bitmap);
                //Log.i("image", encodedImageList.toString());
                Map<String, String> params = new Hashtable<String, String>();
//                params.put("imagefile", encodedImageList.toString());
//                 image = getStringImage(bitmap);
//                  System.out.print("Check" + image);
//                params.put("mobile", "9799569458");
//                 params.put("",json1.toString());
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);

    }






}