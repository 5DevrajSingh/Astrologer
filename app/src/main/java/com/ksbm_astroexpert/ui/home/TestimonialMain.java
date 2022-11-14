package com.ksbm_astroexpert.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.ksbm_astroexpert.Chat.MainActivityChat;
import com.ksbm_astroexpert.Chat.activity.UsersActivity;
import com.ksbm_astroexpert.R;

public class TestimonialMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimonial_main);
    }
    public void journey(View view) {
        Intent privacyIntent = new Intent(TestimonialMain.this, EnquiryME.class);
        startActivity(privacyIntent);
    }

    public void share(View view) {
        final String appPackageName = getPackageName();
        PackageManager pm = getApplicationContext().getPackageManager();

        Intent intent = new Intent(TestimonialMain.this, MainActivityChat.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

       /* try {

            Intent sendInt = new Intent(Intent.ACTION_SEND);
            sendInt.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            sendInt.putExtra(Intent.EXTRA_TEXT, "Download AstroExperts app"
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

        }*/
    }


    public void backed(View view) {
        onBackPressed();
    }
}