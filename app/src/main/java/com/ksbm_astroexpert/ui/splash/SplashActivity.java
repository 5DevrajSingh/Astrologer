package com.ksbm_astroexpert.ui.splash;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.ksbm_astroexpert.Astrologer.OnClearFromRecentService;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.FCM.SharedHelper;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.SplashActivityBinding;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.ksbm_astroexpert.ui.otpverification.SmsHashCodeHelper;
import com.ksbm_astroexpert.ui.signin.SignInActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class SplashActivity extends BaseActivity {
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int EXTERNAL = 786;
    private SplashActivityBinding splashActivityBinding;
    private Context mContext=SplashActivity.this;
    String device_token, device_UDID;
    public Context context = SplashActivity.this;
    @Override
    protected int getLayout() {
       return R.layout.splash_activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashActivityBinding = (SplashActivityBinding) getBinding();
        GetToken();
        SmsHashCodeHelper smsHashCodeHelper = new SmsHashCodeHelper(this);
        smsHashCodeHelper.getAppHashCode();

//        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
//        }
//
//        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL);
//        }

        startService(new Intent(getBaseContext(), OnClearFromRecentService.class));


        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_CAMERA_REQUEST_CODE);
            Toast.makeText(context, "Please Allow Permissions for better performance.", Toast.LENGTH_SHORT).show();
        } else
        {
            new Handler().postDelayed(() -> {
                // This method will be executed once the timer is over
                // Start your app main activity
                SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, MODE_PRIVATE);

                if(!sharedpreferences.getString("id","").equals("")) {
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(i);
                }
                finish();
            }, 2000);
        }


//        splashActivityBinding.imgBrandLogo.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                throw new RuntimeException("Test Crash"); // Force a crash
//            }
//        });
        /*
         * Showing splash screen with a timer. This will be useful when you
         * want to show case your app logo / company
         */

    }
    public void GetToken() {
        try {
            if (!SharedHelper.getKey(context, "device_token").equals("") &&
                    SharedHelper.getKey(context, "device_token") != null) {
                device_token = SharedHelper.getKey(context, "device_token");
                // Log.i(TAG, "GCM Registration Token: " + device_token);
            } else {
                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(SplashActivity.this, new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        String newToken = instanceIdResult.getToken();
                        Log.e("newToken", newToken);
                        SharedHelper.putKey(getApplicationContext(), "device_token", "" + newToken);
                        device_token = newToken;

                    }
                });
            }
        } catch (Exception e) {
            device_token = "COULD NOT GET FCM TOKEN";
            // Log.d(TAG, "Failed to complete token refresh", e);
        }


        try {
            device_UDID = Settings.Secure.getString(getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            // Log.i(TAG, "Device UDID:" + device_UDID);
        } catch (Exception e) {
            device_UDID = "COULD NOT GET UDID";
            e.printStackTrace();
            //  Log.d(TAG, "Failed to complete device UDID");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

            SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, MODE_PRIVATE);

            if(!sharedpreferences.getString("id","").equals("")) {
                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);
            }else{
                Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                startActivity(i);
            }
            finish();

        }else if (requestCode == EXTERNAL){
            if (grantResults.length>0)
            {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
                }

                SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, MODE_PRIVATE);

                if(!sharedpreferences.getString("id","").equals("")) {
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(i);
                }
                finish();

            }

            else
            {

            }

        }
    }

}
