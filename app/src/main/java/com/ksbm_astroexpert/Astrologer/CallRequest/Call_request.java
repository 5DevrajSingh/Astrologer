package com.ksbm_astroexpert.Astrologer.CallRequest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ksbm_astroexpert.Astrologer.Astrologer;
import com.ksbm_astroexpert.Constant.LocalDataBase;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.notification.MyFirebaseMessagingService;
import com.ksbm_astroexpert.notification.NotificationDialog;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Call_request  extends Activity implements View.OnClickListener {


    public static boolean IsRequest=false;
    String title = "", message = "", type = "", id = "", image = "", orderId = "", orderStatus = "", status = "",
            storeStatus = "", notification_id = "", inviteUnique = "", fullName = "", profileImage = "",booking_id="",name="";

    CircleImageView UserPic;
    TextView usernamechat;

    CountDownTimer caont;

    ImageView acceptchat,cancelchat;
    private MediaPlayer mp;

    Bundle bundle;
    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.call_request);

            cancelchat = findViewById(R.id.cancelchat);
            acceptchat = findViewById(R.id.acceptchat);

            UserPic = findViewById(R.id.UserPic);
            usernamechat = findViewById(R.id.usernamechat);

            acceptchat.setOnClickListener(this);
            cancelchat.setOnClickListener(this);
            mp = MediaPlayer.create(this, R.raw.new_ringtone);
            mp.setLooping(true);
            mp.start();

            bundle = getIntent().getExtras();

            if (bundle != null) {

                type = bundle.getString("type");
                message = bundle.getString("message");
                title = bundle.getString("title");
                id = bundle.getString("user_type");
                booking_id=bundle.getString("booking_id");
                image = bundle.getString("image");
                name = bundle.getString("name");
                notification_id = bundle.getString("notification_id");
                inviteUnique = bundle.getString("inviteunique");

                fullName = bundle.getString("full_name");
                profileImage = bundle.getString("profile_image");




                Picasso.get()
                        .load(image.replaceAll(" ","%20"))
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        //.resize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()))
                        // .centerCrop()
                        .placeholder(R.drawable.logo)
                        .into(UserPic, new Callback()
                        {
                            @Override
                            public void onSuccess()
                            {

                            }

                            @Override
                            public void onError(Exception e)
                            {
                                Picasso.get()
                                        .load(image)
                                        //.resize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()))
                                        //.centerCrop()
                                        .placeholder(R.drawable.logo)
                                        .error(R.drawable.logo)
                                        .into(UserPic);
                            }
                        });



                usernamechat.setText(name);



                countDown();



            }



        } catch (Exception e) {
            e.printStackTrace();
        }


        SharedPreferences sharedPreferences = getSharedPreferences("GetRequest", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("getrequest",false);
        editor.commit();
        editor.apply();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelchat:
                if (mp.isPlaying()) {
                    mp.stop();
                }
                Toast.makeText(this, "Working on this...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.acceptchat:
                if (mp.isPlaying()) {
                    mp.stop();
                }
                IsRequest=true;
                fetchastrologerdetails();
                try
                {
                    caont.cancel();
                }
                catch (Exception e)
                {

                }

                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        try
        {
            caont.cancel();
        }
        catch (Exception e)
        {

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void fetchastrologerdetails()
    {
        Intent chatIntent = new Intent(Call_request.this, Astrologer.class);
        chatIntent.putExtra("title","Received Accept");

        SharedPreferences sharedPreferences = getSharedPreferences("RequestDetail", Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("IsAdded",false))
        {
            Toast.makeText(this, "Added"+"  "+sharedPreferences.getString("chatid","").toString(), Toast.LENGTH_SHORT).show();

            try
            {
                chatIntent.putExtra("userid", sharedPreferences.getString("chatid","").toString());
                Log.d("chat_price_m", sharedPreferences.getString("chat_price_m","").toString());
                chatIntent.putExtra("chatprice", sharedPreferences.getString("chat_price_m","").toString());
                Log.d("call_price_m", sharedPreferences.getString("call_price_m","").toString());
                chatIntent.putExtra("callprice", sharedPreferences.getString("call_price_m","").toString());
                Log.d("astroid", sharedPreferences.getString("astroid","").toString());
                chatIntent.putExtra("astroid", sharedPreferences.getString("astroid","").toString());
                Log.d("id1", sharedPreferences.getString("id1","").toString());
                chatIntent.putExtra("id", sharedPreferences.getString("id1","").toString());
                Log.d("image", sharedPreferences.getString("image","").toString());
                chatIntent.putExtra("image", sharedPreferences.getString("image","").toString());
                Log.d("lang", sharedPreferences.getString("lang","").toString());
                chatIntent.putExtra("lang", sharedPreferences.getString("lang","").toString());
                Log.d("userbanlance", sharedPreferences.getString("userbanlance","").toString());
                chatIntent.putExtra("userbalance", sharedPreferences.getString("userbanlance","").toString());
                Log.d("astroname", sharedPreferences.getString("astroname","").toString());
                chatIntent.putExtra("astroname", sharedPreferences.getString("astroname","").toString());
                Log.d("TAG", sharedPreferences.getString("chat_price_m","").toString());
                chatIntent.putExtra("type", "chat");
                Log.d("chat_commission", sharedPreferences.getString("chat_commission","").toString());
                chatIntent.putExtra("chat_commission", sharedPreferences.getString("chat_commission","").toString());
                Log.d("call_commission", sharedPreferences.getString("call_commission","").toString());
                chatIntent.putExtra("call_commission", sharedPreferences.getString("call_commission","").toString());
                Log.d("astro_token", sharedPreferences.getString("astro_token","").toString());
                chatIntent.putExtra("astro_token", sharedPreferences.getString("astro_token","").toString());
                startActivity(chatIntent);
                try{
                    caont.cancel();
                }
                catch (Exception e)
                {

                }

                finish();

            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            chatIntent.putExtra("userid", LocalDataBase.chatid1);
            chatIntent.putExtra("chatprice", LocalDataBase.chat_price_m1);
            chatIntent.putExtra("callprice", LocalDataBase.call_price_m1);
            chatIntent.putExtra("astroid", LocalDataBase.astroid1);
            chatIntent.putExtra("id", LocalDataBase.id1);
            chatIntent.putExtra("image", LocalDataBase.image1);
            chatIntent.putExtra("lang", LocalDataBase.lang1);
            chatIntent.putExtra("userbalance", LocalDataBase.userbanlance1);
            chatIntent.putExtra("astroname", LocalDataBase.astroname1);
            chatIntent.putExtra("type", "chat");
            chatIntent.putExtra("chat_commission", LocalDataBase.chat_commission1);
            chatIntent.putExtra("call_commission", LocalDataBase.call_commission1);
            chatIntent.putExtra("astro_token", LocalDataBase.astro_token1);
            startActivity(chatIntent);
            try{
                caont.cancel();
            }
            catch (Exception e)
            {

            }
            finish();
        }


    }



    public void countDown()
    {
      caont =  new CountDownTimer(150000, 1000) {
            public void onTick(long millisUntilFinished) {
                //call to my UI thread every 2 seconds
                if(MyFirebaseMessagingService.CallRejected)
                {
                    Toast.makeText(Call_request.this, "Caller is busy, try again later.", Toast.LENGTH_SHORT).show();
                    MyFirebaseMessagingService.CallRejected=false;
                    finish();
                }
                else
                {
                    //Do nothimg
                }
            }
            public void onFinish() {
                //final call to my UI thread after 10 seconds
                Toast.makeText(Call_request.this, "Caller not responding, try again later.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }.start();
    }





}
