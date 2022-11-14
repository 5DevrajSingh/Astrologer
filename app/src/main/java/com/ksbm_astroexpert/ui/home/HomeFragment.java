package com.ksbm_astroexpert.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ksbm_astroexpert.Adapter.AstrologerAdapterd;
import com.ksbm_astroexpert.Adapter.RemidesAdapter;
import com.ksbm_astroexpert.Adapter.SlidingImage_Adapter2;
import com.ksbm_astroexpert.Adapter.TestimonialAdapter;
import com.ksbm_astroexpert.Astrologer.ImageDatad;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.databinding.HomeFragmentBinding;
import com.ksbm_astroexpert.databinding.HomeMagicalRemediesItemBinding;
import com.ksbm_astroexpert.databinding.HomeOurExpertsItemBinding;
import com.ksbm_astroexpert.network.APIClient;
import com.ksbm_astroexpert.network.APIInterface;
import com.ksbm_astroexpert.procode.helper.JSONParserVolley;
import com.ksbm_astroexpert.ui.Astrodetails.ModelAstrologerList;
import com.ksbm_astroexpert.ui.Wallet.Offer;
import com.ksbm_astroexpert.ui.agentprofile.AgentProfileActivity;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.base.BaseFragment;
import com.ksbm_astroexpert.ui.base.BaseProgressRecyclerListAdapter;
import com.ksbm_astroexpert.ui.otpverification.OTPVerificationActivity;
import com.ksbm_astroexpert.ui.search.SearchActivity;
import com.ksbm_astroexpert.ui.signin.SignInActivity;
import com.ksbm_astroexpert.ui.signin.SignInResponseModel;
import com.ksbm_astroexpert.ui.utils.KeyboardUtils;
import com.ksbm_astroexpert.ui.utils.UserConstants;
import com.ksbm_astroexpert.ui.utils.UserUtils;
import com.ksbm_astroexpert.view.ImageData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.ksbm_astroexpert.ui.home.HomeActivity.ChanFrag;
import static com.ksbm_astroexpert.ui.home.HomeActivity.loaderBannerAll;
import static com.ksbm_astroexpert.ui.home.HomeActivity.loaderBannerAll1;
import static com.ksbm_astroexpert.ui.home.HomeActivity.loaderBannerAll2;

public class HomeFragment extends BaseFragment {


    private List<Offer> imageData2=new ArrayList<>();
    private List<ImageData> imageData4=new ArrayList<>();

    private HomeFragmentBinding binding;
    private APIInterface apiInterface;
    private CompositeDisposable compositeDisposable;

    AlertDialog.Builder builder;


    String currentVersion;

    private List<ImageDatad> imageData3=new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.home_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = (HomeFragmentBinding) getBinding();

        builder = new AlertDialog.Builder(getActivity());

        ChanFrag="Home";


        try {
            currentVersion = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        new GetVersionCode().execute();


        Animation aniRotate = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_clockwise);
        loaderBannerAll.startAnimation(aniRotate);
        loaderBannerAll1.startAnimation(aniRotate);
        loaderBannerAll2.startAnimation(aniRotate);

//        Glide.with(this).load(R.drawable.zodiac).into(loaderBannerAll);
//        Glide.with(this).load(R.drawable.zodiac).into(loaderBannerAll1);
//        Glide.with(this).load(R.drawable.zodiac).into(loaderBannerAll2);
        Astrologerbiexp();
        init();

