package com.ksbm_astroexpert.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.ksbm_astroexpert.App;
import com.ksbm_astroexpert.Astrologer.CallRequest.Call_request;
import com.ksbm_astroexpert.Chat.MainActivityChat;
import com.ksbm_astroexpert.Chat.RegisterUser;
import com.ksbm_astroexpert.Chat.activity.OrderHistory_Activity;
import com.ksbm_astroexpert.Chat.activity.ProfileActivity;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.InternetCheck.ConnectivityReceiver;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.Support.Supportchat;

import com.ksbm_astroexpert.databinding.HomeActivityBinding;
import com.ksbm_astroexpert.ui.Setting.Settings;
import com.ksbm_astroexpert.ui.Wallet.RechargeWallet;
import com.ksbm_astroexpert.ui.account.AccountFragment;
import com.ksbm_astroexpert.ui.base.BaseActivity;
import com.ksbm_astroexpert.ui.blog.BlogFragment;
import com.ksbm_astroexpert.ui.contactus.ContactUsActivity;
import com.ksbm_astroexpert.ui.faqs.FAQsActivity;
import com.ksbm_astroexpert.ui.howorks.HowItWorksActivity;
import com.ksbm_astroexpert.notification.NotificationActivity;
import com.ksbm_astroexpert.ui.privacy.PrivacyPolicyActivity;
import com.ksbm_astroexpert.ui.search.SearchActivity;
import com.ksbm_astroexpert.ui.signin.SignInActivity;
import com.ksbm_astroexpert.ui.utils.UserUtils;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class HomeActivity extends BaseActivity{

    private HomeActivityBinding binding;
    private Drawer drawer;
    private FragmentManager fragmentManager;

    TextView txtBalance;
    ImageView imgSupport;
    RequestQueue requestQueue;
    LinearLayout pending_request_layout;
    TextView accepthome,cancelhome;

    public static String ChanFrag="";

    String WhatsAppNo= "+919991900369";
    AlertDialog.Builder builder;

    public static ImageView loaderBannerAll,loaderBannerAll1,loaderBannerAll2;

    private BroadcastReceiver mNetworkReceiver;

    @Override
    protected int getLayout() {
        return R.layout.home_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (HomeActivityBinding) getBinding();

        builder = new AlertDialog.Builder(this);
        init();

        loaderBannerAll = findViewById(R.id.loaderBannerAll);
        loaderBannerAll1 = findViewById(R.id.loaderBannerAll1);
        loaderBannerAll2 = findViewById(R.id.loaderBannerAll2);


        ///////////////added by shivam//////////
        mNetworkReceiver = new ConnectivityReceiver();
        registerNetworkBroadcastForNougat();

        txtBalance = findViewById(R.id.txtBalance);
        imgSupport = findViewById(R.id.imgSupport);
        pending_request_layout  = findViewById(R.id.pending_request_layout);
        accepthome  = findViewById(R.id.accepthome);
        cancelhome  = findViewById(R.id.cancelhome);
        requestQueue=Volley.newRequestQueue(this);

        GetProfile();
        getToken();
        txtBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), RechargeWallet.class);
                startActivity(intent);
            }
        });

        imgSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                geotowhapp();
