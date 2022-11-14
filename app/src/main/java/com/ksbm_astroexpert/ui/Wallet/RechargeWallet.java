package com.ksbm_astroexpert.ui.Wallet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.ksbm_astroexpert.Adapter.RechargeAdapter;
import com.ksbm_astroexpert.Adapter.RechargeAdapter2;
import com.ksbm_astroexpert.Chat.utils.ParseManager;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.ModelClass.AllRemidesModel;
import com.ksbm_astroexpert.ModelClass.ApplyCouponModel;
import com.ksbm_astroexpert.ModelClass.OnlineRechargeModel;
import com.ksbm_astroexpert.ModelClass.RechargeCustomerModel;
import com.ksbm_astroexpert.ModelClass.WalletModel;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.network.APIInterface;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

import static com.ksbm_astroexpert.network.ApiCallConsent.apiInterface;
import static com.ksbm_astroexpert.network.ApiCallConsent2.apiInterface2;


public class RechargeWallet extends Activity implements PaymentResultListener {
    String stOrderId, stStatus, stTxnid, stAmount;

    int Ok=0;

    // For Pay U Payment Integaration
    PayUmoneySdkInitializer.PaymentParam paymentParam;
    JSONObject jsonObj = null, resultJsonObj = null;
    TextView txtBalance, walletbalance;
    //RelativeLayout rs50card, rs100, rs150, rs200, rs250, rs500;
    ImageView backarrow;
    public  Context context=this;
    ProgressDialog progress;
    RecyclerView recyclerShowAMT,recycler_show_amt1;
    EditText codeEDT;
    TextView netpriceTXT, gstamountTXT, totalamoutdTXT, txtCouponDisPrice, txtCouponTotalDisAMT;
    RelativeLayout relCouponDis, relCouponDisAMT;
    double netamount;
    private static final String TAG = RechargeWallet.class.getSimpleName();
    SharedPreferences sharedpreferences;
    String ttamount = "",transidget = "",ttamountAPI = "";
    ArrayList<WalletModel.Response> arrListRecharge = new ArrayList<>();
    ArrayList<WalletModel.Response2> arrListRecharge2 = new ArrayList<>();
    String recharge_type="0",ExtraPErcentage1="0";


    String CupanValue="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_wallet);
        sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        txtBalance = findViewById(R.id.txtBalance);
        walletbalance = findViewById(R.id.walletbalance);
        recyclerShowAMT = findViewById(R.id.recycler_show_amt);
        recycler_show_amt1 = findViewById(R.id.recycler_show_amt1);
//        rs50card = findViewById(R.id.rs50card);
//        rs100 = findViewById(R.id.rs100);
//        rs150 = findViewById(R.id.rs150);
//        rs200 = findViewById(R.id.rs200);
//        rs250 = findViewById(R.id.rs250);
//        rs500 = findViewById(R.id.rs500);
        backarrow = findViewById(R.id.backarrow);

        walletbalnce();
        hitRechargeDataApi();



        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
      /*  rs50card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String netamount = "50.00";
                showCustomDialog(netamount);
                *//* *//*

            }
        });
        rs100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txnamoun = "100.00";
                showCustomDialog(txnamoun);

            }
        });
        rs150.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txnamoun = "150.00";

                showCustomDialog(txnamoun);

            }
        });
        rs200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txnamoun = "200.00";

                showCustomDialog(txnamoun);

            }
        });
        rs250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txnamoun = "250.00";

                showCustomDialog(txnamoun);

            }
        });
        rs500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txnamoun = "500.00";
                showCustomDialog(txnamoun);
            }
        });*/


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(Ok==2)
        {
            if(progress.isShowing())
            {
                progress.dismiss();
            }
        }
        else
        {
            startActivity(new Intent(RechargeWallet.this, HomeActivity.class));
        }


