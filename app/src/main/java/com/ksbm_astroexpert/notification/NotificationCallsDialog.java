package com.ksbm_astroexpert.notification;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.ksbm_astroexpert.Chat.MainActivityChat;
import com.ksbm_astroexpert.R;

public class NotificationCallsDialog extends Activity implements OnClickListener {
    private Context context = this;
    String username="",user_id="",title = "", message = "", type = "", id = "", image = "", orderId = "", orderStatus = "", status = "",
            storeStatus = "", notification_id = "", inviteUnique = "", fullName = "", profileImage = "";
    String yes_no = "";
    TextView name,reject,accept,mesg;
    ImageView imageView,close;

    Bundle bundle;
    ImageView iv_banner;
    private ProgressDialog progressDialog;
    private MediaPlayer mp;
    private String booking_id="";

    @Override
    public void onBackPressed() {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //setContentView(R.layout.dialog_notification);
            setContentView(R.layout.call_dialog_notification);
            context = this;


            Window window = getWindow();
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(0));
            Dialog dialog = new Dialog(context);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            // dialog.setContentView(R.layout.dialog_box_ok);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));




            reject=findViewById(R.id.reject);
            reject.setOnClickListener(this);
            accept=findViewById(R.id.accept);
            accept.setOnClickListener(this);
            imageView=findViewById(R.id.image);
            name=findViewById(R.id.name);
            mesg=findViewById(R.id.mesg);
            close=findViewById(R.id.close);
            close.setOnClickListener(this);

            bundle = getIntent().getExtras();
            mp = MediaPlayer.create(this, R.raw.new_ringtone);
            mp.setLooping(false);
            mp.start();

            if (bundle != null) {
                type = bundle.getString("type");
                username=bundle.getString("username");
                message = bundle.getString("message");
                title = bundle.getString("title");
                id = bundle.getString("user_type");
                booking_id=bundle.getString("booking_id");
                image = bundle.getString("filename");
                notification_id = bundle.getString("notification_id");
                inviteUnique = bundle.getString("inviteunique");
                fullName = bundle.getString("full_name");
                profileImage = bundle.getString("profile_pic");
                user_id=bundle.getString("user_id");
                name.setText(username);
                name.setTextColor(ContextCompat.getColor(context, R.color.black));
                mesg.setText(message);

            }

            try
            {
                  /*  Picasso.get().load(profileImage)
                            .error(R.drawable.logo).placeholder(R.drawable.logo).into(iv_banner);*/
                Log.d("imageurlsis",profileImage);
                Glide.with(this)
                        .load(profileImage) // image url
                        .placeholder(R.drawable.logo) // any placeholder to load at start
                        .error(R.drawable.logo)  // any image in case of error
                        .override(200, 200)
                        .centerCrop()
                        .into(imageView);// resizing

            }
            catch (Exception e)
            {

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_no:
                if (mp.isPlaying()) {
                    mp.stop();
                }

                finish();
                break;
            case R.id.tv_yes:
                if (mp.isPlaying()) {
                    mp.stop();
                }
                finish();

                break;

            case R.id.accept:
                Intent chatIntent = new Intent(NotificationCallsDialog.this, MainActivityChat.class);
                chatIntent.putExtra("userid", user_id);
                startActivity(chatIntent);
                finish();
                break;
            case R.id.reject:
                finish();
                break;
            case R.id.close:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}
