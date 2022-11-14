package com.ksbm_astroexpert.ui.signup;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.SignUpActivityBinding;
import com.ksbm_astroexpert.network.APIInterface;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.otpverification.OTPVerificationActivity;
import com.ksbm_astroexpert.ui.signin.SignInResponseModel;
import com.ksbm_astroexpert.ui.utils.KeyboardUtils;
import com.ksbm_astroexpert.ui.utils.UserUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends BaseActivity {

    private SignUpActivityBinding signUpActivityBinding;
    private final int RESOLVE_HINT = 1001;
    private GoogleApiClient googleApiClient;
    private Call<SignInResponseModel> signUpCall;


    @Override
    protected int getLayout() {
        return R.layout.sign_up_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpActivityBinding = (SignUpActivityBinding) getBinding();
        signUpActivityBinding.btnSignUp.setOnClickListener(v -> {
            doSignUp();
        });
        signUpActivityBinding.txtLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, SignUpActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
        signUpActivityBinding.imgClose.setOnClickListener(v -> finish());
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.CREDENTIALS_API)
                .build();
        requestHint();
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
                    signUpActivityBinding.edtMobileNumber.setText(id);
                    signUpActivityBinding.edtFullName.setText(credential.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doSignUp() {
        UserUtils.invisible(signUpActivityBinding.txtErrFullName);
        UserUtils.invisible(signUpActivityBinding.txtErrMobile);
        String fullName = UserUtils.isNotNull(signUpActivityBinding.edtFullName);
        boolean isValidated = false;
        if (TextUtils.isEmpty(fullName)) {
            signUpActivityBinding.txtErrFullName.setVisibility(View.VISIBLE);
            isValidated = true;
        }
        String phoneNumber = UserUtils.isNotNull(signUpActivityBinding.edtMobileNumber);
        if (TextUtils.isEmpty(phoneNumber) || UserUtils.isValidPhoneNumber(phoneNumber)) {
            signUpActivityBinding.txtErrMobile.setVisibility(View.VISIBLE);
            isValidated = true;
        }
        if(isValidated){
            return;
        }
        KeyboardUtils.hideSoftInput(signUpActivityBinding.btnSignUp);
        UserUtils.visible(signUpActivityBinding.loaderSignUp);
        UserUtils.gone(signUpActivityBinding.btnSignUp);
        APIInterface apiInterface = getApiInterface();
        signUpCall = apiInterface.signUp(phoneNumber,fullName,"1");
        signUpCall.enqueue(new Callback<SignInResponseModel>() {
            @Override
            public void onResponse(Call<SignInResponseModel> call, Response<SignInResponseModel> response) {
                SignInResponseModel signInResponseModel = null;
                if (response.isSuccessful()) {
                    signInResponseModel = response.body();
                }
                try {
                    if (signInResponseModel != null) {
                        if (signInResponseModel.getStatus().equals("1")) {
                            List list = (List) signInResponseModel.getResponse();
                            if (!list.isEmpty()) {
                                String data = UserUtils.getString(list);
                                TypeToken<List<SignInResponseModel.User>> userList = new TypeToken<List<SignInResponseModel.User>>() {
                                };
                                SignInResponseModel.User user = (SignInResponseModel.User) UserUtils.getList(data, userList.getType()).get(0);
                                Intent intent = new Intent(SignUpActivity.this, OTPVerificationActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("user", user);
                                bundle.putInt("otp", signInResponseModel.getOtp());
                                bundle.putString("phone", phoneNumber);
                                bundle.putString("status", signInResponseModel.getStatus());
                                intent.putExtra("data", bundle);
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        onApiFailure();
                    }
                } catch (Exception e) {
                    onApiFailure();
                }
            }

            @Override
            public void onFailure(Call<SignInResponseModel> call, Throwable t) {
                if (call.isCanceled()) {
                    return;
                }
                call.cancel();
                onApiFailure();
            }
        });
    }

    private void onApiFailure() {
        UserUtils.toast(SignUpActivity.this, R.string.toast_server_error);
        UserUtils.gone(signUpActivityBinding.loaderSignUp);
        UserUtils.visible(signUpActivityBinding.btnSignUp);
    }
}
