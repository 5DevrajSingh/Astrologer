package com.ksbm_astroexpert.Chat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.ksbm_astroexpert.Chat.activity.ProfileActivity;
import com.ksbm_astroexpert.Chat.activity.UsersActivity;
import com.ksbm_astroexpert.Chat.fragment.ChatFragment;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.ksbm_astroexpert.ui.signin.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


public class MainActivityChat extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainnd);
        // Action bar related
        Toolbar toolbar = findViewById(R.id.main_app_bar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Astro Expert");
        // Fragments handler using SmartTabLayout
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                //.add("Requests", RequestsFragment.class)
                .add("Chat", ChatFragment.class)
               // .add("Friends", FriendsFragment.class)
                .create());

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            // If no logged in user send them to login/register

            Intent welcomeIntent = new Intent(MainActivityChat.this, SignInActivity.class);
            startActivity(welcomeIntent);
            finish();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser != null) {
            FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid()).child("online").setValue("true");
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null) {
            FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid()).child("online").setValue(ServerValue.TIMESTAMP);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(MainActivityChat.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
          /*  case R.id.menuLogout:
                AlertDialog.Builder logoutBuilder = new AlertDialog.Builder(MainActivityChat.this);
                logoutBuilder.setTitle("Logout");
                logoutBuilder.setMessage("Are you sure you want to logout?");
                logoutBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("online").setValue(ServerValue.TIMESTAMP);

                        FirebaseAuth.getInstance().signOut();

                        Intent welcomeIntent = new Intent(MainActivityChat.this, Login.class);
                        startActivity(welcomeIntent);
                        finish();
                    }
                });
                logoutBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.dismiss();
                    }
                });
                AlertDialog logoutDialog = logoutBuilder.create();
                logoutDialog.show();
                return true;
           */

            case R.id.menuProfile:
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                profileIntent.putExtra("userid", FirebaseAuth.getInstance().getCurrentUser().getUid());
               // startActivity(profileIntent);
                return true;
            case R.id.menuSearch:
                Intent usersIntent = new Intent(this, UsersActivity.class);
                startActivity(usersIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}