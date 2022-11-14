package com.ksbm_astroexpert.ui.privacy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.PrivacyPolicyActivityBinding;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.base.BaseProgressRecyclerListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class PrivacyPolicyActivity extends BaseActivity {

    private PrivacyPolicyActivityBinding binding;
    private BaseProgressRecyclerListAdapter baseRecyclerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.privacy_policy_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (PrivacyPolicyActivityBinding) getBinding();
        init();
        faq();
    }

    private void init() {
        actionBar(binding.actionbar.toolbar , R.drawable.ic_arrow_back, false, true, true,true);
        binding.actionbar.txtTitle.setText(R.string.lbl_privacy_policy);
    }

    private void faq() {
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

        final String URLALL=RootURL.Base_URL+"/Astroksbmadmin/user/pages.php?id=3";

        Log.d("ABCsds",URLALL);
        StringRequest request=new StringRequest(Request.Method.GET,URLALL.replaceAll(" ","%20"),
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        try {



                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                            //    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if (jsonObject.getString("success").equals("200")){

                            /*    comments.setText(jsonObject.getString("comment")+"k mins");
                                reports.setText(jsonObject.getString("report")+" reports");
                                calls.setText(jsonObject.getString("call")+"k mins");*/

                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    binding.title.setText(obj.getString("title"));
                                    String nini = Html.fromHtml(obj.getString("content")).toString();
                                    binding.desc.setText(Html.fromHtml(nini).toString());
                                }


                            }
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
                }) {

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
