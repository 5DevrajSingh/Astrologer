package com.ksbm_astroexpert.ui.otpverification;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.InstanceIdResult;
import com.ksbm_astroexpert.Chat.RegisterUser;
import com.ksbm_astroexpert.Chat.RegisterUser1;
import com.ksbm_astroexpert.Chat.utils.SharedPreference;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.OtpVerificationActivityBinding;
import com.ksbm_astroexpert.network.APIInterface;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.ksbm_astroexpert.ui.signin.SignInResponseModel;
import com.google.android.gms.auth.api.Auth;
import com.ksbm_astroexpert.ui.utils.KeyboardUtils;
import com.ksbm_astroexpert.ui.utils.UserUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.aabhasjindal.otptextview.OTPListener;
import retrofit2.Call;

public class OTPVerificationActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks,
        GetOtpInterface, GoogleApiClient.OnConnectionFailedListener {

    private OtpVerificationActivityBinding binding;
    private SignInResponseModel.User user;
    private int otp;
    private String phoneNumber;
    private String status;
    private int counter = 30;
    private CountDownTimer countDownTimer;
    private Call<SignInResponseModel> resendCall;
    String getotp, email, phone, name;
    private final String TAG = "CA/WelcomeActivity";
    SharedPreference sharedPreference;
    GoogleApiClient mGoogleApiClient;

    MySMSBroadCastReceiver mySMSBroadCastReceiver;
    private int RESOLVE_HINT = 2;
    TextView inputOtp;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;



    @Override
    protected int getLayout() {
        return R.layout.otp_verification_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (OtpVerificationActivityBinding) getBinding();
        final Intent i = getIntent();
        phone = i.getStringExtra("phone");
        otp = Integer.parseInt(i.getStringExtra("otp"));
        sharedPreference = SharedPreference.getInstance(this);
        Log.d("checkopty", String.valueOf(otp));


        // Toast.makeText(this, otp, Toast.LENGTH_SHORT).show();

        binding.txtPhoneNo.setText("Please enter OTP sent to " + phone);

        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

       /* try {
            if (getIntent() != null) {
                Bundle bundle = getIntent().getBundleExtra("data");
                if (bundle != null) {
                    if (bundle.containsKey("user")) {
                        user = bundle.getParcelable("user");
                    }
                    if (bundle.containsKey("otp")) {
                        otp = bundle.getInt("otp");


                    }
                    if (bundle.containsKey("status")) {
                        status = bundle.getString("status");
                    }
                    if (bundle.containsKey("phone")) {
                        phoneNumber = bundle.getString("phone");
                        binding.txtPhoneNo.setText(String.format(getString(R.string.please_type_the_verification_code_sent_to), UserUtils.getMaskPhoneNo(phoneNumber)));
                    } else {
                        UserUtils.gone(binding.txtPhoneNo);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        // Toast.makeText(this, otp, Toast.LENGTH_SHORT).show();


        binding.txtResend.setOnClickListener(v -> {

            UserUtils.gone(binding.txtTimer);
            resendCode();

        });
        UserUtils.disable(binding.txtResend);
        startTimer();

        inputOtp = findViewById(R.id.textView);
        // init broadcast receiver
        mySMSBroadCastReceiver = new MySMSBroadCastReceiver();

        //set google api client for hint request
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();
        mySMSBroadCastReceiver.setOnOtpListeners(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
        getApplicationContext().registerReceiver(mySMSBroadCastReceiver, intentFilter);
        // get mobile number from phone
        //  getHintPhoneNumber();
        //start SMS listner

/*
        binding.otpView.setOtpCompletionListener(otp1 -> {
            // do Stuff
            if (otp1.equals(String.valueOf(otp))) {

                binding.otpView.setLineColor(ContextCompat.getColor(OTPVerificationActivity.this, R.color.green));
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    getSessionManger().setUser(user);
                    Intent intent = new Intent(OTPVerificationActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }, 500);


                verfiyotp(otp1);

                Log.i("fdsfsdfdsf",otp1);

            } else {
                binding.otpView.setLineColor(ContextCompat.getColor(OTPVerificationActivity.this, R.color.errColor));
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    binding.otpView.setLineColor(ContextCompat.getColor(OTPVerificationActivity.this, R.color.Black));
                }, 500);
            }
        });
*/


        binding.otpView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {
                // fired when user types something in the Otpbox
            }

            @Override
            public void onOTPComplete(String otp1) {
                Log.d("fdsfsdfdsf", otp1 + "-" + otp);
                if (otp == Integer.parseInt(otp1)) {
                    verfiyotp(otp1);
                } else {
                    Toast.makeText(OTPVerificationActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
        smsListener();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onOtpReceived(String otp) {
        Toast.makeText(this, "Otp Received " + otp, Toast.LENGTH_LONG).show();
        inputOtp.setText(otp);
        // binding.otpView.setOTP(otp);
        // binding.otpView.setText(otp);


    }

    @Override
    public void onOtpTimeout() {
        Toast.makeText(this, "Time out, please resend", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    public void smsListener() {
        SmsRetrieverClient mClient = SmsRetriever.getClient(this);
        Task<Void> mTask = mClient.startSmsRetriever();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                //   Toast.makeText(OTPVerificationActivity.this, "SMS Retriever Started", Toast.LENGTH_LONG).show();
            }
        });
        mTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(OTPVerificationActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * @desc This function is using hint picker to show user's phone number
     */
    public void getHintPhoneNumber() {
        HintRequest hintRequest =
                new HintRequest.Builder()
                        .setPhoneNumberIdentifierSupported(true)
                        .build();
        PendingIntent mIntent = Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, hintRequest);
        try {
            startIntentSenderForResult(mIntent.getIntentSender(), RESOLVE_HINT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    private void startTimer() {
        UserUtils.gone(binding.loaderResend);
        UserUtils.visible(binding.txtTimer);
        counter = 60;
        binding.txtTimer.setText("00:" + counter);
        countDownTimer = new CountDownTimer(counter * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counter--;
                binding.txtTimer.setText("00:" + counter);
            }

            @Override
            public void onFinish() {
                binding.txtTimer.setText("00:00");
                binding.txtResend.setVisibility(View.VISIBLE);
                UserUtils.enable(binding.txtResend);
            }
        };
        countDownTimer.start();
    }

    private void resendCode() {
        KeyboardUtils.hideSoftInput(binding.otpView);
        UserUtils.visible(binding.loaderResend);
        UserUtils.gone(binding.txtTimer);
        UserUtils.disable(binding.txtResend);
        APIInterface apiInterface = getApiInterface();
//        resendCall = apiInterface.login(phoneNumber);
//        resendCall.enqueue(new Callback<SignInResponseModel>() {
//            @Override
//            public void onResponse(Call<SignInResponseModel> call, Response<SignInResponseModel> response) {
//                SignInResponseModel signInResponseModel = null;
//                if (response.isSuccessful()) {
//                    signInResponseModel = response.body();
//                }
//                try {
//                    if (signInResponseModel != null) {
//                        if (signInResponseModel.getStatus().equals("3")) {
//                            List list = (List) signInResponseModel.getResponse();
//                            if (!list.isEmpty()) {
//                                String data = UserUtils.getString(list);
//                                TypeToken<List<SignInResponseModel.User>> userList = new TypeToken<List<SignInResponseModel.User>>() {
//                                };
//                                user = (SignInResponseModel.User) UserUtils.getList(data, userList.getType()).get(0);
//                                otp = signInResponseModel.getOtp();
//                                startTimer();
//                            }
//                        } else if (signInResponseModel.getStatus().equals("2")) {
//                            otp = signInResponseModel.getOtp();
//                            startTimer();
//                        }
//                    } else {
//                        onApiFailure();
//                    }
//                } catch (Exception e) {
//                    onApiFailure();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SignInResponseModel> call, Throwable t) {
//                if (call.isCanceled()) {
//                    return;
//                }
//                call.cancel();
//                onApiFailure();
//            }
//        });
    }

    private void onApiFailure() {
        UserUtils.gone(binding.loaderResend);
        UserUtils.enable(binding.txtResend);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (resendCall != null) {
            resendCall.cancel();
            resendCall = null;
        }
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    private void verfiyotp(String getotp) {
        final ProgressDialog progress = new ProgressDialog(OTPVerificationActivity.this);
        progress.setMessage("Please Wait..\n Your internet connection is slow");
        progress.setCancelable(false);
        progress.show();

        // mView.show(getSupportFragmentManager(), "");
        final String URL_PRODUCTS = RootURL.OTPVERIFICTION + "otp=" + getotp + "&number=" + phone + "&user_token=" + "1235455";
        Log.d("mncxvxcvxdfsfcv", URL_PRODUCTS);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_PRODUCTS, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("mncxvxcvxcv", response.toString());

                        progress.dismiss();
                        /// mView.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            Log.d("responsejhhhhhhhhgj...", String.valueOf(response));

                            //String msg= data.getString("msg");// get your msg
                            //  JSONObject data = jsonObject.getJSONObject("user_array");// now get your secong json object like this
                            if (jsonObject.getString("status").equals("1")) {
                                SharedPreferences sharedPreferences = getSharedPreferences(RootURL.PREFACCOUNT, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("number", jsonObject.getString("number"));
                                editor.putString("id", jsonObject.getString("id"));
                                editor.putString("username", jsonObject.getString("username"));
                                editor.putString("email", jsonObject.getString("email"));
                                editor.putString("status", "1");
                                editor.commit();

                                sharedPreference.putString("userId", jsonObject.getString("id"));
                                String firebaseidm = jsonObject.getString("number") + "@gmail.com";
                                loginfirebase(firebaseidm);
                                Toast.makeText(OTPVerificationActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                                if (jsonObject.getString("is_new_user").equals("1")) {
                                    registerUser(phone);
                                } else {
                                    binding.otpView.setOTP(String.valueOf(otp));
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            } else if (jsonObject.getString("status").equals("0")) {

                                Toast.makeText(OTPVerificationActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                            } else if (jsonObject.getString("status").equals("2")) {
                                String firebaseidm = jsonObject.getString("number") + "@gmail.com";
                                regfirebase(firebaseidm);

                                SharedPreferences sharedPreferences = getSharedPreferences(RootURL.PREFACCOUNT, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("number", jsonObject.getString("number"));
                                editor.putString("id", jsonObject.getString("id"));
                                editor.putString("status", "2");
                                editor.commit();
                                Toast.makeText(OTPVerificationActivity.this, "You have to update your profile", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                //startActivity(intent);
                                //  finish();
                                if (jsonObject.getString("is_new_user").equals("1")) {
                                    registerUser(phone);
                                } else {
                                    Intent intent2 = new Intent(getApplicationContext(), HomeActivity.class);
                                    intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent2);
                                    finish();
                                }

                            }

                        } catch (JSONException e) {

                        }

                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                        //      progressbar.setVisibility(View.GONE);
                        progress.dismiss();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);

    }

    private void loginfirebase(String number) {


        FirebaseAuth.getInstance().signInWithEmailAndPassword(number, "12345678").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseInstanceId.getInstance().getInstanceId()
                            .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                @Override
                                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                    if (!task.isSuccessful()) {
                                        return;
                                    }

                                    String token = task.getResult().getToken();
                                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    Log.d("adjkskjl", userid);

                                    FirebaseDatabase.getInstance().getReference().child("Users").child(userid).child("token").setValue(token).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                            } else {
                                                Log.d(TAG, "uploadToken failed: " + task.getException().getMessage());
                                            }
                                        }
                                    });

                                }
                            });


                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    Log.d(TAG, "signIn failed: " + task.getException().getMessage());


                }
            }
        });

    }

    private void regfirebase(String firebaseidm) {
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(firebaseidm, "12345678").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                    if (firebaseUser != null) {
                        String userid = firebaseUser.getUid();

                        // "Packing" user data
                        Log.i("dfsfsfsd", userid);

                        Map map = new HashMap<>();
                        map.put("token", FirebaseInstanceId.getInstance().getToken());
                        map.put("name", "User" + firebaseidm);
                        map.put("email", firebaseidm);
                        map.put("phone", sharedpreferences.getString("number", ""));
                        map.put("status", "Welcome to my Profile!");
                        map.put("image", "default");
                        map.put("cover", "default");
                        map.put("date", ServerValue.TIMESTAMP);

                        // Uploading user data

                        FirebaseDatabase.getInstance().getReference().child("Users").child(userid).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                   /* Toast.makeText(SignupActivity.this, "sfsfdfsf", Toast.LENGTH_SHORT).show();
                                    //registerButton.setProgress(100);

                                    Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                                    startActivity(intent);
                                    finish();*/
                                    //login_user();
                                } else {
                                    //  login_user();
                                    Log.d(TAG, "registerData failed: " + task.getException().getMessage());
                                }
                            }
                        });
                    }
                } else {
                    login_userfirbeasetrue(firebaseidm);

                    Log.d(TAG, "createUser failed: " + task.getException().getMessage());


                }
            }
        });
    }

    private void login_userfirbeasetrue(String firebaseidm) {
        //     Toast.makeText(getApplicationContext(), firebaseidm, Toast.LENGTH_LONG).show();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(firebaseidm, "12345678").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String token = FirebaseInstanceId.getInstance().getToken();
                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Log.d("adjkskjl", userid);
                    // Updating user device token

                    FirebaseDatabase.getInstance().getReference().child("Users").child(userid).child("token").setValue(token).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //   startActivity(new Intent(getApplicationContext(), MainActivity.class));

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
                            } else {
                                Log.d(TAG, "uploadToken failed: " + task.getException().getMessage());
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    Log.d(TAG, "signIn failed: " + task.getException().getMessage());


                }
            }
        });
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void registerUser(String mobile) {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("isSkip", 0); // 0 - for private mode

        if(pref.getString("skip","").equals("Yes"))
        {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            fetchdata1(mobile);
        }

    }




    private void fetchdata1(String mobile) {

        ProgressDialog progressDialog;
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please wait...");
        progressDialog.show();
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        String url= RootURL.FETCHCALLINTAKEDORM+"?user_id="+sharedpreferences.getString("id", "");

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    progressDialog.dismiss();
                    Log.d("ghsdsdasdasdsdag", response.toString());

                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    JSONArray jsonArray = jsonObject.getJSONArray("records");

                    if(!String.valueOf(jsonArray.length()).equals("0"))
                    {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(OTPVerificationActivity.this, RegisterUser1.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("mobile", mobile);
                        startActivity(intent);
                        finish();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    Intent intent = new Intent(OTPVerificationActivity.this, RegisterUser1.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("mobile", mobile);
                    startActivity(intent);
                    finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //   Toast.makeText(RegisterUser1.this, error.toString(), Toast.LENGTH_LONG).show();
//                Log.i(TAG,"Error :" + error.toString());
                progressDialog.dismiss();
                Intent intent = new Intent(OTPVerificationActivity.this, RegisterUser1.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
                finish();

            }
        });

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(OTPVerificationActivity.this).add(mStringRequest);
        mRequestQueue.add(mStringRequest);
    }

}
