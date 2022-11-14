package com.ksbm_astroexpert.ui.signin;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Chat.RegisterUser;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.SignInActivityBinding;
import com.ksbm_astroexpert.network.APIInterface;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.ksbm_astroexpert.ui.otpverification.OTPVerificationActivity;
import com.ksbm_astroexpert.ui.signup.SignUpActivity;
import com.ksbm_astroexpert.ui.splash.SplashActivity;
import com.ksbm_astroexpert.ui.utils.KeyboardUtils;
import com.ksbm_astroexpert.ui.utils.UserUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignInActivity extends BaseActivity {

    private SignInActivityBinding signInActivityBinding;
    private final int RESOLVE_HINT = 1001;
    private GoogleApiClient googleApiClient;
    private CompositeDisposable compositeDisposable;


    AlertDialog.Builder builder;

    @Override
    protected int getLayout() {
        return R.layout.sign_in_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        builder = new AlertDialog.Builder(this);

        signInActivityBinding = (SignInActivityBinding) getBinding();
        signInActivityBinding.btnLogin.setOnClickListener(v -> {
//            if (isValidated()) {
//                Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
//                startActivity(intent);
//                finish();
//            }
            // doLogin();

            if(signInActivityBinding.tnccheck.isSelected())
            {
                loginvolley();
            }
            else
            {
                Toast.makeText(this, "Please accept Terms and Conditions", Toast.LENGTH_SHORT).show();
            }

        });



        signInActivityBinding.txtcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uncomment the below code to Set the message and title from the strings.xml file
//            builder.setMessage(R.string.dialog_message) .setTitle(R.string.Terms_and_Conditions);

                //Setting message manually and performing action on button click
                builder.setMessage(R.string.dialog_message)
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();

//                            if(signInActivityBinding.tnccheck.isChecked())
//                            {
//                                signInActivityBinding.tnccheck.setSelected(false);
//                                signInActivityBinding.tnccheck.setChecked(false);
//                            }
//                            else
//                            {
//                                signInActivityBinding.tnccheck.setSelected(true);
//                                signInActivityBinding.tnccheck.setChecked(true);
//                            }

                            }
                        });
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            //  Action for 'NO' Button
//                            dialog.cancel();
//                        }
//                    });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle(R.string.Terms_and_Conditions);
                alert.show();
            }
        });




        signInActivityBinding.tnccheck.setOnClickListener(v -> {

            if(signInActivityBinding.tnccheck.isSelected())
            {
                signInActivityBinding.tnccheck.setSelected(false);
                signInActivityBinding.tnccheck.setChecked(false);
            }
            else
            {
                signInActivityBinding.tnccheck.setSelected(true);
                signInActivityBinding.tnccheck.setChecked(true);
            }

        });

        signInActivityBinding.txtSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
        signInActivityBinding.imgClose.setOnClickListener(v -> finish());
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.CREDENTIALS_API)
                .build();
        requestHint();
    }

    private void loginvolley()
    {
        UserUtils.invisible(signInActivityBinding.txtErrMobile);
        String phoneNumber = UserUtils.isNotNull(signInActivityBinding.edtMobileNumber);
        if (TextUtils.isEmpty(phoneNumber) || UserUtils.isValidPhoneNumber(phoneNumber))
        {
            signInActivityBinding.txtErrMobile.setVisibility(View.VISIBLE);
            return;
        }

        final ProgressDialog progress = new ProgressDialog(SignInActivity.this);
        progress.setMessage("Please Wait..");
        progress.setCancelable(false);
        progress.show();
        final String URL_Product = RootURL.LOGIN+"&number="+phoneNumber;/*+"&token="+SharedHelper.getKey(Phone_Login.this, "device_token")*/
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_Product, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            progress.dismiss();
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            Log.d("response...", String.valueOf(response));
                            Log.i("sfsfsf",URL_Product);

                            //String msg= data.getString("msg");// get your msg
                            //  JSONObject data = jsonObject.getJSONObject("user_array");// now get your secong json object like this
                            if (jsonObject.getString("status").equals("1")) {

                                Intent intent = new Intent(SignInActivity.this, OTPVerificationActivity.class);
                                intent.putExtra("phone", phoneNumber);
                                intent.putExtra("otp",jsonObject.getString("otp"));
                                startActivity(intent);
                                finish();

                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                            else if (jsonObject.getString("status").equals("0")) {

                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                            else
                                {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                        } catch (JSONException e) {
                            progress.dismiss();
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "There is seem to be network issue, check internet connection and try again", Toast.LENGTH_LONG).show();
                //Toast.makeText(SignInActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                progress.dismiss();
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

    private void doLogin() {
        UserUtils.invisible(signInActivityBinding.txtErrMobile);
        String phoneNumber = UserUtils.isNotNull(signInActivityBinding.edtMobileNumber);
        if (TextUtils.isEmpty(phoneNumber) || UserUtils.isValidPhoneNumber(phoneNumber)) {
            signInActivityBinding.txtErrMobile.setVisibility(View.VISIBLE);
            return;
        }
        compositeDisposable = new CompositeDisposable();
        APIInterface apiInterface = getApiInterface();
        Single<SignInResponseModel> signInResponseModelObservable = apiInterface.login(phoneNumber);
        signInResponseModelObservable.subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Object o) {


                        SignInResponseModel signInResponseModel = (SignInResponseModel) o;

                        Log.i("dfsdfsdfsdf",signInResponseModel.toString());
                        try {
                            if (signInResponseModel.getStatus().equals("3")) {
                                List list = (List) signInResponseModel.getResponse();
                                if (!list.isEmpty()) {
                                    String data = UserUtils.getString(list);
                                    TypeToken<List<SignInResponseModel.User>> userList = new TypeToken<List<SignInResponseModel.User>>() {
                                    };
                                    //Toast.makeText(SignInActivity.this,"bbbb" , Toast.LENGTH_SHORT).show();

                                    SignInResponseModel.User user = (SignInResponseModel.User) UserUtils.getList(data, userList.getType()).get(0);
                                    Intent intent = new Intent(SignInActivity.this, OTPVerificationActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("user", user);
                                    bundle.putInt("otp", signInResponseModel.getOtp());
                                    bundle.putString("phone", phoneNumber);
                                    bundle.putString("status", signInResponseModel.getStatus());
                                    intent.putExtra("data", bundle);
                                    startActivity(intent);
                                    ////   Toast.makeText(SignInActivity.this,signInResponseModel.getOtp() , Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            } else if (signInResponseModel.getStatus().equals("2")) {
                               // Toast.makeText(SignInActivity.this,signInResponseModel.getStatus() , Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(SignInActivity.this, OTPVerificationActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("otp", 1234);
                                bundle.putString("phone", phoneNumber);
                                intent.putExtra("data", bundle);
                                bundle.putString("status", signInResponseModel.getStatus());
                                startActivity(intent);
                                finish();
                            } else {
                                onApiFailure();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            onApiFailure();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        onApiFailure();
                    }
                });
        KeyboardUtils.hideSoftInput(signInActivityBinding.btnLogin);
        UserUtils.visible(signInActivityBinding.loaderSignin);
        UserUtils.gone(signInActivityBinding.btnLogin);
    }

    private void onApiFailure() {
        UserUtils.toast(SignInActivity.this, R.string.toast_server_error);
        UserUtils.gone(signInActivityBinding.loaderSignin);
        UserUtils.visible(signInActivityBinding.btnLogin);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(SignInActivity.this, SplashActivity.class);
        startActivity(intent);
        finish();

    }

    // Construct a request for phone numbers and show the picker
    private void requestHint() {
        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();

        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(
                googleApiClient, hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(),
                    RESOLVE_HINT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {

        }
    }

    // Obtain the phone number from the result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK) {
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
//                 credential.getId(); <-- E.164 format phone number on 10.2.+ devices
                try {
                    String id = UserUtils.getPhoneNumberFromId(credential.getId());
                    signInActivityBinding.edtMobileNumber.setText(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