        Toast.makeText(RechargeWallet.this, "back", Toast.LENGTH_SHORT).show();

    }

    public void hitRechargeDataApi() {
        progress = new ProgressDialog(RechargeWallet.this);
        progress.setMessage("Please Wait..");
        progress.setCancelable(false);
        progress.show();
        Call<WalletModel> call1 = apiInterface.getRchage();
        call1.enqueue(new Callback<WalletModel>() {
            @Override
            public void onResponse(Call<WalletModel> call, retrofit2.Response<WalletModel> response) {
                String res = new Gson().toJson(response.body());
                progress.dismiss();
                Log.d("checkrssss", res);
                if (response.isSuccessful()) {
                    WalletModel walletModel = response.body();
                    if (walletModel.status.equals("1")) {
                        arrListRecharge = walletModel.response;
                        serRechargeRecycler();
                    } else {
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.snack_id), "Coupon Code Invailid", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else {
                    Toast.makeText(RechargeWallet.this, "" + response, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<WalletModel> call, Throwable t) {
                Toast.makeText(RechargeWallet.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                progress.dismiss();
                call.cancel();

            }
        });
    }
    void  serRechargeRecycler(){
        recycler_show_amt1.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(RechargeWallet.this, 1);
        recycler_show_amt1.setLayoutManager(layoutManager);
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(RechargeWallet.this, R.anim.recyclerview_anim_layout);
        recycler_show_amt1.setLayoutAnimation(layoutAnimationController);
        RechargeAdapter rechargeAdapter = new RechargeAdapter(RechargeWallet.this, arrListRecharge, new RechargeAdapter.RechargeListener() {
            @Override
            public void getAmount(String amt,String ExtraPercentage) {
                recharge_type="0";
                CupanValue="0";
                showCustomDialog(amt,ExtraPercentage);
            }
        });
        recycler_show_amt1.setAdapter(rechargeAdapter);

        hitRechargeDataApi2();
    }





    public void hitRechargeDataApi2() {
        progress = new ProgressDialog(RechargeWallet.this);
        progress.setMessage("Please Wait..");
        progress.setCancelable(false);
        progress.show();
        Call<WalletModel> call1 = apiInterface2.postRechargePlan(sharedpreferences.getString("id", ""));
        call1.enqueue(new Callback<WalletModel>() {
            @Override
            public void onResponse(Call<WalletModel> call, retrofit2.Response<WalletModel> response) {
                String res = new Gson().toJson(response.body());
                progress.dismiss();
                Log.d("checkrssss", res);
                if (response.isSuccessful()) {
                    WalletModel walletModel = response.body();
                    if (walletModel.status.equals("1")) {
                        arrListRecharge2 = walletModel.response2;
                        if(walletModel.firstoffer!=null && !walletModel.firstoffer.equals("0"))
                        {
                            recyclerShowAMT.setVisibility(View.GONE);
                        }
                        else
                        {
                            serRechargeRecycler2();
                        }

                    } else {
                        recyclerShowAMT.setVisibility(View.GONE);
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.snack_id), "Coupon Code Invailid", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else {
                    recyclerShowAMT.setVisibility(View.GONE);
                    Toast.makeText(RechargeWallet.this, "" + response, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<WalletModel> call, Throwable t) {
                Toast.makeText(RechargeWallet.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                progress.dismiss();
                recyclerShowAMT.setVisibility(View.GONE);
                call.cancel();
            }
        });
    }


    void  serRechargeRecycler2(){
        recyclerShowAMT.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(RechargeWallet.this, 1);
        recyclerShowAMT.setLayoutManager(layoutManager);
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(RechargeWallet.this, R.anim.recyclerview_anim_layout);
        recyclerShowAMT.setLayoutAnimation(layoutAnimationController);
        RechargeAdapter2 rechargeAdapter = new RechargeAdapter2(RechargeWallet.this, arrListRecharge2, new RechargeAdapter2.RechargeListener() {
            @Override
            public void getAmount(String amt,String ExtraPercentage) {
                recharge_type="1";
                CupanValue="0";
                showCustomDialog(amt,ExtraPercentage);
            }
        });
        recyclerShowAMT.setAdapter(rechargeAdapter);
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
                                txtBalance.setText(jsonObject1.getString("wallet"));
                                walletbalance.setText("Your Wallet Balance is " + "₹" + jsonObject1.getString("wallet"));
                                Log.i("dfsdsd", jsonObject1.getString("wallet"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "There is seem to be network issue, check internet connection and try again", Toast.LENGTH_LONG).show();
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

/*

    private void launchPayU(String orderId, String amount)
    {

        PayUmoneyConfig payUmoneyConfig = PayUmoneyConfig.getInstance();

        //Use this to set your custom text on result screen button
        payUmoneyConfig.setDoneButtonText("Done");

        //Use this to set your custom title for the activity
        payUmoneyConfig.setPayUmoneyActivityTitle(getString(R.string.app_name));


        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();


        BaseEnvironment appBaseEnvironment = ((App) getApplication()).getBaseEnvironment();
        // builder.setAmount(amount)
        builder.setAmount(String.valueOf(amount))
                .setTxnId(orderId.trim())
                .setPhone("9799569458")
                .setProductName(getString(R.string.app_name)+" Wallet")
                .setFirstName("Rohit sharma")
                .setEmail("rohitsharma9799569458@gmail.com")
                .setsUrl(appBaseEnvironment.surl())
                .setfUrl(appBaseEnvironment.furl())
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setIsDebug(appBaseEnvironment.debug())
                .setKey(appBaseEnvironment.merchant_Key())
                .setMerchantId(appBaseEnvironment.merchant_ID());

        try {

            paymentParam = builder.build();

            generateHashFromServer(paymentParam);

        } catch (Exception e) {

        }
    }

    public static String hashCal(String type, String str) {
        byte[] hashseq = str.getBytes();
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (int i = 0; i<messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF &messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException nsae) {

        }
        return hexString.toString();
    }

    public void generateHashFromServer(PayUmoneySdkInitializer.PaymentParam paymentParam)
    {
        HashMap<String, String> params = paymentParam.getParams();

        // lets create the post params
        StringBuffer postParamsBuffer = new StringBuffer();
        postParamsBuffer.append(concatParams(PayUmoneyConstants.KEY, params.get(PayUmoneyConstants.KEY)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.AMOUNT, params.get(PayUmoneyConstants.AMOUNT)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.TXNID, params.get(PayUmoneyConstants.TXNID)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.EMAIL, params.get(PayUmoneyConstants.EMAIL)));
        postParamsBuffer.append(concatParams("productinfo", params.get(PayUmoneyConstants.PRODUCT_INFO)));
        postParamsBuffer.append(concatParams("firstname", params.get(PayUmoneyConstants.FIRSTNAME)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF1, params.get(PayUmoneyConstants.UDF1)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF2, params.get(PayUmoneyConstants.UDF2)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF3, params.get(PayUmoneyConstants.UDF3)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF4, params.get(PayUmoneyConstants.UDF4)));
        postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF5, params.get(PayUmoneyConstants.UDF5)));


        String postParams = postParamsBuffer.charAt(postParamsBuffer.length() - 1) == '&' ? postParamsBuffer.substring(0, postParamsBuffer.length() - 1).toString() : postParamsBuffer.toString();

        // lets make an api call
        GetHashesFromServerTask getHashesFromServerTask = new GetHashesFromServerTask();
        getHashesFromServerTask.execute(postParams);
    }


    protected String concatParams(String key, String value)
    {
        return key + "=" + value + "&";
    }


    */
/**
 * This AsyncTask generates hash from server.
 *//*

    private class GetHashesFromServerTask extends AsyncTask<String, String, String>
    {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(RechargeWallet.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... postParams) {

            String merchantHash = "";
            try {
                //TODO Below url is just for testing purpose, merchant needs to replace this with their server side hash generation url
                URL url = new URL(RootURL.Base_URL+"/get_hash.php");
              //  URL url = new URL("http://jumangigames.com/get_hash.php");

                String postParam = postParams[0];

                byte[] postParamsByte = postParam.getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", String.valueOf(postParamsByte.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postParamsByte);

                InputStream responseInputStream = conn.getInputStream();
                StringBuffer responseStringBuffer = new StringBuffer();
                byte[] byteContainer = new byte[1024];
                for (int i; (i = responseInputStream.read(byteContainer)) != -1; ) {
                    responseStringBuffer.append(new String(byteContainer, 0, i));
                }

                System.out.println("Response "+responseStringBuffer.toString());
                JSONObject response = new JSONObject(responseStringBuffer.toString());

                Iterator<String> payuHashIterator = response.keys();
                while (payuHashIterator.hasNext()) {
                    String key = payuHashIterator.next();
                    switch (key) {
                        */

    /**
     * This hash is mandatory and needs to be generated from merchant's server side
     *//*

                        case "payment_hash":
                            merchantHash = response.getString(key);
                            break;
                        default:
                            break;
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return merchantHash;
        }

        @Override
        protected void onPostExecute(String merchantHash) {
            super.onPostExecute(merchantHash);

            progressDialog.dismiss();
            if (merchantHash.isEmpty() || merchantHash.equals("")) {

                // NGToast.makeText(CartDetails.this,"Could not generate hash", NGToast.LENGTH_LONG, NGToast.TYPE_ERROR).show();
            } else {
                paymentParam.setMerchantHash(merchantHash);
                PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, RechargeWallet.this, R.style.AppTheme_default, false);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Result Code is -1 send from Payumoney activity
        Log.d("ProductOrderSummary", "request code " + requestCode + " resultcode " + resultCode);
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data !=
                null)
        {
            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager
                    .INTENT_EXTRA_TRANSACTION_RESPONSE);

            ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);

            // Check which object is non-null
            if (transactionResponse != null && transactionResponse.getPayuResponse() != null)
            {
                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL))
                {
                    // Response from Payumoney

                    String payuResponse = transactionResponse.getPayuResponse();

                    // Response from SURl and FURL
                    String merchantResponse = transactionResponse.getTransactionDetails();


                    try {

                        jsonObj = new JSONObject(payuResponse);

                        resultJsonObj = new JSONObject(jsonObj.getString("result"));

                        stStatus = resultJsonObj.getString("status");
                        stTxnid = resultJsonObj.getString("txnid");
                        stAmount = resultJsonObj.getString("amount");

                        //  orderStatus(stTxnid,stAmount,stStatus);


                        final JSONObject json1 = new JSONObject();
                        try {
                            json1.put("amount", stAmount);
                            json1.put("user_id","1");
                           // json1.put("response",stStatus);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        final String url = RootURL.ADDMONEY;
                        Log.i("gdfgvgbnn", url+json1.toString());

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            Log.d("ghgdfgrtr", response.toString());


                                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                                            Toast.makeText(RechargeWallet.this, "Amount sucessfully added to your account", Toast.LENGTH_SHORT).show();

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

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




                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }




                } else {
                    // orderStatus(stOrderId,"0.0","failure");
                }



            } else if (resultModel != null && resultModel.getError() != null) {
                //Log.d("Pay U", "Error response : " + resultModel.getError().getTransactionResponse());
            } else {
                //Log.d("Pay U", "Both objects are null!");
            }
        }


    }
*/


    private void showCustomDialog(String txnamoun,String ExtraPErcentage) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        netpriceTXT = dialogView.findViewById(R.id.netprice);
        gstamountTXT = dialogView.findViewById(R.id.gstamount);
        totalamoutdTXT = dialogView.findViewById(R.id.totalamoutd);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        Button applyBTN = dialogView.findViewById(R.id.btn_apply_coupon);
        codeEDT = dialogView.findViewById(R.id.edt_copon_code);
        txtCouponDisPrice = dialogView.findViewById(R.id.text_coupon_dis);
        txtCouponTotalDisAMT = dialogView.findViewById(R.id.text_total_dis_amt);
        relCouponDis = dialogView.findViewById(R.id.rel_coupon_dis);
        relCouponDisAMT = dialogView.findViewById(R.id.rel_coupon_dis_amt);

        netamount = Double.parseDouble(txnamoun);
        double gst = netamount * 0.18;

        netpriceTXT.setText(String.valueOf(netamount));
        gstamountTXT.setText(String.valueOf(gst));
        double c = netamount + gst;
        totalamoutdTXT.setText(String.valueOf(c));
        ttamount = String.valueOf(c);
        ttamountAPI = String.valueOf(netamount);
        ExtraPErcentage1=ExtraPErcentage;

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });
        applyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (codeEDT.getText().toString().equals("")) {
                    Toast.makeText(RechargeWallet.this, "Before enter code", Toast.LENGTH_SHORT).show();
                } else {
                    hitCoupnApi();
                }
            }
        });

    }

    //-------------------Apply Coupon Code-------
    public void hitCoupnApi() {
        ttamountAPI="0";
        progress = new ProgressDialog(RechargeWallet.this);
        progress.setMessage("Please Wait..");
        progress.setCancelable(false);
        progress.show();
        Call<ApplyCouponModel> call1 = apiInterface.postCoupon(codeEDT.getText().toString());
        call1.enqueue(new Callback<ApplyCouponModel>() {
            @Override
            public void onResponse(Call<ApplyCouponModel> call, retrofit2.Response<ApplyCouponModel> response) {
                String res = new Gson().toJson(response.body());
                progress.dismiss();
                Log.d("checklk", res);
                if (response.isSuccessful()) {
                    ApplyCouponModel applyCouponModel = response.body();
                    if (applyCouponModel.status.equals("1")) {
                        int coupon_value = Integer.parseInt(applyCouponModel.data.couponValue);
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.snack_id), "" + applyCouponModel.msg, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        if (applyCouponModel.data.couponType.equals("Percentage")) {

                            ////Before Code
                            //                            double dis_amt = (netamount * coupon_value) / 100;
//                            relCouponDis.setVisibility(View.VISIBLE);
//                            relCouponDisAMT.setVisibility(View.VISIBLE);
//                            txtCouponDisPrice.setText(String.valueOf(dis_amt));
//                            double total_Disamt = netamount - dis_amt;
//                            txtCouponTotalDisAMT.setText(String.valueOf(total_Disamt));
//                            double gst = total_Disamt * 0.18;
//                            gstamountTXT.setText(String.valueOf(gst));
////                            double final_amt = total_Disamt + gst;
//                            double final_amt = total_Disamt;
//                            totalamoutdTXT.setText(String.valueOf(final_amt));
//                            ttamount = String.valueOf(final_amt);
//


                            ///////////////////



                            ////New Code

                            double dis_amt = (netamount * coupon_value) / 100;
                            relCouponDis.setVisibility(View.VISIBLE);
                            relCouponDisAMT.setVisibility(View.GONE);
                            txtCouponDisPrice.setText(String.valueOf(dis_amt));
//                            double total_Disamt = netamount + dis_amt;
//                            txtCouponTotalDisAMT.setText(String.valueOf(total_Disamt));
                            double gst = netamount * 0.18;
                            gstamountTXT.setText(String.valueOf(gst));
//                            double final_amt = total_Disamt + gst;
                            double final_amt = netamount+gst;
                            totalamoutdTXT.setText(String.valueOf(final_amt));
                            ttamount = String.valueOf(final_amt);
                            ttamountAPI = String.valueOf(netamount);
                            CupanValue = String.valueOf(dis_amt);

                        } else {
                            int dis_amt = Integer.parseInt(applyCouponModel.data.couponValue);
                            relCouponDis.setVisibility(View.VISIBLE);
                            relCouponDisAMT.setVisibility(View.GONE);
                            txtCouponDisPrice.setText(String.valueOf(dis_amt));
//                            double total_amt = netamount + dis_amt;
                            double gst = netamount * 0.18;
                            gstamountTXT.setText(String.valueOf(gst));
//                            double final_amt = total_amt + gst;
                            double final_amt = netamount+gst;
                            totalamoutdTXT.setText(String.valueOf(final_amt));
                            ttamount = String.valueOf(final_amt);
                            ttamountAPI = String.valueOf(netamount);
                            CupanValue = String.valueOf(dis_amt);
                        }

                    } else {

                        // Toast.makeText(RechargeWallet.this, "Coupon Code Invailid", Toast.LENGTH_SHORT).show();
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.snack_id), "Coupon Code Invailid", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        CupanValue="0";
                    }
                } else {
                    CupanValue="0";
                    Toast.makeText(RechargeWallet.this, "" + response, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ApplyCouponModel> call, Throwable t) {
                Toast.makeText(RechargeWallet.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                progress.dismiss();
                CupanValue="0";
                call.cancel();

            }
        });
    }

//-------------------Razorpaypayment gateway-------
    public void startPayment() {
         /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        double total = Double.parseDouble(ttamount);
        total = total * 100;
        final Activity activity = this;
        final Checkout co = new Checkout();
        // co.setKeyID("rzp_test_s3oxLIxxZxksxM");
        try {
            JSONObject options = new JSONObject();
            options.put("name", "AstroExperts");
            options.put("description", "astro payment");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "http://xporusinternational.in/surmanthan/uploads/tones_img/4_1591009029_tone.jpg");
            options.put("currency", "INR");
            options.put("amount", total);
            JSONObject preFill = new JSONObject();
            preFill.put("email", sharedpreferences.getString("email", ""));
            preFill.put("contact", sharedpreferences.getString("number", ""));
            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            progress.dismiss();
            // Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
            Log.i("dfsdfsdfs", razorpayPaymentID);
            paymentcapture(razorpayPaymentID);
            if(!CupanValue.equals("0") && CupanValue!=null)
            {

            }
            else
            {
                CupanValue="0";
            }
            hitOnlineRechargeApi(razorpayPaymentID,recharge_type,ExtraPErcentage1,CupanValue);
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int code, String response) {
        try {
            progress.dismiss();
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }

    private void paymentcapture(final String razorpayPaymentID) throws JSONException {
        ProgressDialog progress = new ProgressDialog(RechargeWallet.this);
        progress.setMessage("Please Wait..");
        progress.setCancelable(false);
        progress.show();
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        final JSONObject json1 = new JSONObject();
        json1.put("payment_id", razorpayPaymentID);
        json1.put("amount", ttamountAPI);
        Log.i("checkparms", json1.toString());
        final String URL_Product = RootURL.PAYMENTCAPTURE;
        Log.d("Dfsdfsdvvvfds", URL_Product + json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, URL_Product, json1,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("Dfsfsvvvdfsdfs", response.toString());
                        try {
                            progress.dismiss();
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            if (jsonObject.getString("status").equals("1")) {
                                progress.dismiss();
                                Toast.makeText(getApplicationContext(), "Please Wait Your transaction is in progress...", Toast.LENGTH_SHORT).show();
//                                hitRechargeCustomerApi();
                            } else {
                                progress.dismiss();
                                new SweetAlertDialog(RechargeWallet.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Transaction Alert")
                                        .setContentText("Transaction declined your payement will be auto return in 2 to 3 working days")
                                        .setConfirmText("Ok")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismissWithAnimation();
                                            }
                                        }).show();
                                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progress.dismiss();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "Your Account is under verified", Toast.LENGTH_LONG).show();
                progress.dismiss();
                Log.i("Dfsdfsdf", error.toString());
            }
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


    public void hitRechargeCustomerApi() {

        Ok=Ok+1;

        progress = new ProgressDialog(RechargeWallet.this);
        progress.setMessage("Please Wait5..");
        progress.setCancelable(false);
        progress.show();
        Call<RechargeCustomerModel> call1 = apiInterface.postRechageAmt(sharedpreferences.getString("id", ""),ttamountAPI,transidget);
        call1.enqueue(new Callback<RechargeCustomerModel>() {
            @Override
            public void onResponse(Call<RechargeCustomerModel> call, retrofit2.Response<RechargeCustomerModel> response) {
                String res = new Gson().toJson(response.body());
                progress.dismiss();
                Log.d("checklk", res);
                if (response.isSuccessful()) {
                    RechargeCustomerModel applyCouponModel = response.body();
                    if (applyCouponModel.status.equals("1")) {

                        Ok=Ok+1;
                        new SweetAlertDialog(RechargeWallet.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Congratulation")
                                .setContentText("Amount sucessfully added to your account")
                                .setConfirmText("Ok")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                        startActivity(new Intent(RechargeWallet.this, HomeActivity.class));
                                    }
                                }).show();

                    } else {
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.snack_id), "Error", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else {
                    Toast.makeText(RechargeWallet.this, "" + response, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<RechargeCustomerModel> call, Throwable t) {
                Toast.makeText(RechargeWallet.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                progress.dismiss();
                call.cancel();
            }
        });
    }

    private void hitOnlineRechargeApi(final String razorpayPaymentID,final String recharge_type1,final  String ExtraPErcentage, final String CupanAmt) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RootURL.ONLINE_RECHARGE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ressopnseee", response);
                try {
                    OnlineRechargeModel model = ParseManager.getInstance().fromJSON(response, OnlineRechargeModel.class);
                    if (model != null) {
                        if (model.status.equals("1")){
                            Log.d("checkress",model.response);
                            String Transid = model.transid;
                            if(!Transid.equals("null") || Transid!=null || !Transid.equals(""))
                            {
                                transidget=Transid;
                            }

                            hitRechargeCustomerApi();

                        }else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("dsdjld",volleyError.getMessage());
                // progressbar.hideProgress();


            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("user_id", sharedpreferences.getString("id", ""));
                hashMap.put("amount", ttamountAPI);
                hashMap.put("type", "credit");
                hashMap.put("p_id",razorpayPaymentID);
                hashMap.put("status","success");
                hashMap.put("recharge_type",recharge_type1);
                hashMap.put("extra_percent",ExtraPErcentage);
                hashMap.put("coupan_discount",CupanAmt);
            Log.d("parumsss", String.valueOf(hashMap));
                return hashMap;
            }
        };
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }


/*
    private void datasendserver(String razorpayPaymentID) throws JSONException {
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        progress = new ProgressDialog(RechargeWallet.this);
        progress.setMessage("Please Wait6..");
        progress.setCancelable(false);
        progress.show();
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("amount", ttamount);
            json1.put("user_id", sharedpreferences.getString("id", ""));
            // json1.put("response",stStatus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.ADDMONEY;
         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ghgdfgrtr", response.toString());
                            new SweetAlertDialog(RechargeWallet.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Congratulation")
                                    .setContentText("Amount sucessfully added to your account")
                                    .setConfirmText("Ok")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                            startActivity(new Intent(RechargeWallet.this, HomeActivity.class));
                                        }
                                    }).show();

                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            Toast.makeText(RechargeWallet.this, "", Toast.LENGTH_SHORT).show();

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
*/




}