//                Intent intent = new Intent(getApplicationContext(), Supportchat.class);
//                startActivity(intent);
            }
        });




        SharedPreferences sharedPreferences = getSharedPreferences("GetRequest", Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("getrequest",false))
        {
            pending_request_layout.setVisibility(View.VISIBLE);
        }
        else
        {
            pending_request_layout.setVisibility(View.GONE);
        }

        accepthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pending_request_layout.setVisibility(View.GONE);
                SharedPreferences sharedPreferences = getSharedPreferences("GetRequest", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("getrequest",false);
                editor.commit();
                editor.apply();

                Intent intent = new Intent(getBaseContext(), Call_request.class);
                startActivity(intent);
            }
        });
        cancelhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pending_request_layout.setVisibility(View.GONE);

                SharedPreferences sharedPreferences = getSharedPreferences("GetRequest", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("getrequest",false);
                editor.commit();
                editor.apply();
            }
        });

    }


    public static void dialog(boolean value,Context context){

        if(value){
            ///////Do Nothing
        }else {
        }
    }



    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }




    /**
     * Callback will be triggered when there is change in
     * network connection
     */

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPreferences = getSharedPreferences("GetRequest", Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("getrequest",false))
        {
            pending_request_layout.setVisibility(View.VISIBLE);
        }
        else
        {
            pending_request_layout.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        SharedPreferences sharedPreferences = getSharedPreferences("GetRequest", Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("getrequest",false))
        {
            pending_request_layout.setVisibility(View.VISIBLE);
        }
        else
        {
            pending_request_layout.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Check Killing: ","Kill");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Check Kill: ","Kill");
//        Toast.makeText(this, "Kiling App", Toast.LENGTH_SHORT).show();
    }

    private void geotowhapp() {

        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
        Uri uri;
        if (isWhatsappInstalled) {

//            Intent sendIntent = new Intent(Intent.ACTION_SENDTO,   uri = Uri.parse("smsto:" + "9560393886"));
//            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hai Good Morning");
//            sendIntent.setType("text/plain");
//            sendIntent.setPackage("com.whatsapp");
//            startActivity(sendIntent);
            PackageManager packageManager = this.getPackageManager();
            Intent i = new Intent(Intent.ACTION_VIEW);

            try {
                String url = "https://api.whatsapp.com/send?phone="+ WhatsAppNo +"&text=" + URLEncoder.encode("Hello,\n", "UTF-8");
                i.setPackage("com.whatsapp");
                i.setData(Uri.parse(url));
                if (i.resolveActivity(packageManager) != null) {
                    this.startActivity(i);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "WhatsApp not Installed",
                    Toast.LENGTH_SHORT).show();
            uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goToMarket);

        }
    }
    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }


    private void GetProfile() {
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        Integer id = Integer.valueOf(sharedpreferences.getString("id", ""));
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.GETPROFILEINFO;
        Log.i("gdfgvgbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ghg", response.toString());
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            if (jsonObject.getString("status").equals("1")) {
                                JSONObject jsonObject1 = jsonObject.optJSONObject("records");
                                SharedPreferences sharedPreferences = getSharedPreferences(RootURL.PREFACCOUNT, MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                try {

                                }
                                catch (NullPointerException e)
                                {
                                    editor.putString("user_profile_image",jsonObject1.getString("user_profile_image"));
                                    editor.putString("phone",jsonObject1.getString("phone"));
                                    editor.commit();
                                }


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
         //       Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

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
        fragmentManager = getSupportFragmentManager();
        initDrawer();
        binding.navigationView.setItemIconTintList(null);
        binding.navigationView.setOnNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            switch (id) {
                case R.id.navigation_home:
                    binding.actionbar.txtTitle.setText(R.string.app_name);
                    HomeFragment homeFragment = new HomeFragment();
                    loadFragment(homeFragment, HomeFragment.class.getSimpleName(), false);
                    break;
                case R.id.navigation_search:
//                    binding.actionbar.txtTitle.setText(R.string.lbl_search);
//                    SearchFragment searchFragment = new SearchFragment();
//                    loadFragment(searchFragment, SearchFragment.class.getSimpleName(), false);

                    String url = "https://www.youtube.com/channel/UCcPPh1YtlV8YYPf78B_uPeQ";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    break;
                case R.id.navigation_read:
                    binding.actionbar.txtTitle.setText(R.string.lbl_blogs);
                    BlogFragment blogFragment = new BlogFragment();
                    loadFragment(blogFragment, BlogFragment.class.getSimpleName(), true);
                    break;
                case R.id.navigation_account:
                    binding.actionbar.txtTitle.setText(R.string.lbl_account);
                    AccountFragment accountFragment = new AccountFragment();
                    loadFragment(accountFragment, AccountFragment.class.getSimpleName(), true);
                    break;
            }
            return true;
        });
        binding.navigationView.setSelectedItemId(R.id.navigation_home);
        UserUtils.visible(binding.actionbar.imgNotification);
        UserUtils.visible(binding.actionbar.imgSupport);
        UserUtils.visible(binding.actionbar.viewWallet);
        binding.actionbar.imgNotification.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
            startActivity(intent);
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences("GetRequest", Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("getrequest",false))
        {
            pending_request_layout.setVisibility(View.VISIBLE);
        }
        else
        {
            pending_request_layout.setVisibility(View.GONE);
        }

        selectLastSelectedTab();
        walletbalnce();
    }

    private void selectLastSelectedTab() {
        Fragment fragment = fragmentManager.findFragmentById(R.id.container);
        if (fragment instanceof BlogFragment) {
          binding.navigationView.setSelectedItemId(R.id.navigation_read);
        }else if (fragment instanceof HomeFragment) {
          binding.navigationView.setSelectedItemId(R.id.navigation_home);
        }else if (fragment instanceof AccountFragment) {
          binding.navigationView.setSelectedItemId(R.id.navigation_account);
        }
    }

    private void initDrawer() {

        actionBar(binding.actionbar.toolbar, R.drawable.ic_burger_menu, false, true, true, false);
        binding.actionbar.toolbar.setNavigationOnClickListener(view -> drawer.openDrawer());
        CustomExpandableDrawerItem item1 = new CustomExpandableDrawerItem().withName(R.string.lbl_home).withIcon(R.drawable.weekly_updates);
//        CustomExpandableDrawerItem item2 = new CustomExpandableDrawerItem().withName(R.string.testimonials).withIcon(R.drawable.add);
//        CustomExpandableDrawerItem item4 = new CustomExpandableDrawerItem().withName(R.string.lbl_about_astrology).withIcon(R.drawable.about_astro);
//        CustomExpandableDrawerItem item5 = new CustomExpandableDrawerItem().withName(R.string.lbl_what_can_i_ask).withIcon(R.drawable.ask);
//        CustomExpandableDrawerItem item6 = new CustomExpandableDrawerItem().withName(R.string.lbl_share_with_friend_and_family).withIcon(R.drawable.share);
        CustomExpandableDrawerItem item7 = new CustomExpandableDrawerItem().withName(R.string.lbl_rate_astrology).withIcon(R.drawable.rate_astro);
//        CustomExpandableDrawerItem item8 = new CustomExpandableDrawerItem().withName(R.string.lbl_privacy_policy).withIcon(R.drawable.policy);
        CustomExpandableDrawerItem item9 = new CustomExpandableDrawerItem().withName(R.string.lbl_logout).withIcon(R.drawable.logout);

//        CustomExpandableDrawerItem item10 = new CustomExpandableDrawerItem().withName(R.string.profile).withIcon(R.drawable.profile);
        CustomExpandableDrawerItem item11 = new CustomExpandableDrawerItem().withName(R.string.orderhistroy).withIcon(R.drawable.policy);
//        CustomExpandableDrawerItem item12 = new CustomExpandableDrawerItem().withName(R.string.blogs).withIcon(R.drawable.policy);
//        CustomExpandableDrawerItem item13 = new CustomExpandableDrawerItem().withName(R.string.faq).withIcon(R.drawable.policy);
//        CustomExpandableDrawerItem item14 = new CustomExpandableDrawerItem().withName(R.string.temsandcondition).withIcon(R.drawable.policy);



//        CustomExpandableDrawerItem item3 = new CustomExpandableDrawerItem();
//        item3.withArrowHide(false);
//        item3.withIsExpanded(false);
//        item3.withIcon(R.drawable.help_center);
//        item3.withLevel(1);
//        item3.withName(R.string.lbl_help_and_support);
        CustomDividerDrawerItem divider = new CustomDividerDrawerItem();
        List<IDrawerItem> primaryDrawerItemList = new ArrayList<>();
//        item3.withSubItems(primaryDrawerItemList);
        primaryDrawerItemList.add(divider);
//        primaryDrawerItemList.add(new CustomExpandableDrawerItem().withName(R.string.lbl_faqs).witSubMenu(true));
//        primaryDrawerItemList.add(divider);
//        primaryDrawerItemList.add(new CustomExpandableDrawerItem().withName(R.string.lbl_contact_us).witSubMenu(true));
//        primaryDrawerItemList.add(divider);
//        primaryDrawerItemList.add(new CustomExpandableDrawerItem().withName(R.string.lbl_privacy_policy).witSubMenu(true));
//        primaryDrawerItemList.add(divider);
//        primaryDrawerItemList.add(new CustomExpandableDrawerItem().withName(R.string.lbl_how_it_works).witSubMenu(true));



        //divider,item1, divider, item2,divider,item11,divider,item12, divider, item3, divider, item4, divider, item5, divider, item6, divider, item7, divider, divider,divider,item13,divider,item9,divider


        drawer = new DrawerBuilder()
                .withActivity(this)
                .withSliderBackgroundColor(ContextCompat.getColor(this, R.color.subMenuItemBg))
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(divider , item1, divider,item11,divider,item7, divider,item9,divider//pass your items here
                ).withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    drawer.closeDrawer();
                    if (drawerItem instanceof CustomExpandableDrawerItem) {
                        CustomExpandableDrawerItem item = (CustomExpandableDrawerItem) drawerItem;

                        if(item.getName().getTextRes() == R.string.lbl_home)
                        {
                            ChanFrag="";
                            binding.actionbar.txtTitle.setText(R.string.app_name);
                            HomeFragment homeFragment = new HomeFragment();
                            loadFragment(homeFragment, HomeFragment.class.getSimpleName(), false);

                        } else if(item.getName().getTextRes() == R.string.lbl_help_and_support) {
                            drawer.openDrawer();
                        } else if (item.getName().getTextRes() == R.string.lbl_logout) {
                            showLogoutDialog();
                        } else if (item.getName().getTextRes() == R.string.lbl_about_astrology) {
                            Intent aboutAstrology = new Intent(HomeActivity.this, Supportchat.class);
                            startActivity(aboutAstrology);
                        } else if (item.getName().getTextRes() == R.string.lbl_faqs) {
                            Intent faqIntent = new Intent(HomeActivity.this, FAQsActivity.class);
                            startActivity(faqIntent);
                        } else if (item.getName().getTextRes() == R.string.lbl_how_it_works) {
                            Intent howitworkIntent = new Intent(HomeActivity.this, HowItWorksActivity.class);
                            startActivity(howitworkIntent);
                        } else if (item.getName().getTextRes() == R.string.lbl_contact_us) {
                            Intent contactUsIntent = new Intent(HomeActivity.this, ContactUsActivity.class);
                            startActivity(contactUsIntent);
                        } else if (item.getName().getTextRes() == R.string.lbl_share_with_friend_and_family) {

                            final String appPackageName = getPackageName();
                            PackageManager pm = getApplicationContext().getPackageManager();
                            try {

                                Intent sendInt = new Intent(Intent.ACTION_SEND);
                                sendInt.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                                sendInt.putExtra(Intent.EXTRA_TEXT, "Download AstroExperts app, your Problem our Solution"
                                        + "\n"+ Uri.parse("https://play.google.com/store/apps/details?" + appPackageName));
                                PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                sendInt.setPackage("com.whatsapp");
                                sendInt.setType("text/plain");
                                startActivity(Intent.createChooser(sendInt, "Share with"));

                            } catch (PackageManager.NameNotFoundException e) {
                                Intent i = new Intent();
                                i.putExtra(Intent.EXTRA_TEXT, "sharelink");
                                i.setAction(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("https://www.whatsapp.com/" + ("https://play.google.com/store/apps/details?"+appPackageName)));
                                startActivity(i);

                            }
                        } else if (item.getName().getTextRes() == R.string.lbl_privacy_policy) {
                            Intent privacyIntent = new Intent(HomeActivity.this, PrivacyPolicyActivity.class);
                            startActivity(privacyIntent);
                        }else if (item.getName().getTextRes() == R.string.lbl_rate_astrology) {
                            Intent privacyIntent = new Intent(HomeActivity.this, MainActivityChat.class);
                            startActivity(privacyIntent);
                        }else if (item.getName().getTextRes() == R.string.testimonials){
                            Intent privacyIntent = new Intent(HomeActivity.this, TestimonialMain.class);
                            startActivity(privacyIntent);
                        }else if (item.getName().getTextRes() == R.string.lbl_what_can_i_ask) {
                            Intent privacyIntent = new Intent(HomeActivity.this, Settings.class);
                            startActivity(privacyIntent);
                        }
                        else if (item.getName().getTextRes() == R.string.profile) {
                            Intent privacyIntent = new Intent(HomeActivity.this, RegisterUser.class);
                            startActivity(privacyIntent);
                        }
                        else if (item.getName().getTextRes() == R.string.orderhistroy) {
                            Intent privacyIntent = new Intent(HomeActivity.this, OrderHistory_Activity.class);
                            startActivity(privacyIntent);
                        }
                    }
                    return false;
                })
                .build();
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DatePickerDialogTheme);
        builder.setTitle(R.string.lbl_logout);
        builder.setMessage(R.string.lbl_you_sure_you_want_to_logout);
        builder.setPositiveButton(R.string.lbl_logout, (dialog, which) -> {

            SharedPreferences sharedPreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //-----here user is logout------
            editor.clear();
            editor.commit();
            Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finishAffinity();
        });
        builder.setNegativeButton(R.string.lbl_cancel, (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(dialog -> {
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.viewAllBtn));
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
        });
        alertDialog.show();
    }

    public void loadFragment(Fragment fragment, String tag, boolean isReplace) {
        fragmentManager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (isReplace) {
            ft.replace(R.id.container, fragment, tag);
            ft.addToBackStack(tag);
        } else {
            Fragment fragment1 = fragmentManager.findFragmentById(R.id.container);
            if (fragment1 != null) {
                String tag_ = fragment1.getTag();
                if (tag_ != null) {
                    ft.hide(fragmentManager.findFragmentByTag(tag_));
                }
            }
            ft.add(R.id.container, fragment, tag);
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
            return;
        } else if(ChanFrag.equals("Blog"))
        {
            ChanFrag="";
            binding.actionbar.txtTitle.setText(R.string.app_name);
            HomeFragment homeFragment = new HomeFragment();
            loadFragment(homeFragment, HomeFragment.class.getSimpleName(), false);
            
        } else if(ChanFrag.equals("Account"))
        {
            ChanFrag="";
            binding.actionbar.txtTitle.setText(R.string.app_name);
            HomeFragment homeFragment = new HomeFragment();
            loadFragment(homeFragment, HomeFragment.class.getSimpleName(), false);
        }
        else if(ChanFrag.equals("Home"))
        {
            ChanFrag="";
            popup();
        }
        else
        {
            super.onBackPressed();
        }



    }


    public void popup()
    {

        builder.setMessage("Are you sure want to exit.")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                        System.exit(0);

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
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();
                        }
                    });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle(R.string.app_name);
        alert.show();

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
                             Log.i("ghgdfsdfsfsdfsdfsd", response.toString());

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
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

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


    private void getToken()
    {

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            // Log.w("sucesssss", "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token2 = task.getResult().getToken();

                        // Log and toast
                        Log.d("tokenis2", token2);
                        SharedPreferences sharedPreferences = getSharedPreferences(RootURL.PREFACCOUNT, MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("token",token2);
                        editor.commit();
                        updateToken(token2);
                    }
                });
    }
    private void updateToken(String token)
    {
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        Integer id = Integer.valueOf(sharedpreferences.getString("id", ""));
        final String url = RootURL.add_or_update_device_token;
        Log.d("usreeisi",url);
        //String url=RootURL.Base_URL+"/Astroksbmadmin/api/Api_astro/add_or_update_device_token";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responsis",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                map.put("user_id", String.valueOf(id));
               // map.put("user_type", "astrologer");
                map.put("user_type", "user");
                map.put("device_token", token);
                Log.d("mapais",map.toString());
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                120000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);




    }


}
