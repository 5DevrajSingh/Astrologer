package com.ksbm_astroexpert.Astrologer;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Chat.RegisterUser;
import com.ksbm_astroexpert.Chat.RegisterUser1;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.RegisterUserBinding;
import com.ksbm_astroexpert.ui.home.HomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TimeZone;

public class ProfileUpdate  extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    RegisterUserBinding binding;
    private RequestQueue requestQueue;
    String gender="Male";


    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.register_user);
        requestQueue= Volley.newRequestQueue(this);
        binding.mobile.setText(getIntent().getStringExtra("mobile"));
        binding.register.setOnClickListener(this);
        binding.register.setText("Update");
        binding.skip.setOnClickListener(this);
        binding.skip.setVisibility(View.GONE);
        binding.textsign.setText("Update Profile");
        binding.textsign.setTextSize(18);
        binding.textsign1.setVisibility(View.GONE);
        binding.dob.setOnClickListener(this);
        binding.timeofbith.setOnClickListener(this);
        binding.radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i==R.id.male)
                {
                    gender="Male";
                }
                else if (i==R.id.female)
                {
                    gender="Female";
                }
            }
        });


        fetchdata();



    }


    private void getUploadDetails()
    {
        final ProgressDialog progress = new ProgressDialog(ProfileUpdate.this);
        progress.setMessage("Uploading,please wait...");
        progress.setCancelable(false);
        progress.show();
        String url= RootURL.udate_profile_fields;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progress.dismiss();
                Log.d("dsdwdwdwdw",response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getString("status").equals("1"))
                    {
                        sendatatpserver();
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
                map.put("mobile",binding.mobile.getText().toString());
                map.put("date_of_birth",binding.dob.getText().toString());
                map.put("time_of_birth",binding.timeofbith.getText().toString());
                map.put("place_of_birth",binding.placeofbirth.getText().toString());
                map.put("first_name",binding.name.getText().toString());
                map.put("last_name",binding.lastname.getText().toString());
                map.put("email",binding.email.getText().toString());
                map.put("gender",gender);
                return map;
            }


        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

    }





    private void sendatatpserver() {
        final String[] status = {""};


        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        final ProgressDialog progress = new ProgressDialog(ProfileUpdate.this);
        progress.setMessage("Please Wait..");
        progress.setCancelable(false);
        progress.show();
        progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progress.dismiss();
            }
        });

        final JSONObject json1 = new JSONObject();
        try {
            json1.put("user_id", sharedpreferences.getString("id", ""));
            json1.put("firstname", binding.name.getText().toString());
            json1.put("lastname", binding.lastname.getText().toString());
            json1.put("countrycode", "");
            json1.put("phone", binding.mobile.getText().toString());
            json1.put("email", binding.email.getText().toString());
            json1.put("gender", gender);
            json1.put("astro_id", "");
            json1.put("chat_call", "");
            json1.put("dob", binding.dob.getText().toString());
            json1.put("tob", binding.timeofbith.getText().toString());
            json1.put("city", "");
            json1.put("place_city", binding.placeofbirth.getText().toString());
            json1.put("state", "");
            json1.put("country", "");
            json1.put("country_code", "");
            json1.put("marital", "");
            json1.put("occupation", "");
            json1.put("topic", "");
            json1.put("partner_name", "");
            json1.put("partner_dob", "");
            json1.put("partner_tob", "");
            json1.put("partner_city", "");
            json1.put("partner_state", "");
            json1.put("partner_country", "");
            json1.put("profile","1");
            Log.d("JSONObjectdddfcedfcd", json1 + "");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String URL_PRODUCTS = RootURL.CALLINTAKESUBMIT;
        Log.i("Dfsdfsdfsdf", URL_PRODUCTS + json1);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, URL_PRODUCTS, json1,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            progress.dismiss();
                            status[0] =jsonObject.getString("status");
                            if (jsonObject.getString("status").equals("1")) {
                                Toast.makeText(ProfileUpdate.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                                fetchdata1();
                            }
                            else if (jsonObject.getString("status").equals("0"))
                            {
//                                Toast.makeText(RegisterUser1.this, jsonObject.getString("message").toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(ProfileUpdate.this, "Profile not update due to server issue.", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            progress.dismiss();
                            if(status[0].equals("1"))
                            {
                                Toast.makeText(ProfileUpdate.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                                fetchdata1();
                            }
                            else
                            {
                                Toast.makeText(ProfileUpdate.this, "Profile not update due to server issue.", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                progress.dismiss();
                if(status[0].equals("1"))
                {
                    Toast.makeText(ProfileUpdate.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                    fetchdata1();
                }
                else
                {
                    Toast.makeText(ProfileUpdate.this, "Profile not update due to server issue.", Toast.LENGTH_SHORT).show();
                }            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                //params.put("imagefile", encodedImageList.toString());
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.register)
        {
            getUploadDetails();
        }
        else if (view.getId()==R.id.skip)
        {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }
        else if (view.getId()==R.id.dob)
        {
            alertDate();
        }
        else if (view.getId()==R.id.timeofbith)
        {
            alertTime();
        }
    }



    private void fetchdata() {

        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        String url= RootURL.FETCHCALLINTAKEDORM+"?user_id="+sharedpreferences.getString("id", "");

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    Log.d("ghsdsdasdasdsdag", response.toString());

                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    JSONArray jsonArray = jsonObject.getJSONArray("records");

                    if(!String.valueOf(jsonArray.length()).equals("0"))
                    {

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            binding.name.setText(obj.getString("firstname"));
                            binding.lastname.setText(obj.getString("lastname"));
                            binding.mobile.setText(obj.getString("phone"));
                            binding.dob.setText(obj.getString("dob"));
                            binding.timeofbith.setText(obj.getString("tob"));
                            binding.placeofbirth.setText(obj.getString("city"));
                            binding.email.setText(obj.getString("email"));
                            String Gender = obj.getString("gender");
                            if(Gender.equals("Male"))
                            {
                                binding.male.setChecked(true);
                                binding.male.setSelected(true);
                                binding.female.setChecked(false);
                                binding.female.setSelected(false);
                            }
                            else if(Gender.equals("Female"))
                            {
                                binding.male.setChecked(false);
                                binding.male.setSelected(false);
                                binding.female.setChecked(true);
                                binding.female.setSelected(true);
                            }


                        }

                    }
                    else {

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileUpdate.this, error.toString(), Toast.LENGTH_LONG).show();
//                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(mStringRequest);
        mRequestQueue.add(mStringRequest);
    }

    private void fetchdata1() {

        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        String url= RootURL.FETCHCALLINTAKEDORM+"?user_id="+sharedpreferences.getString("id", "");

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    Log.d("ghsdsdasdasdsdag", response.toString());

                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    JSONArray jsonArray = jsonObject.getJSONArray("records");

                    if(!String.valueOf(jsonArray.length()).equals("0"))
                    {

                        Toast.makeText(ProfileUpdate.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();


                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            binding.name.setText(obj.getString("firstname"));
                            binding.lastname.setText(obj.getString("lastname"));
                            binding.mobile.setText(obj.getString("phone"));
                            binding.dob.setText(obj.getString("dob"));
                            binding.timeofbith.setText(obj.getString("tob"));
                            binding.placeofbirth.setText(obj.getString("city"));
                            binding.email.setText(obj.getString("email"));
                            String Gender = obj.getString("gender");
                            if(Gender.equals("Male"))
                            {
                                binding.male.setChecked(true);
                                binding.male.setSelected(true);
                                binding.female.setChecked(false);
                                binding.female.setSelected(false);
                            }
                            else if(Gender.equals("Female"))
                            {
                                binding.male.setChecked(false);
                                binding.male.setSelected(false);
                                binding.female.setChecked(true);
                                binding.female.setSelected(true);
                            }


                        }

                    }
                    else {

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileUpdate.this, error.toString(), Toast.LENGTH_LONG).show();
//                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(mStringRequest);
        mRequestQueue.add(mStringRequest);
    }


    private void alertDate()
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(ProfileUpdate.this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        String date= String.valueOf(new StringBuilder().append(year).append("-").append(monthOfYear + 1).append("-").append(dayOfMonth).append(" "));
        binding.dob.setText(date);
        Log.d("date9ss",date);
    }

    private void alertTime()
    {
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        TimePickerDialog picker = new TimePickerDialog(ProfileUpdate.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        binding.timeofbith.setText(sHour + ":" +sMinute+":"+"00");
                    }
                }, hour, minutes, true);
        picker.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
       finish();
    }
}