        binding.viewPagerBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentViewAll = new Intent(mContext, ViewAllAstrologer.class);
                intentViewAll.putExtra("type", false);
                startActivity(intentViewAll);
            }
        });

        binding.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                        loaderBannerAll.setVisibility(View.VISIBLE);
        loaderBannerAll1.setVisibility(View.VISIBLE);
        loaderBannerAll2.setVisibility(View.VISIBLE);
                Astrologer();
                Remidesnew();
                getAstrologerList();
                binding.pullToRefresh.setRefreshing(false);
            }
        });

                loaderBannerAll.setVisibility(View.VISIBLE);
        loaderBannerAll1.setVisibility(View.VISIBLE);
        loaderBannerAll2.setVisibility(View.VISIBLE);
        Astrologer();
        Remidesnew();
        getAstrologerList();

    }

    class GetVersionCode extends AsyncTask<Void, String, String> {

        @Override

        protected String doInBackground(Void... voids) {

            String newVersion = null;

            try {
                Document document = Jsoup.connect("https://play.google.com/store/apps/details?id=" + getActivity().getPackageName()  + "&hl=en")
                        .timeout(90000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get();
                if (document != null) {
                    Elements element = document.getElementsContainingOwnText("Current Version");
                    for (Element ele : element) {
                        if (ele.siblingElements() != null) {
                            Elements sibElemets = ele.siblingElements();
                            for (Element sibElemet : sibElemets) {
                                newVersion = sibElemet.text();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newVersion;

        }


        @Override

        protected void onPostExecute(String onlineVersion) {

            super.onPostExecute(onlineVersion);

          //  Toast.makeText(getActivity(), "Playstore app version: "+onlineVersion+", "+"Current app version: "+currentVersion , Toast.LENGTH_SHORT).show();

            if (onlineVersion != null && !onlineVersion.isEmpty()) {

                if (!currentVersion.equals(onlineVersion)) {

//                if (Float.valueOf(currentVersion) < Float.valueOf(onlineVersion)) {
                    //show anything
                    builder.setMessage("Updated app available on playstore, Please update app.")
                    .setCancelable(true)
                    .setPositiveButton("Click here to update", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle(R.string.app_name);
        alert.show();

                }
                else
                {
                  //  Toast.makeText(getActivity(), "You are using updated app.", Toast.LENGTH_SHORT).show();
                }



            }
            else {
                Toast.makeText(getActivity(), "App not available on play store.", Toast.LENGTH_SHORT).show();

//                builder.setMessage("App not available on play store.")
//                        .setCancelable(false)
//                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
////        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
////                        public void onClick(DialogInterface dialog, int id) {
////                            //  Action for 'NO' Button
////
////                        }
////                    });
//                //Creating dialog box
//                AlertDialog alert = builder.create();
//                //Setting the title manually
//                alert.setTitle(R.string.app_name);
//                alert.show();

            }

            Log.d("update", "Current version " + currentVersion + "playstore version " + onlineVersion);

        }
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onStart() {
        super.onStart();
                loaderBannerAll.setVisibility(View.VISIBLE);
        loaderBannerAll1.setVisibility(View.VISIBLE);
        loaderBannerAll2.setVisibility(View.VISIBLE);
        Astrologer();
        Remidesnew();
        getAstrologerList();
    }

    private void Remidesnew() {
        binding.recyclerviewastro.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
        binding.recyclerviewastro.setLayoutManager(gridLayoutManager3);
        binding.recyclerviewastro.setItemAnimator(new DefaultItemAnimator());
        fechremides();
    }

    private void fechremides() {
        final String url = RootURL.REMIDES;
        Log.i("gdfdddgvgbnn", url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                             Log.d("dfdffghg", response.toString());

                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                            if (jsonObject.getString("success").equals("200")){
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    ImageData id = new ImageData();
                                    id.setId(obj.getString("remedies_id"));
                                    id.setImage(obj.getString("icon"));
                                    id.setOwner_name(obj.getString("title"));
                                    id.setLanguage(obj.getString("icon2"));
                                    id.setAvg_rating(obj.getString("content"));
                                    imageData4.add(id);
                                }

                                RemidesAdapter categoriesAdapter=new RemidesAdapter(getActivity(),imageData4);
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
                Toast.makeText(getActivity(), "There is seem to be network issue, Unable to get REMIDIES from server.", Toast.LENGTH_LONG).show();
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
        Volley.newRequestQueue(getActivity()).add(jsonObjectRequest);

    }

    private void Astrologer() {
        binding.testimonials.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
        binding.testimonials.setLayoutManager(gridLayoutManager3);
        binding.testimonials.setItemAnimator(new DefaultItemAnimator());
        testimonials();
    }

    private void testimonials() {
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("perpage", "10");
            json1.put("pageno","1");
            json1.put("filter_field", "working_ex");
            //  json1.put("user_id",sharedpreferences.getString("id",""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.TESTIMONIALS;
        Log.i("gdfgvgbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Log.d("ghg", response.toString());

                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                            //    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if (jsonObject.getString("success").equals("200")){

                            /*    comments.setText(jsonObject.getString("comment")+"k mins");
                                reports.setText(jsonObject.getString("report")+" reports");
                                calls.setText(jsonObject.getString("call")+"k mins");*/

                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    Offer id = new Offer();
                                    id.setname(obj.getString("name"));
                                    id.setscheduled(obj.getString("email"));
                                    id.setBannerSrc(obj.getString("description"));
                                    imageData2.add(id);
                                }
                                TestimonialAdapter donationAdapter=new TestimonialAdapter(getActivity(),imageData2);
                                donationAdapter.notifyDataSetChanged();
                                binding.testimonials.setAdapter(donationAdapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "There is seem to be network issue, Server not responding.", Toast.LENGTH_LONG).show();
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
        Volley.newRequestQueue(getActivity()).add(jsonObjectRequest);

    }




    private void Astrologerbiexp() {
        binding.expertise.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
        binding.expertise.setLayoutManager(gridLayoutManager3);
        binding.expertise.setItemAnimator(new DefaultItemAnimator());
        fetchastrologerexp();
    }

    private void fetchastrologerexp() {
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("perpage", "10");
            json1.put("pageno","1");
            //  json1.put("user_id",sharedpreferences.getString("id",""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.Base_URL+"/Astroksbmadmin/user/expertise.php?";
        Log.i("gdfgvgbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                             Log.d("gdsdsdhg", response.toString());


                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                          //  if (jsonObject.getString("success").equals(200)){
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    ImageDatad id = new ImageDatad();
                                    id.setId(obj.getString("id"));
                                    id.setOwner_name(obj.getString("name"));
                                    Log.i("sdfsdfsdfsdf",obj.getString("name"));

                                    imageData3.add(id);

                                }
                                AstrologerAdapterd donationAdapter=new AstrologerAdapterd(getActivity(),imageData3);
                                donationAdapter.notifyDataSetChanged();
                                binding.expertise.setAdapter(donationAdapter);

                            ///}
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "There is seem to be network issue, Server issue.", Toast.LENGTH_LONG).show();
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
        Volley.newRequestQueue(getActivity()).add(jsonObjectRequest);
    }

    private void init() {
        apiInterface = ((BaseActivity) mContext).getApiInterface();
        compositeDisposable = new CompositeDisposable();
//        getBanners();
//        getRemedies();
//        getOurExperts();
        binding.txtViewAllExperts.setOnClickListener(v -> {
            Intent intentViewAll = new Intent(mContext, ViewAllAstrologer.class);
            intentViewAll.putExtra("type", false);
            startActivity(intentViewAll);
        });
        binding.txtViewAllRemedies.setOnClickListener(v -> {

        });


        Single.zip(apiInterface.getBanners().subscribeOn(Schedulers.io()), apiInterface.getRemedies().subscribeOn(Schedulers.io()), apiInterface.getExperts().subscribeOn(Schedulers.io()), (brm, rms, experts) -> {
            List<Object> objects = new ArrayList<>();
            objects.add(brm);
            objects.add(rms);
            objects.add(experts);
            return objects;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<Object>() {
            @Override
            public void onSuccess(Object o) {
                if (o instanceof List) {
                    for (Object object : (List) o) {
                        if (object instanceof BannerResponseModel) {
                            getBanners((BannerResponseModel) object);
                        } else if (object instanceof RemediesResponseModel) {
                            getRemedies((RemediesResponseModel) object);
                        } else if (object instanceof ExpertsResponseModel) {
                            getOurExperts((ExpertsResponseModel) object);
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        });

        //astroDetails();

    }

    private void getBanners(BannerResponseModel bannerResponseModel) {
//        UserUtils.visible(binding.loaderBanner);
//        Call<BannerResponseModel> call = apiInterface.getBanners();
//        call.enqueue(new Callback<BannerResponseModel>() {
//            @Override
//            public void onResponse(Call<BannerResponseModel> call, Response<BannerResponseModel> response) {
        UserUtils.gone(binding.loaderBanner);
        loaderBannerAll.setVisibility(View.GONE);
//                BannerResponseModel bannerResponseModel = null;
//                if (response.isSuccessful()) {
//                    bannerResponseModel = response.body();
//                }
        if (bannerResponseModel != null && bannerResponseModel.getData() != null && !bannerResponseModel.getData().isEmpty()) {
            //PagerAdapter adapter = new ScreenSlidePagerAdapter(getChildFragmentManager(), bannerResponseModel.getData());

            SlidingImage_Adapter2 adapter1 = new SlidingImage_Adapter2(bannerResponseModel.getData(), getActivity());
            binding.viewPagerBanner.setAdapter(adapter1);
            binding.tabLayout.setupWithViewPager(binding.viewPagerBanner, true);
        } else {
            UserUtils.gone(binding.flBanner);
        }
//            }
//
//            @Override
//            public void onFailure(Call<BannerResponseModel> call, Throwable t) {
//                call.cancel();
//                UserUtils.gone(binding.loaderBanner);
//                UserUtils.gone(binding.flBanner);
//            }
//        });
    }

    private void init(List<RemediesResponseModel.RemediesModel> items) {
        if (items == null || items.isEmpty()) {
            UserUtils.gone(binding.llRemedies);
            return;
        }
        UserUtils.gone(binding.loaderRemedies);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        binding.rvMagicalRemedies.setLayoutManager(manager);
        binding.rvMagicalRemedies.setNestedScrollingEnabled(false);
        remediesBaseProgressRecyclerListAdapter = new BaseProgressRecyclerListAdapter(items) {

            @Override
            public int getLayout(int viewType) {
                return R.layout.home_magical_remedies_item;
            }

            @Override
            public int getViewType(int position) {
                return position;
            }

            @Override
            public void onBind(ViewHolder holder) {
                HomeMagicalRemediesItemBinding binding = (HomeMagicalRemediesItemBinding) holder.getBinder();
                // binding.setRemediesModel((RemediesResponseModel.RemediesModel) remediesBaseProgressRecyclerListAdapter.getItem(holder.getAdapterPosition()));
                binding.setRemediesModel((RemediesResponseModel.RemediesModel) getItem(holder.getAdapterPosition()));
                binding.executePendingBindings();

                binding.item.setOnClickListener(v -> {

                    Intent blogDetailIntent = new Intent(mContext, SearchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("blog", binding.getRemediesModel());
                    blogDetailIntent.putExtra("data", bundle);
                    startActivity(blogDetailIntent);
                });
            }
        };
        binding.rvMagicalRemedies.setAdapter(remediesBaseProgressRecyclerListAdapter);
        remediesBaseProgressRecyclerListAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                //Scrolling to starting position
                try {
                    if (binding.rvMagicalRemedies.getLayoutManager() != null) {
                        binding.rvMagicalRemedies.getLayoutManager().scrollToPosition(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

//        builder.setMessage("Are you sure want to exit.")
//                .setCancelable(false)
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        dialog.cancel();
//                        System.exit(0);
//
////                            if(signInActivityBinding.tnccheck.isChecked())
////                            {
////                                signInActivityBinding.tnccheck.setSelected(false);
////                                signInActivityBinding.tnccheck.setChecked(false);
////                            }
////                            else
////                            {
////                                signInActivityBinding.tnccheck.setSelected(true);
////                                signInActivityBinding.tnccheck.setChecked(true);
////                            }
//
//                    }
//                });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            //  Action for 'NO' Button
//                            dialog.cancel();
//                            Intent intent = new Intent(getActivity(),HomeActivity.class);
//                            startActivity(intent);
//                        }
//                    });
//        //Creating dialog box
//        AlertDialog alert = builder.create();
//        //Setting the title manually
//        alert.setTitle(R.string.app_name);
//        alert.show();


    }

    private void getRemedies(RemediesResponseModel remediesResponseModel) {
//        UserUtils.visible(binding.loaderBanner);
//        Call<RemediesResponseModel> call = apiInterface.getRemedies();
//        call.enqueue(new Callback<RemediesResponseModel>() {
//            @Override
//            public void onResponse(Call<RemediesResponseModel> call, Response<RemediesResponseModel> response) {
//                UserUtils.gone(binding.loaderBanner);
//                RemediesResponseModel remediesResponseModel = null;
//                if (response.isSuccessful()) {
//                    remediesResponseModel = response.body();
//                }
        if (remediesResponseModel != null && remediesResponseModel.hasList()) {
            initRemediesList(remediesResponseModel.getData());
        } else {
            initRemediesList(null);
        }
//            }
//
//            @Override
//            public void onFailure(Call<RemediesResponseModel> call, Throwable t) {
//                call.cancel();
//                initRemediesList(null);
//            }
//        });
    }


    private BaseProgressRecyclerListAdapter remediesBaseProgressRecyclerListAdapter;

    private void initRemediesList(List<RemediesResponseModel.RemediesModel> items) {
        if (items == null || items.isEmpty()) {
            UserUtils.gone(binding.llRemedies);
            return;
        }
        UserUtils.gone(binding.loaderRemedies);
        loaderBannerAll1.setVisibility(View.GONE);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        binding.rvMagicalRemedies.setLayoutManager(manager);
        binding.rvMagicalRemedies.setNestedScrollingEnabled(false);
        remediesBaseProgressRecyclerListAdapter = new BaseProgressRecyclerListAdapter(items) {

            @Override
            public int getLayout(int viewType) {
                return R.layout.home_magical_remedies_item;
            }

            @Override
            public int getViewType(int position) {
                return position;
            }

            @Override
            public void onBind(ViewHolder holder) {
                HomeMagicalRemediesItemBinding binding = (HomeMagicalRemediesItemBinding) holder.getBinder();
               // binding.setRemediesModel((RemediesResponseModel.RemediesModel) remediesBaseProgressRecyclerListAdapter.getItem(holder.getAdapterPosition()));
                binding.setRemediesModel((RemediesResponseModel.RemediesModel) getItem(holder.getAdapterPosition()));
                binding.executePendingBindings();

                binding.item.setOnClickListener(v -> {

                    Intent blogDetailIntent = new Intent(mContext, SearchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("blog", binding.getRemediesModel());
                    blogDetailIntent.putExtra("data", bundle);
                    startActivity(blogDetailIntent);
                });
            }
        };
        binding.rvMagicalRemedies.setAdapter(remediesBaseProgressRecyclerListAdapter);
        remediesBaseProgressRecyclerListAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                //Scrolling to starting position
                try {
                    if (binding.rvMagicalRemedies.getLayoutManager() != null) {
                        binding.rvMagicalRemedies.getLayoutManager().scrollToPosition(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getOurExperts(ExpertsResponseModel expertsResponseModel) {
//        UserUtils.visible(binding.loaderOurExperts);
//        Call<ExpertsResponseModel> call = apiInterface.getExperts();
//        call.enqueue(new Callback<ExpertsResponseModel>() {
//            @Override
//            public void onResponse(Call<ExpertsResponseModel> call, Response<ExpertsResponseModel> response) {
//
//                ExpertsResponseModel expertsResponseModel = null;
//                if (response.isSuccessful()) {
//                    expertsResponseModel = response.body();
//                }
        if (expertsResponseModel != null
                /*&& expertsResponseModel.getData() != null
                && expertsResponseModel.getData().get(0) != null
                && expertsResponseModel.getData().get(0).getRelatedVendor() != null*/) {
          //  initOurExpertList(expertsResponseModel.getData().get(0).getRelatedVendor());
        } else {
            initOurExpertList(null);
        }
//            }
//
//            @Override
//            public void onFailure(Call<ExpertsResponseModel> call, Throwable t) {
//                call.cancel();
//                initOurExpertList(null);
//            }
//        });
    }

    private BaseProgressRecyclerListAdapter ourExpertBaseProgressRecyclerListAdapter;

    private void initOurExpertList(List<ExpertsResponseModel.RelatedVendor> relatedVendorList) {
        UserUtils.gone(binding.loaderOurExperts);
        loaderBannerAll2.setVisibility(View.GONE);
        if (relatedVendorList == null) {
            UserUtils.visible(binding.txtNoExpertData);
            return;
        }
        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        binding.rvExpertList.setLayoutManager(manager);
        binding.rvExpertList.setNestedScrollingEnabled(false);
        ourExpertBaseProgressRecyclerListAdapter = new BaseProgressRecyclerListAdapter(relatedVendorList) {

            @Override
            public int getLayout(int viewType) {
                return R.layout.home_our_experts_item;
            }

            @Override
            public int getViewType(int position) {
                return position;
            }

            @Override
            public void onBind(ViewHolder holder) {
                HomeOurExpertsItemBinding binding = (HomeOurExpertsItemBinding) holder.getBinder();
                ExpertsResponseModel.RelatedVendor item = (ExpertsResponseModel.RelatedVendor) getItem(holder.getAdapterPosition());
                binding.setRelatedVendor(item);
                binding.executePendingBindings();
                binding.item.setOnClickListener(v -> {
                    Intent intent = new Intent(mContext, AgentProfileActivity.class);
                    intent.putExtra("data",UserUtils.getString(binding.getRelatedVendor()));

                    startActivity(intent);
                });
            }
        };
        binding.rvExpertList.setAdapter(ourExpertBaseProgressRecyclerListAdapter);
        ourExpertBaseProgressRecyclerListAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
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
    }
    // astro Details
    List<AstroModel> mList = new ArrayList<>();
    private void astroDetails()
    {
       // mList = new ArrayList<>();
        String Api = RootURL.Base_URL+"/Astroksbmadmin/api/api/astrolist";
        final JSONObject parseJson = new JSONObject();
        try {
            parseJson.put("perpage","50");
            parseJson.put("pageno","1");
            parseJson.put("filter_field","working_ex");
            parseJson.put("filter_type","DESC");



        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("fsdfdfsf",Api+parseJson);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, Api, parseJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("fsfsdfsdfs",response.toString());

                        JSONParserVolley jVolley = new JSONParserVolley(response.toString());
                        JSONObject json = jVolley.JSONParseVolley();
                        try
                        {


                            if(json.has("status")&&json.getString("status").equals("1"))
                            {
                                JSONArray jArray = json.getJSONArray("records");
                                Log.e("DtatCall",":2Game:"+jArray.toString());

                                for (int kk=0;kk<jArray.length();kk++)
                                {
                                    JSONObject jsonData = jArray.getJSONObject(kk);
                                    mList.add(new AstroModel(
                                            jsonData.getString("id"),
                                            jsonData.getString("owner_name"),
                                            jsonData.getString("image"),
                                            jsonData.getString("shop_name"),
                                            jsonData.getString("language"),
                                            jsonData.getString("experience"),
                                            jsonData.getString("phone"),
                                            jsonData.getString("avg_rating"),
                                            jsonData.getString("call_price_m"),
                                            jsonData.getString("chat_price_m"),
                                            jsonData.getString("current_status"),
                                            jsonData.getString("current_status_call"),
                                            jsonData.getString("wait_time"),
                                            jsonData.getString("chat_commission"),
                                            jsonData.getString("call_commission"),
                                            jsonData.getString("device_token")
                                    ));
                                    Log.e("DttattaS","::"+jsonData.getString("wait_time"));
                                }

                              /*  GridLayoutManager manager = new GridLayoutManager(mContext, 3);
                                binding.rvAstroList.setLayoutManager(manager);
                                binding.rvAstroList.setNestedScrollingEnabled(false);
                                binding.rvAstroList.setAdapter(new AstroAdapter(getActivity(),mList,binding.rvAstroList));
                                binding.loaderOurExperts.setVisibility(View.GONE);*/
                            }


                        } catch (JSONException e)
                        {
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy( 200*30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getActivity()).add(jsonObjectRequest);

    }

    private void initOurAstroList(List<AstroModel> relatedVendorList) {
        UserUtils.gone(binding.loaderOurExperts);
        if (relatedVendorList == null) {
            UserUtils.visible(binding.txtNoExpertData);
            return;
        }
        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        binding.rvExpertList.setLayoutManager(manager);
        binding.rvExpertList.setNestedScrollingEnabled(false);
        ourExpertBaseProgressRecyclerListAdapter = new BaseProgressRecyclerListAdapter(relatedVendorList) {

            @Override
            public int getLayout(int viewType) {
                return R.layout.astro_menu;
            }

            @Override
            public int getViewType(int position) {
                return position;
            }

            @Override
            public void onBind(ViewHolder holder) {
                HomeOurExpertsItemBinding binding = (HomeOurExpertsItemBinding) holder.getBinder();
                ExpertsResponseModel.RelatedVendor item = (ExpertsResponseModel.RelatedVendor) getItem(holder.getAdapterPosition());
                binding.setRelatedVendor(item);
                binding.executePendingBindings();
                binding.item.setOnClickListener(v -> {

                });
            }
        };
        binding.rvExpertList.setAdapter(ourExpertBaseProgressRecyclerListAdapter);
        ourExpertBaseProgressRecyclerListAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
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
    }





    private void getAstrologerList() {

        //Log.d("sqqss", "doLogin: "+"login dara start here");
        compositeDisposable = new CompositeDisposable();
        APIInterface apiInterface = getApiInterface();
        Single<ModelAstrologerList> signInResponseModelObservable = apiInterface.astrodetails_list();
        signInResponseModelObservable.subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Object o) {
                        ModelAstrologerList signInResponseModel = (ModelAstrologerList) o;
                        Log.d("dfsdfsdfsdf","="+new Gson().toJson(signInResponseModel));
                        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
                        binding.rvAstroList.setLayoutManager(manager);
                        binding.rvAstroList.setNestedScrollingEnabled(false);
                        binding.rvAstroList.setAdapter(new AstroAdapter(getActivity(),signInResponseModel.getRecords(),binding.rvAstroList));
                        binding.loaderOurExperts.setVisibility(View.GONE);
                        loaderBannerAll.setVisibility(View.GONE);
                        loaderBannerAll1.setVisibility(View.GONE);
                        loaderBannerAll2.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {


                    }
                });

    }

    public APIInterface getApiInterface() {
        return APIClient.getClient(UserConstants.BASE_URL2).create(APIInterface.class);
    }

}
