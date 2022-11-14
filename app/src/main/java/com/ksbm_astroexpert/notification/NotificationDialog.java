package com.ksbm_astroexpert.notification;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ksbm_astroexpert.Astrologer.Astrologer;
import com.ksbm_astroexpert.Constant.LocalDataBase;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.Astrodetails.Astrologerdetails;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class NotificationDialog extends Activity implements OnClickListener {
    private Context context = this;
    private TextView tvMessage, tvTitleDailogBox;
    private TextView tv_no, tv_yes;
    String title = "", message = "", type = "", id = "", image = "", orderId = "", orderStatus = "", status = "",
            storeStatus = "", notification_id = "", inviteUnique = "", fullName = "", profileImage = "";
    String yes_no = "";
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
            setContentView(R.layout.dialog_notification);
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
            tvMessage = (TextView) findViewById(R.id.tv_message);
            iv_banner = (ImageView) findViewById(R.id.iv_banner);
            tvTitleDailogBox = (TextView) findViewById(R.id.title_dailog_box);
            tvMessage.setMovementMethod(new ScrollingMovementMethod());
            tv_yes = (TextView) findViewById(R.id.tv_yes);
            tv_no = (TextView) findViewById(R.id.tv_no);
            tv_yes.setOnClickListener(this);
            tv_no.setOnClickListener(this);
            bundle = getIntent().getExtras();
            mp = MediaPlayer.create(this, R.raw.new_ringtone);
            mp.setLooping(false);
            mp.start();

            if (bundle != null) {

                type = bundle.getString("type");
                message = bundle.getString("message");
                title = bundle.getString("title");
                id = bundle.getString("user_type");
                booking_id=bundle.getString("booking_id");
                image = bundle.getString("filename");
                notification_id = bundle.getString("notification_id");
                inviteUnique = bundle.getString("inviteunique");

                fullName = bundle.getString("full_name");
                profileImage = bundle.getString("profile_image");



            }
            tvMessage.setText(Html.fromHtml(message));
            tvTitleDailogBox.setText(Html.fromHtml(title));
            if (!image.equals("")) {
                iv_banner.setVisibility(View.VISIBLE);
//                Picasso.with(context).load(image)
//                        .error(R.drawable.newlogo).placeholder(R.drawable.newlogo).into(iv_banner);

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

                String Message= tvMessage.getText().toString();

                if(Message.equals("Your chat request has been accepted by astologer."))
                {
//                    Intent intent = new Intent(NotificationDialog.this, Astrologer.class);
//                    Bundle bundle1 = new Bundle();
//                    bundle1.putString("title", "" + "Received Accept");
//                    intent.putExtras(bundle1);
//                    startActivity(intent);

                    fetchastrologerdetails();
                }

                finish();
                break;
        }
    }




    private void fetchastrologerdetails()
    {
        Intent chatIntent = new Intent(NotificationDialog.this, Astrologer.class);
        chatIntent.putExtra("title","Received Accept");

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
        finish();
    }









    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}
