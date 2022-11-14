package com.ksbm_astroexpert.Chat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.ksbm_astroexpert.Adapter.HistoryTabAdapter;
import com.ksbm_astroexpert.Chat.fragment.Call_History_Fragment;
import com.ksbm_astroexpert.Chat.fragment.Chat_History_Fragment;
import com.ksbm_astroexpert.R;

public class OrderHistory_Activity extends AppCompatActivity{
    private HistoryTabAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_);
        viewPager = findViewById(R.id.pager);


        ImageView imageBack=findViewById(R.id.img_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        // setting up the adapter
        viewPagerAdapter = new HistoryTabAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new Call_History_Fragment(), "Call");
        viewPagerAdapter.add(new Chat_History_Fragment(), "Chat");

        // Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to  set the page viewer
        // we use the setupWithViewPager().
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

}