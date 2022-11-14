package com.ksbm_astroexpert.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TimePicker;
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
import com.ksbm_astroexpert.databinding.RegisterUserBinding;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.ksbm_astroexpert.ui.otpverification.OTPVerificationActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    RegisterUserBinding binding;
    private RequestQueue requestQueue;
    String gender="Male";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.register_user);
        requestQueue=Volley.newRequestQueue(this);
        binding.mobile.setText(getIntent().getStringExtra("mobile"));
        binding.register.setOnClickListener(this);
        binding.skip.setOnClickListener(this);
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
    }


    private void getUploadDetails()
    {
        final ProgressDialog progress = new ProgressDialog(RegisterUser.this);
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
                        Toast.makeText(RegisterUser.this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
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

    private void alertDate()
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(RegisterUser.this, this,
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
        TimePickerDialog picker = new TimePickerDialog(RegisterUser.this,
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
        Intent intent = new Intent(RegisterUser.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}