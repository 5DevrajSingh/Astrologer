package com.ksbm_astroexpert.ui.search;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Adapter.AstrologerAdapter;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.InternetCheck.ConnectivityReceiver;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.SearchActivityBinding;
import com.ksbm_astroexpert.databinding.SearchExpertsItemBinding;
import com.ksbm_astroexpert.network.APIInterface;
import com.ksbm_astroexpert.ui.Wallet.RechargeWallet;
import com.ksbm_astroexpert.ui.agentprofile.AgentProfileActivity;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.base.BaseProgressRecyclerListAdapter;
import com.ksbm_astroexpert.ui.home.ExpertsResponseModel;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.ksbm_astroexpert.ui.utils.KeyboardUtils;
import com.ksbm_astroexpert.ui.utils.UserUtils;
import com.ksbm_astroexpert.view.ImageData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends BaseActivity {

    private SearchActivityBinding binding;
    private BaseProgressRecyclerListAdapter baseRecyclerAdapter;
    private APIInterface apiInterface;
    private FilterModel filterModel = null;
    private boolean screenType;
    private CompositeDisposable compositeDisposable;
    private List<ImageData> imageData2=new ArrayList<>();
    AstrologerAdapter categoriesAdapter;

    TextView txtBalance;

    private BroadcastReceiver mNetworkReceiver;


    String genderstr,concernstr;
    Spinner genderspiner,concern;
    String[] gender = { "Exp : high to low", "Exp : low to high", "Price : high to low","Price : low to high"};
    EditText etSearch;

    String bir= "DESC";
    @Override
    protected int getLayout() {
        return R.layout.search_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (SearchActivityBinding) getBinding();
        init();
        etSearch = findViewById(R.id.etSearch);
        txtBalance = findViewById(R.id.txtBalance);
        genderspiner = findViewById(R.id.genderspiner);

        ///////////////added by shivam//////////
        mNetworkReceiver = new ConnectivityReceiver();
        registerNetworkBroadcastForNougat();

        Astrologer();
        walletbalnce();
       // etSearch=(EditText)findViewById(R.id.etSearch);
        etSearch.setText("");
        //text change listner
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {

                if (categoriesAdapter!=null){


                    categoriesAdapter.getFilter().filter(s.toString());
                    categoriesAdapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (categoriesAdapter!=null){

                    //filter(s.toString());
                    categoriesAdapter.getFilter().filter(s);
                    categoriesAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (categoriesAdapter!=null){

                    categoriesAdapter.getFilter().filter(s);
                    categoriesAdapter.notifyDataSetChanged();
                }
            }
        });



        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        genderspiner.setAdapter(aa);

        genderspiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    Toast.makeText(getApplicationContext(),gender[position] , Toast.LENGTH_LONG).show();


                    genderstr = gender[position];

                    if (gender[position].equals("Exp : high to low")){
                        bir = "DESC";
                        fetchastrologer();


                    }else if (gender[position].equals("Exp : low to high")){

                        bir = "ASC";
                        fetchastrologer();

                    }else if (gender[position].equals("Price : high to low")){
                        bir = "DESC";
                        fetchastrologer();
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.fillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.header.setVisibility(View.VISIBLE);
            }
        });

        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.viewWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RechargeWallet.class);
                startActivity(intent);
            }
        });

        binding.recharegewallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RechargeWallet.class);
                startActivity(intent);
            }
        });

    }


    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();

    }

    private void Astrologer() {
        binding.recyclerviewastro.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(SearchActivity.this,1,GridLayoutManager.VERTICAL,false);
        binding.recyclerviewastro.setLayoutManager(gridLayoutManager3);
        binding.recyclerviewastro.setItemAnimator(new DefaultItemAnimator());
        fetchastrologer();
    }

    private void fetchastrologer() {

        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("perpage", "44");
            json1.put("pageno","1");
            json1.put("filter_field", "working_ex");
            json1.put("filter_type",bir);
            //  json1.put("user_id",sharedpreferences.getString("id",""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.ASTROLIST;
        Log.i("gdfgvgbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Log.d("ghg", response.toString());


                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                            if (jsonObject.getString("status").equals("1")){
                                JSONArray jsonArray = jsonObject.getJSONArray("records");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    ImageData id = new ImageData();
                                    id.setId(obj.getString("id"));
                                    id.setImage(obj.getString("image"));
                                    id.setOwner_name(obj.getString("owner_name"));
                                    id.setLanguage(obj.getString("language"));
                                    id.setAvg_rating(obj.getString("avg_rating"));
                                    id.setWorking_ex(obj.getString("experience"));
                                    id.setCall_price_m(obj.getString("chat_price_m"));
                                    id.setcurrent_status(obj.getString("current_status"));
                                    id.setwait_time(obj.getString("wait_time"));
                                    id.setchat_commission(obj.getString("chat_commission"));
                                    imageData2.add(id);
                                }
                                categoriesAdapter=new AstrologerAdapter(getApplicationContext(),imageData2);
                                binding.recyclerviewastro.setAdapter(categoriesAdapter);
                                categoriesAdapter.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "There is seem to be network issue, check internet connection and try again", Toast.LENGTH_LONG).show();
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

    private void init() {
        compositeDisposable = new CompositeDisposable();
        try {
            screenType = getIntent().getExtras().getBoolean("type");
        } catch (Exception e) {
            e.printStackTrace();
        }

        apiInterface = getApiInterface();
        binding.fabFilter.setOnClickListener(v -> {
           /* // TODO: 18-06-2020 Open bottomsheet dialog to apply filter on search astrology function
            FilterBottomSheetDialogFragment bottomSheetFragment = new FilterBottomSheetDialogFragment();
            bottomSheetFragment.setFilterModel(filterModel);
            bottomSheetFragment.setDismissListener(o -> {
                if (o instanceof FilterModel) {
                    filterModel = (FilterModel) o;
                    getFilteredExperts();
                }
            });
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());*/


        });
        binding.imgSearch.setOnClickListener(v -> {
            UserUtils.gone(binding.txtTitle);
            UserUtils.gone(binding.imgSearch);
            UserUtils.visible(binding.edtSearch);
            UserUtils.visible(binding.imgClose);
            binding.edtSearch.setText("");
            binding.edtSearch.requestFocus();
        });
        binding.imgBack.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.imgClose.setOnClickListener(v -> {
            if (screenType) {
                binding.edtSearch.setText("");
            } else {
                UserUtils.gone(binding.edtSearch);
                UserUtils.gone(binding.imgClose);
                UserUtils.visible(binding.imgSearch);
                UserUtils.visible(binding.txtTitle);
                KeyboardUtils.hideSoftInput(binding.edtSearch);
            }
        });
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    getFilteredExperts();
                }
            }
        });
        filterModel = new FilterModel(1, 1, 5000);
        if (screenType) {
            UserUtils.gone(binding.txtTitle);
            UserUtils.gone(binding.imgSearch);
            binding.edtSearch.requestFocus();
            KeyboardUtils.showSoftInput(binding.edtSearch);
            showEmptyHolder();
        } else {
            binding.txtTitle.setText(R.string.lbl_astrologer);
            UserUtils.gone(binding.imgClose);
            UserUtils.gone(binding.edtSearch);
            Single<ExpertsResponseModel> expertsResponseModelSingle = apiInterface.getExperts();
            expertsResponseModelSingle.subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(Object o) {
                            if (o instanceof ExpertsResponseModel) {
                                ExpertsResponseModel expertsResponseModel = (ExpertsResponseModel) o;
                                if ( expertsResponseModel.getData() != null
                                        && expertsResponseModel.getData().get(0) != null
                                        && expertsResponseModel.getData().get(0).getRelatedVendor() != null) {
                                    initSearchedExpertList(expertsResponseModel.getData().get(0).getRelatedVendor());
                                } else {
                                    initSearchedExpertList(null);
                                }
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                        }
                    });
        }
    }

    private void getFilteredExperts() {
        UserUtils.visible(binding.loaderSearchExperts);
        Call<ExpertsResponseModel> call = apiInterface.getExpertFilter("4", "5000");
        call.enqueue(new Callback<ExpertsResponseModel>() {
            @Override
            public void onResponse(Call<ExpertsResponseModel> call, Response<ExpertsResponseModel> response) {

                ExpertsResponseModel expertsResponseModel = null;
                if (response.isSuccessful()) {
                    expertsResponseModel = response.body();
                }
                try {
                    if (expertsResponseModel != null
                            && expertsResponseModel.getData() != null
                            && expertsResponseModel.getData().get(0) != null
                            && expertsResponseModel.getData().get(0).getRelatedVendor() != null) {
                        initSearchedExpertList(expertsResponseModel.getData().get(0).getRelatedVendor());
                    } else {
                        initSearchedExpertList(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    initSearchedExpertList(null);
                }
            }

            @Override
            public void onFailure(Call<ExpertsResponseModel> call, Throwable t) {
                call.cancel();
                initSearchedExpertList(null);
            }
        });
    }

    private void initSearchedExpertList(List<ExpertsResponseModel.RelatedVendor> relatedVendorList) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.rvExpertList.setLayoutManager(manager);
        binding.rvExpertList.setNestedScrollingEnabled(false);
        baseRecyclerAdapter = new BaseProgressRecyclerListAdapter(relatedVendorList) {

            @Override
            public int getLayout(int viewType) {
                return R.layout.search_experts_item;
            }

            @Override
            public int getViewType(int position) {
                return position;
            }

            @Override
            public void onBind(BaseProgressRecyclerListAdapter.ViewHolder holder) {
                SearchExpertsItemBinding binding = (SearchExpertsItemBinding) holder.getBinder();
                binding.setRelatedVendor((ExpertsResponseModel.RelatedVendor) getItem(holder.getAdapterPosition()));
                binding.item.setOnClickListener(v -> {
                    Intent intent = new Intent(SearchActivity.this, AgentProfileActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("expert", (ExpertsResponseModel.RelatedVendor) getItem(holder.getAdapterPosition()));
                    intent.putExtra("data", bundle);
                    startActivity(intent);
                });
            }
        };
        binding.rvExpertList.setAdapter(baseRecyclerAdapter);
        baseRecyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                //Scrolling to starting position
                try {
                    if (binding.rvExpertList.getLayoutManager() != null) {
                        binding.rvExpertList.getLayoutManager().scrollToPosition(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        showEmptyHolder();
    }

    private void showEmptyHolder() {
        UserUtils.gone(binding.loaderSearchExperts);
        if (baseRecyclerAdapter != null && baseRecyclerAdapter.getItemCount() > 0) {
            UserUtils.gone(binding.txtNoExpertData);
        } else {
            UserUtils.visible(binding.txtNoExpertData);
        }
    }

    private void walletbalnce() {


        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        Integer id = Integer.valueOf(sharedpreferences.getString("id", ""));

        final JSONObject json1 = new JSONObject();
        try {
            json1.put("user_id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.WALLETBALANCE;
        Log.i("gdfgvgbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Log.d("ghg", response.toString());

                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                            if (jsonObject.getString("status").equals("1")){
                                // JSONArray jsonArray = jsonObject.getJSONArray("records");
                                JSONObject jsonObject1 = jsonObject.getJSONObject("records");
                                txtBalance.setText(jsonObject1.getString("wallet"));
                                Log.i("dfsdsd",jsonObject1.getString("wallet"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "There is seem to be network issue, check internet connection and try again", Toast.LENGTH_LONG).show();
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

}
