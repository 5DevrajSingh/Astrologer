package com.ksbm_astroexpert.notification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Adapter.AstrologerAdapterd;
import com.ksbm_astroexpert.Adapter.NotificationAdapter;
import com.ksbm_astroexpert.Astrologer.Astrologer;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.ModelClass.NotificationModel;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.NotificationActivityBinding;
import com.ksbm_astroexpert.databinding.NotificationItemBinding;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.base.BaseProgressRecyclerListAdapter;
import com.ksbm_astroexpert.ui.home.RemediesResponseModel;
import com.ksbm_astroexpert.ui.utils.UserUtils;
import com.ksbm_astroexpert.view.ImageData;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class NotificationActivity extends BaseActivity {

    private NotificationActivityBinding notificationActivityBinding;
    private BaseProgressRecyclerListAdapter baseRecyclerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.notification_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationActivityBinding = (NotificationActivityBinding) getBinding();
        init();
    }

    private void init() {
        actionBar(notificationActivityBinding.actionbar.toolbar, R.drawable.ic_arrow_back, false, true, true,true);
        notificationActivityBinding.actionbar.txtTitle.setText(R.string.lbl_notification);
        notificationActivityBinding.actionbar.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        initNotificationList();
    }

    private void initNotificationList()
    {
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        final ProgressDialog progress = new ProgressDialog(NotificationActivity.this);
        progress.setMessage("Please Wait..");
        progress.setCancelable(false);
        progress.show();

        progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progress.dismiss();
            }
        });
        StringRequest stringRequest=new StringRequest(Request.Method.POST, RootURL.NotificationList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progress.dismiss();
                Log.i("check", response.toString());

              try{
                  JSONObject jsonObject = new JSONObject(String.valueOf(response));

                  List<NotificationModel> filteredList = new ArrayList<>();
                  if (jsonObject.getString("status").equals("1")) {
                      JSONArray jsonArray = jsonObject.getJSONArray("data");
                      for (int i = 0; i < jsonArray.length(); i++) {
                          JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                          String id = jsonObject1.getString("id");
                          String date = jsonObject1.getString("date");
                          String title = jsonObject1.getString("title");
                          String message = jsonObject1.getString("message");
                          String recipient = jsonObject1.getString("recipient");
                          String customer_name = jsonObject1.getString("customer_name");
                          String role = jsonObject1.getString("role");
                          String type = jsonObject1.getString("type");

                          NotificationModel not = new NotificationModel();
                          not.setId(id);
                          not.setDate(date);
                          not.settitle(title);
                          not.setMessage(message);
                          not.setRecipient(recipient);
                          not.setCustomer_name(customer_name);
                          not.setRole(role);
                          not.settype(type);
                          filteredList.add(not);
                      }

                      NotificationAdapter donationAdapter=new NotificationAdapter(NotificationActivity.this,filteredList);
                      donationAdapter.notifyDataSetChanged();
                      notificationActivityBinding.rvNotiList.setAdapter(donationAdapter);
//                            showEmptyHolder();

                  }
                  else
                  {
                      notificationActivityBinding.txtErrorMessage.setVisibility(View.VISIBLE);
                  }



              }
              catch (JSONException e)
              {

              }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                Log.d("error",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
                map.put("user_id", sharedpreferences.getString("id",""));
                map.put("user_type","1");
                Log.d("check1",map.toString());
                return map;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy( 200*30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }




    private void showEmptyHolder() {
        if (baseRecyclerAdapter.getItemCount() > 0) {
            UserUtils.gone(notificationActivityBinding.txtErrorCaption);
            UserUtils.gone(notificationActivityBinding.txtErrorMessage);
        }else{
            UserUtils.visible(notificationActivityBinding.txtErrorCaption);
            UserUtils.visible(notificationActivityBinding.txtErrorMessage);
        }
    }
}
