package com.ksbm_astroexpert.Chat.activity;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Astrologer.CallRequest.Call_request;
import com.ksbm_astroexpert.Chat.RegisterUser1;
import com.ksbm_astroexpert.Chat.adapter.MessageAdapter;
import com.ksbm_astroexpert.Chat.models.Message;
import com.ksbm_astroexpert.Chat.utils.SharedPreference;
import com.ksbm_astroexpert.Constant.LocalDataBase;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.InternetCheck.ConnectivityReceiver;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.Review.ADDRatting;
import com.ksbm_astroexpert.ui.Wallet.RechargeWallet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.ksbm_astroexpert.ui.signin.SignInActivity;
import com.ksbm_astroexpert.ui.splash.SplashActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

//    Rating View

    boolean ishit=false;


    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    boolean check_timmer=false;

    RatingBar ratebar;
    TextView tvrate,name;
    EditText etreview;
    Button btnreview;
    String review="";
    String rateing,id1,chat_id1;
    ImageView imageViewback;
    CircleImageView ivProfile;
    RelativeLayout chatFeedback;


    public static String ChatStarted="";

//    Ratting View

    private final String TAG = "CA/ChatActivity";
    // public String Legacy_SERVER_KEY = "AIzaSyCRgqSMUElg2B0uG8mY3lEjYlknzs7EJrc";
    public String Legacy_SERVER_KEY = "AAAA1vnAts4:APA91bHaqKhnLBqdFqdldonBkksYpCqPwGrW4HXJx26UaxTY4W_8m35Wplt6yalp8GG2gUUPormzDr_SIliJ69gtfqDMIDDKWBvNgXp1-ZMgMoWvdjY03s3tgyktJE11Ep3xQAoU8woe";

    public String otherdeviceidtoken, currentuseridname, otherdeviceidimage;
    public String curr_otherdeviceidtoken, curr_currentuseridname, curr_otherdeviceidimage;

    private String selectedImagePath = "";
    // Will handle all changes happening in database
    CountDownTimer cdt = null;
    private DatabaseReference userDatabase, chatDatabase;
    private ValueEventListener userListener, chatListener;

    // Will handle old/new messages between users

    private Query messagesDatabase;
    private ChildEventListener messagesListener;

    private MessageAdapter messagesAdapter;
    private final List<Message> messagesList = new ArrayList<>();

    // User data
    String makeurl;
    CountDownTimer countDownTimer;
    private String currentUserId;

    ProgressDialog progressDialog;

    // activity_chat views

    private EditText messageEditText;
    private RecyclerView recyclerView;
    private FloatingActionButton sendButton;
    private ImageView sendPictureButton;
    private String imgPath;
    // chat_bar views
    String message;
    private TextView appBarName, appBarSeen, timmer;

    // Will be used on Notifications to detairminate if user has chat window open

    public static String otherUserId;
    public static boolean running = false;
    RelativeLayout chat_relative;
    String chatprice;
    String userbalance, id, image;
    LinearLayout recharebox;
    String chat_reqiest_status = "", astro_id = "";
    String chatcommision, callcommission, astroname, chat_id, astro_token = "";
    boolean wallet_status = true;
    private String timer_st, chattypehistroy;
    private String currentDateAndTime;
    SharedPreference sharedPreference;

    int dminutes1=0;

    boolean ratting=false;

    private BroadcastReceiver mNetworkReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        progressDialog= new ProgressDialog(ChatActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait");
        timmer = findViewById(R.id.timmer);
        running = true;

        mNetworkReceiver = new ConnectivityReceiver();
        registerNetworkBroadcastForNougat();


        messageEditText = findViewById(R.id.chat_message);
        recharebox = findViewById(R.id.recharebox);
        recyclerView = findViewById(R.id.chat_recycler);
        sharedPreference = SharedPreference.getInstance(this);

        recharebox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RechargeWallet.class);
                startActivity(intent);
            }
        });
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Log.i("Fsfsdfsfdsf", currentUserId);

        otherUserId = getIntent().getStringExtra("userid");
        Log.d("otherUserId", otherUserId);

        chatprice = getIntent().getStringExtra("chatprice");
        userbalance = getIntent().getStringExtra("userbalance");
        chatcommision = getIntent().getStringExtra("chat_commission");
        callcommission = getIntent().getStringExtra("call_commission");
        astro_token = getIntent().getStringExtra("astro_token");
        chat_id = getIntent().getStringExtra("chat_id");
        id = getIntent().getStringExtra("id");
        image = getIntent().getStringExtra("image");
        astroname = getIntent().getStringExtra("astroname");
        chattypehistroy = getIntent().getStringExtra("chattypehistroy");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        getValue for App kill background

        SharedPreferences sharedpreferences1 =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        LocalDataBase.UserIdL=sharedpreferences1.getString("id", "");
        LocalDataBase.chat_commissionL=chatcommision;
        LocalDataBase.chat_price_mL=chatprice;
        LocalDataBase.idL=id;
        LocalDataBase.chatidL=chat_id;


        LocalDataBase.transidL=sharedPreference.getString("transd_id", "");

//        getValue for App kill background



        messagesAdapter = new MessageAdapter(messagesList);

        recyclerView.setAdapter(messagesAdapter);

        ImageView chat_user_pic = findViewById(R.id.chat_user_pic);
        TextView astronamed = findViewById(R.id.astr);
        try {
            Picasso.get().load(image)
                    .error(R.drawable.splash)
                    .into(chat_user_pic);
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }

        // Log.i("dfsdfdsddfds",astroname);
        astronamed.setText(astroname);

        chat_relative = findViewById(R.id.chat_relative);
        TextView endchat = findViewById(R.id.endchat);



        //////////Ratting Chating

        name = findViewById(R.id.name);
        etreview=findViewById(R.id.txtreview);
        btnreview=findViewById(R.id.btnreview);
        ratebar= findViewById(R.id.rate1);
        tvrate= findViewById(R.id.tvrate1);
        ivProfile = findViewById(R.id.ivProfile);
        chatFeedback = findViewById(R.id.chatFeedback);


        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        try {
            Picasso.get().load(sharedpreferences.getString("user_profile_image", "0"))
                    .placeholder(R.drawable.splash)
                    .into(ivProfile);
        }
        catch ( Exception e)
        {

        }

        name.setText(sharedpreferences.getString("username",""));

        btnreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();

            }
        });

        //////////Ratting Cahitng


//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //Do something after 1 second
//                        refresh();
//                        sendwelcomemsg();
//                    }
//                }, 3000);
//            }
//        });


        if (chattypehistroy.equals("1")) {
            chat_relative.setVisibility(View.GONE);
            endchat.setVisibility(View.GONE);
            timmer.setVisibility(View.GONE);

        } else {
            double chatpriced = Double.parseDouble(chatprice);
            double userbalanced = Double.parseDouble(userbalance);
            double chatcommissiond = Double.parseDouble(chatcommision);

            double totalminute = 0;
            double perMinute = chatpriced + chatcommissiond;

            totalminute = userbalanced / perMinute;

//            int a = (int) Math.round(totalminute+1);
            int a = (int) (totalminute+1);

            String second = String.valueOf(totalminute+1);

            String str = second;
            String separator =".";
            int sepPos = str.indexOf(separator);
            if (sepPos == -1) {
                System.out.println("");
            }

            String sec = str.substring(sepPos + separator.length());

            try {
                if(Integer.parseInt(sec)<10)
                {
                    sec=sec+"0";
                }
            }
            catch (Exception e)
            {

            }





////            String document = segments[1];
            sec = sec.length() < 2 ? sec : sec.substring(0, 2);
            int sec1 = Integer.parseInt(sec);

            if(sec1%2==0)
            {

            }
            else
            {

                if(sec1==99)
                {
                    sec1=sec1-1;
                }
                else
                {
                    sec1=sec1+1;
                }
            }

            if(sec1==0)
            {
                sec1 =0;
            }
            else
            {
                sec1 = (sec1)/2;

                String Check = String.valueOf(sec1)+".0";
                double s =1.2*Double.valueOf(Check);

                String str1 = String.valueOf(s);
                String[] arr = str1.split("\\.", 0);
                int i=0;

                for (String w : arr) {
                    if(i==0)
                    {
                        str1=w;
                    }
                    i++;
                }

                System.out.println("Split array length: "+arr.length);

                Log.e("secondcheck1",str1);
////            String document = segments[1];
//            sec3 = sec3.length() < 2 ? sec3 : sec3.substring(0, 2);
                sec1 = Integer.parseInt(str1);

             //   Toast.makeText(this, "Min: "+String.valueOf(a)+", "+"Sec: "+str1+"  >"+arr.length, Toast.LENGTH_LONG).show();


            }


//            int sec2 =  (int) 1.2 * sec1;



//            int a = (int) totalminute+1;
//            float b = (float) totalminute+1;

            String strin = String.valueOf(a);
            Log.i("Dfsfsddfdffdfsdfs", String.valueOf(chatpriced));
            Log.i("Dffffsfsdfsdfs", String.valueOf(userbalanced));
            Log.i("Dfsfsdfsdfffs", String.valueOf(chatcommissiond));
            Log.i("Dfsfsdffxfsdfs", strin);
            getNewRequest(strin,sec1);

            if(Call_request.IsRequest && !check_timmer)
            {
                timerStart(strin,sec1);
            }

            // timerStart(strin);

        }

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View actionBarView = inflater.inflate(R.layout.chat_bar, null);

        appBarName = actionBarView.findViewById(R.id.chat_bar_name);
        appBarSeen = actionBarView.findViewById(R.id.chat_bar_seen);

        // actionBar.setCustomView(actionBarView);

        // Will handle the send button to send a message

        sendButton = findViewById(R.id.chat_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wallet_status == true) {
                    sendMessage();
                    sendFCMPush();
                } else {
                    Toast.makeText(ChatActivity.this, "You have not enough balance!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        sendPictureButton = findViewById(R.id.chat_send_picture);
        sendPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
               /* Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "Select Image"), 1);*/

            }
        });


        // Will handle typing feature, 0 means no typing, 1 typing, 2 deleting and 3 thinking (5+ sec delay)

        messageEditText.addTextChangedListener(new TextWatcher() {
            private Timer timer = new Timer();

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (messagesList.size() > 0) {
                    if (charSequence.length() == 0) {
                        FirebaseDatabase.getInstance().getReference().child("Chat").child(currentUserId).child(otherUserId).child("typing").setValue(0);

                        timer.cancel();
                    } else if (i2 > 0) {
                        FirebaseDatabase.getInstance().getReference().child("Chat").child(currentUserId).child(otherUserId).child("typing").setValue(1);

                        timer.cancel();
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                FirebaseDatabase.getInstance().getReference().child("Chat").child(currentUserId).child(otherUserId).child("typing").setValue(3);
                            }
                        }, 5000);
                    } else if (i1 > 0) {
                        FirebaseDatabase.getInstance().getReference().child("Chat").child(currentUserId).child(otherUserId).child("typing").setValue(2);

                        timer.cancel();
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                FirebaseDatabase.getInstance().getReference().child("Chat").child(currentUserId).child(otherUserId).child("typing").setValue(3);
                            }
                        }, 5000);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Checking if root layout changed to detect soft keyboard

        final RelativeLayout root = findViewById(R.id.chat_root);
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            int previousHeight = root.getRootView().getHeight() - root.getHeight() - recyclerView.getHeight();

            @Override
            public void onGlobalLayout() {
                int height = root.getRootView().getHeight() - root.getHeight() - recyclerView.getHeight();

                if (previousHeight != height) {
                    if (previousHeight > height) {
                        previousHeight = height;
                    } else if (previousHeight < height) {
                        recyclerView.scrollToPosition(messagesList.size() - 1);

                        previousHeight = height;
                    }
                }
            }
        });

        //===============chat timer for payment======

    }





    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }




    public void refresh()
    {

        if(!ishit)
        {
   //         fetchServerTime();
        }
        else
        {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //Do something after 1 second
//                            refresh();
//                        }
//                    }, 1000);
//                }
//            });
        }

    }



    private void sendwelcomemsg() {
        message = "Welcome to AstroExperts consultant will take a min to prepare your chart. You may ask your question in the meanwhile.";

        // Pushing message/notification so we can get keyIds

        DatabaseReference userMessage = FirebaseDatabase.getInstance().getReference().child("Messages").child(currentUserId).child(otherUserId).push();
        String pushId = userMessage.getKey();

        DatabaseReference notificationRef = FirebaseDatabase.getInstance().getReference().child("Notifications").child(otherUserId).push();
        String notificationId = notificationRef.getKey();

        // "Packing" message

        Map messageMap = new HashMap();
        messageMap.put("message", message);
        messageMap.put("type", "text");
        messageMap.put("from", currentUserId);
        messageMap.put("to", otherUserId);

        HashMap<String, String> notificationData = new HashMap<>();
        notificationData.put("from", currentUserId);
        notificationData.put("type", "message");
        messageMap.put("timestamp", ServerValue.TIMESTAMP);

        Map userMap = new HashMap();
        userMap.put("Messages/" + currentUserId + "/" + otherUserId + "/" + pushId, messageMap);
        userMap.put("Messages/" + otherUserId + "/" + currentUserId + "/" + pushId, messageMap);

        userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/message", message);
        userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/timestamp", ServerValue.TIMESTAMP);
        userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/seen", ServerValue.TIMESTAMP);

        userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/message", message);
        userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/timestamp", ServerValue.TIMESTAMP);
        userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/seen", 0);

        userMap.put("Notifications/" + otherUserId + "/" + notificationId, notificationData);
        // Updating database with the new data including message, chat and notification

        FirebaseDatabase.getInstance().getReference().updateChildren(userMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if (databaseError != null) {
                    Log.d(TAG, "sendMessage(): updateChildren failed: " + databaseError.getMessage());
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        running = true;

        FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId).child("online").setValue("true");
        loadMessages();
        initDatabases();
    }


    @Override
    public void onBackPressed() {

        if(ratting)
        {
            Toast.makeText(getApplicationContext(), "Please provide rating to the Expert.", Toast.LENGTH_LONG).show();
        }
        else
        {
            alertBackChat();
        }


        // NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            final Uri url = data.getData();
            Log.i("sddfdsdfsdf", url.toString());
            DatabaseReference messageRef = FirebaseDatabase.getInstance().getReference().child("Messages").child(currentUserId).child(otherUserId).push();
            final String messageId = messageRef.getKey();

            DatabaseReference notificationRef = FirebaseDatabase.getInstance().getReference().child("Notifications").child(otherUserId).push();
            final String notificationId = notificationRef.getKey();

            final StorageReference file = FirebaseStorage.getInstance().getReference().child("message_images").child(messageId + ".jpg");

            file.putFile(url).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                    if (task.isSuccessful()) {
                        file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                makeurl = String.valueOf(uri);
                                userDatabase.child("Chat").child("message").setValue(makeurl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        file.putFile(url).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    // String imageUrl = task.getResult().getDownloadUrl().toString();

                                                    //    String imageUrl = task.getResult().getMetadata().getReference().getDownloadUrl().toString();
                                                    Map messageMap = new HashMap();
                                                    messageMap.put("message", makeurl);
                                                    messageMap.put("type", "image");
                                                    messageMap.put("from", currentUserId);
                                                    messageMap.put("to", otherUserId);
                                                    messageMap.put("timestamp", ServerValue.TIMESTAMP);

                                                    HashMap<String, String> notificationData = new HashMap<>();
                                                    notificationData.put("from", currentUserId);
                                                    notificationData.put("type", "message");

                                                    Map userMap = new HashMap();
                                                    userMap.put("Messages/" + currentUserId + "/" + otherUserId + "/" + messageId, messageMap);
                                                    userMap.put("Messages/" + otherUserId + "/" + currentUserId + "/" + messageId, messageMap);

                                                    userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/message", "You have sent a picture.");
                                                    userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/timestamp", ServerValue.TIMESTAMP);
                                                    userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/seen", ServerValue.TIMESTAMP);

                                                    userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/message", "Has send you a picture.");
                                                    userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/timestamp", ServerValue.TIMESTAMP);
                                                    userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/seen", 0);
                                                    userMap.put("Notifications/" + otherUserId + "/" + notificationId, notificationData);

                                                    FirebaseDatabase.getInstance().getReference().updateChildren(userMap, new DatabaseReference.CompletionListener() {
                                                        @Override
                                                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                            sendButton.setEnabled(true);

                                                            if (databaseError != null) {
                                                                Log.d(TAG, "sendMessage(): updateChildren failed: " + databaseError.getMessage());
                                                            }
                                                        }
                                                    });

                                                    //Log.i("DFsdfdsf",makeurl);
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        // Updating image on user data
                        //   sendFCMPush();
                    } else {
                        Log.d(TAG, "uploadImage listener failed: " + task.getException().getMessage());
                    }

                }
            });


        } else if (requestCode == 0 && resultCode == RESULT_OK) {

            // final Uri url = data.getData();

            Bitmap photo = (Bitmap) data.getExtras().get("data");


            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri url = getImageUri(getApplicationContext(), photo);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(getRealPathFromURI(url));
            Log.i("sddfdsdfdsdsdsdf", finalFile.toString());
            DatabaseReference messageRef = FirebaseDatabase.getInstance().getReference().child("Messages").child(currentUserId).child(otherUserId).push();
            final String messageId = messageRef.getKey();

            DatabaseReference notificationRef = FirebaseDatabase.getInstance().getReference().child("Notifications").child(otherUserId).push();
            final String notificationId = notificationRef.getKey();

            final StorageReference file = FirebaseStorage.getInstance().getReference().child("message_images").child(messageId + ".jpg");

            file.putFile(url).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                    if (task.isSuccessful()) {
                        file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                makeurl = String.valueOf(uri);
                                userDatabase.child("Chat").child("message").setValue(makeurl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        file.putFile(url).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    // String imageUrl = task.getResult().getDownloadUrl().toString();

                                                    //    String imageUrl = task.getResult().getMetadata().getReference().getDownloadUrl().toString();
                                                    Map messageMap = new HashMap();
                                                    messageMap.put("message", makeurl);
                                                    messageMap.put("type", "image");
                                                    messageMap.put("from", currentUserId);
                                                    messageMap.put("to", otherUserId);
                                                    messageMap.put("timestamp", ServerValue.TIMESTAMP);

                                                    HashMap<String, String> notificationData = new HashMap<>();
                                                    notificationData.put("from", currentUserId);
                                                    notificationData.put("type", "message");

                                                    Map userMap = new HashMap();
                                                    userMap.put("Messages/" + currentUserId + "/" + otherUserId + "/" + messageId, messageMap);
                                                    userMap.put("Messages/" + otherUserId + "/" + currentUserId + "/" + messageId, messageMap);

                                                    userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/message", "You have sent a picture.");
                                                    userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/timestamp", ServerValue.TIMESTAMP);
                                                    userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/seen", ServerValue.TIMESTAMP);

                                                    userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/message", "Has send you a picture.");
                                                    userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/timestamp", ServerValue.TIMESTAMP);
                                                    userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/seen", 0);
                                                    userMap.put("Notifications/" + otherUserId + "/" + notificationId, notificationData);

                                                    FirebaseDatabase.getInstance().getReference().updateChildren(userMap, new DatabaseReference.CompletionListener() {
                                                        @Override
                                                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                            sendButton.setEnabled(true);

                                                            if (databaseError != null) {
                                                                Log.d(TAG, "sendMessage(): updateChildren failed: " + databaseError.getMessage());
                                                            }
                                                        }
                                                    });

                                                    //Log.i("DFsdfdsf",makeurl);
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        // Updating image on user data
                        //   sendFCMPush();
                    } else {
                        Log.d(TAG, "uploadImage listener failed: " + task.getException().getMessage());
                    }

                }
            });

        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    private void initDatabases() {
        // Initialize/Update realtime other user data such as name and online status

        userDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(otherUserId);
        userListener = new ValueEventListener() {
            Timer timer;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    String name = dataSnapshot.child("name").getValue().toString();

                    appBarName.setText(name);

                    final String online = dataSnapshot.child("online").getValue().toString();

                    if (online.equals("true")) {
                        if (timer != null) {
                            timer.cancel();
                            timer = null;
                        }

                        appBarSeen.setText("Online");
                    } else {
                        if (appBarSeen.getText().length() == 0) {
                            appBarSeen.setText("Last Seen: " + getTimeAgo(Long.parseLong(online)));
                        } else {
                            timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    ChatActivity.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            appBarSeen.setText("Last Seen: " + getTimeAgo(Long.parseLong(online)));
                                        }
                                    });
                                }
                            }, 2000);
                        }
                    }
                } catch (Exception e) {
                    Log.d(TAG, "setDatabase(): usersOtherUserListener exception: " + e.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "setDatabase(): usersOtherUserListener failed: " + databaseError.getMessage());
            }
        };
        userDatabase.addValueEventListener(userListener);

        //Check if last message is unseen and mark it as seen with current timestamp

        chatDatabase = FirebaseDatabase.getInstance().getReference().child("Chat").child(currentUserId).child(otherUserId);
        chatListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChild("seen")) {
                        long seen = (long) dataSnapshot.child("seen").getValue();

                        if (seen == 0) {
                            chatDatabase.child("seen").setValue(ServerValue.TIMESTAMP);
                        }
                    }
                } catch (Exception e) {
                    Log.d(TAG, "setDatabase(): chatCurrentUserListener exception: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "setDatabase(): chatCurrentUserListener failed: " + databaseError.getMessage());
            }
        };
        chatDatabase.addValueEventListener(chatListener);
    }

    private void loadMessages() {
        messagesList.clear();

        // Load/Update all messages between current and other user

        messagesDatabase = FirebaseDatabase.getInstance().getReference().child("Messages").child(currentUserId).child(otherUserId);
        messagesListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    Message message = dataSnapshot.getValue(Message.class);

                    messagesList.add(message);
                    messagesAdapter.notifyDataSetChanged();

                    recyclerView.scrollToPosition(messagesList.size() - 1);
                } catch (Exception e) {
                    Log.d(TAG, "loadMessages(): messegesListener exception: " + e.getMessage());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                messagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                messagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                messagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadMessages(): messegesListener failed: " + databaseError.getMessage());
            }
        };
        messagesDatabase.addChildEventListener(messagesListener);
    }

    private void removeListeners() {
        try {
            chatDatabase.removeEventListener(chatListener);
            chatListener = null;

            userDatabase.removeEventListener(userListener);
            userListener = null;

            messagesDatabase.removeEventListener(messagesListener);
            messagesListener = null;
        } catch (Exception e) {
            Log.d(TAG, "exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        sendButton.setEnabled(false);
        message = messageEditText.getText().toString();
        if (message.length() == 0) {
            Toast.makeText(getApplicationContext(), "Message cannot be empty", Toast.LENGTH_SHORT).show();
            sendButton.setEnabled(true);
        } else {
            messageEditText.setText("");

            // Pushing message/notification so we can get keyIds

            DatabaseReference userMessage = FirebaseDatabase.getInstance().getReference().child("Messages").child(currentUserId).child(otherUserId).push();
            String pushId = userMessage.getKey();

            DatabaseReference notificationRef = FirebaseDatabase.getInstance().getReference().child("Notifications").child(otherUserId).push();
            String notificationId = notificationRef.getKey();

            // "Packing" message

            SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

            Map messageMap = new HashMap();
            messageMap.put("message", message);
            messageMap.put("name",sharedpreferences.getString("username",""));
            messageMap.put("type", "text");
            messageMap.put("from", currentUserId);
            messageMap.put("to", otherUserId);
            messageMap.put("timestamp", ServerValue.TIMESTAMP);

            HashMap<String, String> notificationData = new HashMap<>();
            notificationData.put("from", currentUserId);
            notificationData.put("type", "message");

            Map userMap = new HashMap();
            userMap.put("Messages/" + currentUserId + "/" + otherUserId + "/" + pushId, messageMap);
            userMap.put("Messages/" + otherUserId + "/" + currentUserId + "/" + pushId, messageMap);

            userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/message", message);
            userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/timestamp", ServerValue.TIMESTAMP);
            userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/seen", ServerValue.TIMESTAMP);

            userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/message", message);
            userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/timestamp", ServerValue.TIMESTAMP);
            userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/seen", 0);

            userMap.put("Notifications/" + otherUserId + "/" + notificationId, notificationData);

            // Updating database with the new data including message, chat and notification

            FirebaseDatabase.getInstance().getReference().updateChildren(userMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    sendButton.setEnabled(true);
                    if (databaseError != null) {
                        Log.d(TAG, "sendMessage(): updateChildren failed: " + databaseError.getMessage());
                    }
                }
            });
        }
    }

    private void sendFCMPush() {
        JSONObject obj = null;
        JSONObject objData = null;
        JSONObject dataobjData = null;
        // TO get device token of other user id
        Log.d("usersisis", otherUserId);
        FirebaseDatabase.getInstance().getReference().child("Users").child(otherUserId).getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                otherdeviceidtoken = dataSnapshot.child("token").getValue().toString();
                otherdeviceidimage = dataSnapshot.child("token").getValue().toString();
                Log.d("tokeoisios", otherdeviceidtoken);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to read app title value.", databaseError.toException());
            }
        });

        //-------get value for current user*---

        FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId).getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                curr_otherdeviceidtoken = dataSnapshot.child("token").getValue().toString();
                curr_currentuseridname = dataSnapshot.child("name").getValue().toString();
                Log.d("othertokenis", curr_otherdeviceidtoken);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to read app title value.", databaseError.toException());
            }
        });

        Log.d("Fgdfgfdgdfsg", otherUserId);

        Log.d("tokeisiisn", astro_token);
        try {
            obj = new JSONObject();
            objData = new JSONObject();
            objData.put("body", message);
            objData.put("title", curr_currentuseridname);
            objData.put("sound", "default");
            objData.put("icon", "http://gintonico.com/content/uploads/2015/03/fontenova.jpg"); //   icon_name image must be there in drawable
            objData.put("tag", otherdeviceidtoken);
            objData.put("priority", "high");
            dataobjData = new JSONObject();
            dataobjData.put("text", message);
            dataobjData.put("title", curr_currentuseridname);
            dataobjData.put("message", message);
            dataobjData.put("type", "chat");
            obj.put("to", otherdeviceidtoken);
            //obj.put("priority", "high");
            obj.put("notification", objData);
            obj.put("data", dataobjData);
            Log.d("mapdatausus", obj.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, "https://fcm.googleapis.com/fcm/send", obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("jsonresppnsis", response + "");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("errorisis", error.getMessage() + "");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "key=" + Legacy_SERVER_KEY);
                params.put("Content-Type", "application/json");
                Log.d("headerparams", params + "");
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        int socketTimeout = 1000 * 60;// 60 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        requestQueue.add(jsObjRequest);
    }




    private void sendMessage1() {
        progressDialog.show();
        sendButton.setEnabled(false);
        message = messageEditText.getText().toString();
        if (message.length() == 0) {
            Toast.makeText(getApplicationContext(), "Message cannot be empty", Toast.LENGTH_SHORT).show();
            sendButton.setEnabled(true);
        } else {
            messageEditText.setText("");

            // Pushing message/notification so we can get keyIds

            DatabaseReference userMessage = FirebaseDatabase.getInstance().getReference().child("Messages").child(currentUserId).child(otherUserId).push();
            String pushId = userMessage.getKey();

            DatabaseReference notificationRef = FirebaseDatabase.getInstance().getReference().child("Notifications").child(otherUserId).push();
            String notificationId = notificationRef.getKey();

            // "Packing" message

            SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

            Map messageMap = new HashMap();
            messageMap.put("message", message);
            messageMap.put("name",sharedpreferences.getString("username",""));
            messageMap.put("type", "text");
            messageMap.put("from", currentUserId);
            messageMap.put("to", otherUserId);
            messageMap.put("timestamp", ServerValue.TIMESTAMP);

            HashMap<String, String> notificationData = new HashMap<>();
            notificationData.put("from", currentUserId);
            notificationData.put("type", "message");

            Map userMap = new HashMap();
            userMap.put("Messages/" + currentUserId + "/" + otherUserId + "/" + pushId, messageMap);
            userMap.put("Messages/" + otherUserId + "/" + currentUserId + "/" + pushId, messageMap);

            userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/message", message);
            userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/timestamp", ServerValue.TIMESTAMP);
            userMap.put("Chat/" + currentUserId + "/" + otherUserId + "/seen", ServerValue.TIMESTAMP);

            userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/message", message);
            userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/timestamp", ServerValue.TIMESTAMP);
            userMap.put("Chat/" + otherUserId + "/" + currentUserId + "/seen", 0);

            userMap.put("Notifications/" + otherUserId + "/" + notificationId, notificationData);

            // Updating database with the new data including message, chat and notification

            FirebaseDatabase.getInstance().getReference().updateChildren(userMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    sendButton.setEnabled(true);
                    progressDialog.dismiss();
                    if (databaseError != null) {
                        Log.d(TAG, "sendMessage(): updateChildren failed: " + databaseError.getMessage());
                    }
                    sendFCMPush1();
                }
            });
        }
    }



    private void sendFCMPush1() {
        JSONObject obj = null;
        JSONObject objData = null;
        JSONObject dataobjData = null;
        // TO get device token of other user id
        Log.d("usersisis", otherUserId);
        progressDialog.show();
        FirebaseDatabase.getInstance().getReference().child("Users").child(otherUserId).getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                otherdeviceidtoken = dataSnapshot.child("token").getValue().toString();
                otherdeviceidimage = dataSnapshot.child("token").getValue().toString();
                Log.d("tokeoisios", otherdeviceidtoken);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to read app title value.", databaseError.toException());
                progressDialog.dismiss();
                id1 = id;
                chat_id1 = chat_id;
                cdt.onFinish();
                cdt.cancel();
                check_timmer=false;
                timmer.setVisibility(View.GONE);
                chatFeedback.setVisibility(View.VISIBLE);
                ratting=true;
                chathistory1();
            }
        });

        //-------get value for current user*---

        FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId).getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                curr_otherdeviceidtoken = dataSnapshot.child("token").getValue().toString();
                curr_currentuseridname = dataSnapshot.child("name").getValue().toString();
                Log.d("othertokenis", curr_otherdeviceidtoken);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to read app title value.", databaseError.toException());
                progressDialog.dismiss();
                id1 = id;
                chat_id1 = chat_id;
                cdt.onFinish();
                cdt.cancel();
                timmer.setVisibility(View.GONE);
                chatFeedback.setVisibility(View.VISIBLE);
                chathistory1();
            }
        });

        Log.d("Fgdfgfdgdfsg", otherUserId);

        Log.d("tokeisiisn", astro_token);
        try {
            obj = new JSONObject();
            objData = new JSONObject();
            objData.put("body", message);
            objData.put("title", curr_currentuseridname);
            objData.put("sound", "default");
            objData.put("icon", "http://gintonico.com/content/uploads/2015/03/fontenova.jpg"); //   icon_name image must be there in drawable
            objData.put("tag", otherdeviceidtoken);
            objData.put("priority", "high");
            dataobjData = new JSONObject();
            dataobjData.put("text", message);
            dataobjData.put("title", curr_currentuseridname);
            dataobjData.put("message", message);
            dataobjData.put("type", "chat");
            obj.put("to", otherdeviceidtoken);
            //obj.put("priority", "high");
            obj.put("notification", objData);
            obj.put("data", dataobjData);
            Log.d("mapdatausus", obj.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, "https://fcm.googleapis.com/fcm/send", obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("jsonresppnsis", response + "");
                        id1 = id;
                        chat_id1 = chat_id;
                        cdt.onFinish();
                        cdt.cancel();
                        timmer.setVisibility(View.GONE);
                        chatFeedback.setVisibility(View.VISIBLE);
                        chathistory1();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("errorisis", error.getMessage() + "");
                        progressDialog.dismiss();
                        id1 = id;
                        chat_id1 = chat_id;
                        cdt.onFinish();
                        cdt.cancel();
                        timmer.setVisibility(View.GONE);
                        chatFeedback.setVisibility(View.VISIBLE);
                        chathistory1();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "key=" + Legacy_SERVER_KEY);
                params.put("Content-Type", "application/json");
                Log.d("headerparams", params + "");
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        int socketTimeout = 1000 * 60;// 60 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        requestQueue.add(jsObjRequest);
    }




    private String getTimeAgo(long time) {
        final long diff = System.currentTimeMillis() - time;
        Log.d("skldjkldfs", String.valueOf(diff));

        if (diff < 1) {
            return " just now";
        }
        if (diff < 60 * 1000) {
            if (diff / 1000 < 2) {
                return diff / 1000 + " second ago";
            } else {
                return diff / 1000 + " seconds ago";
            }
        } else if (diff < 60 * (60 * 1000)) {
            if (diff / (60 * 1000) < 2) {
                return diff / (60 * 1000) + " minute ago";
            } else {
                return diff / (60 * 1000) + " minutes ago";
            }
        } else if (diff < 24 * (60 * (60 * 1000))) {
            if (diff / (60 * (60 * 1000)) < 2) {
                return diff / (60 * (60 * 1000)) + " hour ago";
            } else {
                return diff / (60 * (60 * 1000)) + " hours ago";
            }
        } else {
            if (diff / (24 * (60 * (60 * 1000))) < 2) {
                return diff / (24 * (60 * (60 * 1000))) + " day ago";
            } else {
                return diff / (24 * (60 * (60 * 1000))) + " days ago";
            }
        }
    }

    // Sending push notification in android


    public void backd(View view) {
     /*   cdt.onFinish();
        cdt.cancel();
        onBackPressed();*/

        if(ratting)
        {
            Toast.makeText(getApplicationContext(), "Please provide rating to the Expert.", Toast.LENGTH_LONG).show();
        }
        else
        {
            alertBackChat();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        //chathistory();
        Log.d("dswdwdwdwd", "onPause");

        running = false;

        FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId).child("online").setValue(ServerValue.TIMESTAMP);

        if (messagesList.size() > 0 && messageEditText.getText().length() > 0) {
            FirebaseDatabase.getInstance().getReference().child("Chat").child(currentUserId).child(otherUserId).child("typing").setValue(0);
        }

        removeListeners();
    }



    @Override
    protected void onStop() {
        super.onStop();
//        Toast.makeText(this, "app minimise", Toast.LENGTH_SHORT).show();
        alertBackChat();
//
//        Intent startMain = new Intent(Intent.ACTION_MAIN);
//        startMain.addCategory(Intent.CATEGORY_HOME);
//        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(startMain);

//        ChatActivity.this.moveTaskToBack(true);

        Log.d("dswdwdwdwd", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        alertBackChat();
//        Log.d("dswdwdwdwd", "onDestroy");
        //chathistory();
    }

    public void endchat(View view) {

        if(ratting)
        {
            Toast.makeText(getApplicationContext(), "Please provide rating to the Expert.", Toast.LENGTH_LONG).show();
        }
        else
        {
            alertBackChat();
        }

    }



    //////Ratting Chat


    private void review() {

        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

        final JSONObject json1 = new JSONObject();
        try {
            json1.put("user_id",sharedpreferences.getString("id", ""));
            json1.put("vendor_id",id);
            json1.put("listing_id","1");
            json1.put("child_cat_id","1");
            json1.put("star",rateing);
            json1.put("comments",review);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.ADDREVIEW;
        Log.i("gdfgvgbnn", url+json1.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ratting=false;
                            Log.d("ghsdsdasdasdsdag", response.toString());

                            JSONObject jsonObject = new JSONObject(String.valueOf(response));

                            Toast.makeText(getApplicationContext(), "Your rating submited successfully.", Toast.LENGTH_LONG).show();

                            Intent chatIntent = new Intent(ChatActivity.this, HomeActivity.class);
                            chatIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            chatIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(chatIntent);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            ratting=false;
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something went worng with API", Toast.LENGTH_LONG).show();
                ratting=false;
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

    public void getdata(){
        review=etreview.getText().toString();
        Float ratingvalue = ratebar.getRating();
        tvrate.setText(""+ ratingvalue);
        rateing=tvrate.getText().toString();
        if(rateing.toString().equals("0.0") || tvrate.getText().toString()==null)
        {
            Toast.makeText(this, "Please give rating to astrologer.", Toast.LENGTH_SHORT).show();
        }
        else
        {

            review();
        }

    }


    private void chathistory1() {
        progressDialog.show();
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

//        if(currentDateAndTime.isEmpty() || currentDateAndTime==null)
//        {
//            currentDateAndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        }
//        else
//        {
//            //do nothing
//        }


        currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

        final String URLALL=RootURL.Base_URL+"/Astroksbmadmin/user/chat_out.php?chat_id="+chat_id1+"&out_time="+currentDateAndTime;

        Log.d("ABCsds",URLALL);
        StringRequest request=new StringRequest(Request.Method.GET,URLALL.replaceAll(" ","%20"),
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        Log.d("ABddsdsCsds",response.toString());

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(String.valueOf(response));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(getApplicationContext(), "No internet connection"+error, Toast.LENGTH_LONG).show();

                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //image=getStringImage(bitmap);

                //Log.i("image", image);

                Map<String, String> params = new Hashtable<String, String>();


                return params;
            }
        };
        requestQueue.add(request);

    }


    /////Ratting Caht





    private void chathistory() {
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());


//        if(currentDateAndTime.isEmpty() || currentDateAndTime==null)
//        {
//            currentDateAndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        }
//        else
//        {
//            //do nothing
//        }

        currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

        final String URLALL = RootURL.Base_URL+"/Astroksbmadmin/user/chat_out.php?chat_id=" + chat_id + "&out_time=" + currentDateAndTime;
        Log.d("ABCsds", URLALL);
        StringRequest request = new StringRequest(Request.Method.GET, URLALL.replaceAll(" ", "%20"),
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.d("ABdsCsds", response.toString());
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "No internet connection" + error, Toast.LENGTH_LONG).show();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //image=getStringImage(bitmap);

                //Log.i("image", image);

                Map<String, String> params = new Hashtable<String, String>();


                return params;
            }
        };
        requestQueue.add(request);

    }


    private void timerStart(String timing,int Sec) {

        ChatStarted="Yes";

        float x = Float.parseFloat(timing);
        int y = (int) x;

        long timervalue = 0;

        check_timmer=true;

        if(Sec==0)
        {
            timervalue = (Long.parseLong(String.valueOf(y)) * 60000);
        }
        else
        {
            long sec1 = Long.parseLong(String.valueOf(Sec*1000));
            // tv_timer.setVisibility(View.VISIBLE);
            //////////
            timervalue = (Long.parseLong(String.valueOf(y)) * 60000) + sec1;
        }

        Log.d("checktme", String.valueOf(timervalue));
        cdt = new CountDownTimer(timervalue, 1000) {

            public void onTick(long leftTimeInMilliseconds) {
                int dseconds = (int) (leftTimeInMilliseconds / 1000) % 60;
                int dminutes = (int) ((leftTimeInMilliseconds / (1000 * 60)) % 60);
                int dhours   = (int) ((leftTimeInMilliseconds / (1000*60*60)) % 24);

                timer_st = String.format("%02d", dminutes + (dhours*60)) + ":" + String.format("%02d", dseconds);
                //tv_timer.setText(timer_st);
                Log.d("timersisis", timer_st);

                if(dhours!=0)
                {
                    if(dminutes1!=0 && dminutes1!=dminutes)
                    {
                  //      walletdeduct();
                    }
                    dminutes1=dminutes;
                }

                timmer.setText("(" + timer_st + " min)");
                Log.d("dfdsfsdfsdf", timer_st);

//                if (timer_st.equals("35:59")) {
//                    walletdeduct();
//                } else if (timer_st.equals("34:59")) {
//                    walletdeduct();
//                } else if (timer_st.equals("33:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("32:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("31:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("30:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("29:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("28:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("27:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("26:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("25:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("24:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("23:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("22:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("21:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("20:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("19:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("18:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("17:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("16:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("15:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("14:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("13:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("12:59")) {
//
//                    walletdeduct();
//
//                } else if (timer_st.equals("11:59")) {
//                    walletdeduct();
//                } else if (timer_st.equals("10:59")) {
//                    walletdeduct();
//                } else if (timer_st.equals("09:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("08:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("07:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("06:59")) {
//                    walletdeduct();
//
//                } else if (timer_st.equals("05:59")) {
//                    walletdeduct();
//
//                } else
//
//
                if (timer_st.equals("04:59")) {
                    //recharebox.setVisibility(View.VISIBLE);
                    //walletdeduct1();

                } else if (timer_st.equals("03:59")) {
                    //recharebox.setVisibility(View.VISIBLE);
                    //walletdeduct1();
                } else
                    if (timer_st.equals("02:59")) {
                    Toast.makeText(ChatActivity.this, "Wallet Balance is runing out please recharge to continue", Toast.LENGTH_SHORT).show();
                    //recharebox.setVisibility(View.VISIBLE);
                    //walletdeduct1();
                } else if (timer_st.equals("01:59")) {
                    //recharebox.setVisibility(View.VISIBLE);
                    //walletdeduct1();
                     Toast.makeText(ChatActivity.this, "Wallet Balance is runing out please recharge to continue", Toast.LENGTH_SHORT).show();
                } else if (timer_st.equals("00:59")) {
                        //recharebox.setVisibility(View.VISIBLE);
                } else if (timer_st.equals("00:00")) {
                        //recharebox.setVisibility(View.VISIBLE);
                        chat_relative.setVisibility(View.GONE);
//                        cdt.cancel();
//
//                        walletdeduct();
//                        chathistory();
//                        updateRequestStatus();
//
//                        endChat();



                        if (cdt != null) {
                            cdt.onFinish();
                            cdt.cancel();
                        }

                        walletdeduct();
//                        fetchServerTime();
                        chathistory();
                        updateRequestStatus();

                        endChat();



                    }

                //Log.e("Datat","::"+timer_st);
                String BROADCAST_ACTION = "timer";
                Intent retIntent = new Intent(BROADCAST_ACTION);
                //  Intent retIntent = new Intent(ServiceReturnIntent);
                retIntent.putExtra("retvalue", timer_st);
                sendBroadcast(retIntent);
                // Toast.makeText(getBaseContext(),"::",Toast.LENGTH_LONG).show();
            }

            public void onFinish() {
                //Toast.makeText(getBaseContext(),"Now Time is up you have to recharage your account",Toast.LENGTH_LONG).show();
                chat_relative.setVisibility(View.GONE);
                cdt.cancel();
                //noanswerapi();
                //       tv_timer.setText("00:00:00");
            }
        };
        cdt.start();
        /////////

    }

    private void walletdeduct() {

//        if(currentDateAndTime.isEmpty() || currentDateAndTime==null)
//        {
//            currentDateAndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        }
//        else
//        {
//            //do nothing
//        }

        currentDateAndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());


       // currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        Log.e("DateTime",currentDateAndTime);
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        int a = Integer.parseInt(chatcommision);
        int b = Integer.parseInt(chatprice);
        int c = a + b;
        progressDialog.show();
        // Toast.makeText(this, "rajput", Toast.LENGTH_SHORT).show();
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("user_id", sharedpreferences.getString("id", ""));
            json1.put("astro_id", id);
            json1.put("amount", c);
            json1.put("commission", chatcommision);
            json1.put("astro_amount", chatprice);
            json1.put("chat_id", chat_id);
            json1.put("chat_call", "1");
            json1.put("end_time", currentDateAndTime);
            json1.put("transid", sharedPreference.getString("transd_id", ""));
            Log.d("swwdwdwd", json1.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.CHATWALLETDEDUCT;

        //Toast.makeText(this, url+" $$$$ " + json1.toString(), Toast.LENGTH_LONG).show();
        Log.i("gdsdasdasdfgvgbnn", url + json1.toString());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            ChatStarted="No";
                            Log.d("swwdwdwd12213", response.toString());
                            progressDialog.dismiss();
                            //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                            Log.d("ghsdsdaserererdasdsdag", response.toString());
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            if (jsonObject.getString("status").equals("1")) {
                                wallet_status = true;

                                String Wallet = jsonObject.getString("updated_wallet").toString();
                                Wallet=Wallet.substring(0, Wallet.length()-3);
                                int av = Integer.parseInt(Wallet);
                                Log.d("checkupdatewalet", String.valueOf(av));
                                if (av <= 0) {
                                    Toast.makeText(ChatActivity.this, "You have to recharge your wallet", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), RechargeWallet.class);
//                                    startActivity(intent);
//                                    finish();
                                } else {
                                    Toast.makeText(ChatActivity.this, "Your Balance left : " + jsonObject.getString("updated_wallet"), Toast.LENGTH_SHORT).show();
//                                    Intent chatIntent = new Intent(ChatActivity.this, ADDRatting.class);
//                                    chatIntent.putExtra("id", id);
//                                    chatIntent.putExtra("chat_id", chat_id);
//                                    startActivity(chatIntent);
                                }
                            } else {
                                wallet_status = false;
                                Toast.makeText(ChatActivity.this, "Your Have not Balance Now: " + jsonObject.getString("updated_wallet"), Toast.LENGTH_SHORT).show();
//                                Intent chatIntent = new Intent(ChatActivity.this, ADDRatting.class);
//                                chatIntent.putExtra("id", id);
//                                chatIntent.putExtra("chat_id", chat_id);
//                                startActivity(chatIntent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
//                            Intent chatIntent = new Intent(ChatActivity.this, ADDRatting.class);
//                            chatIntent.putExtra("id", id);
//                            chatIntent.putExtra("chat_id", chat_id);
//                            startActivity(chatIntent);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                progressDialog.dismiss();
//                Intent chatIntent = new Intent(ChatActivity.this, ADDRatting.class);
//                chatIntent.putExtra("id", id);
//                chatIntent.putExtra("chat_id", chat_id);
//                startActivity(chatIntent);
                Log.i("Dfsdfsdf", error.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //imageEncoded=getStringImage(bitmap);
                //Log.i("image", encodedImageList.toString());
                Map<String, String> params = new Hashtable<String, String>();
//                params.put("imagefile", encodedImageList.toString());
//                 image = getStringImage(bitmap);
//                  System.out.print("Check" + image);
//                params.put("mobile", "9799569458");
//                 params.put("",json1.toString());
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);

    }



    private void walletdeduct1() {
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        int a = Integer.parseInt(chatcommision);
        int b = Integer.parseInt(chatprice);
        int c = a + b;

        progressDialog.show();

        // Toast.makeText(this, "rajput", Toast.LENGTH_SHORT).show();
        final JSONObject json1 = new JSONObject();
        try {
            json1.put("user_id", sharedpreferences.getString("id", ""));
            json1.put("astro_id", id);
            json1.put("amount", c);
            json1.put("commission", chatcommision);
            json1.put("astro_amount", chatprice);
            json1.put("chat_id", chat_id);
            json1.put("chat_call", "1");
            json1.put("end_time", currentDateAndTime);
            json1.put("transid", sharedPreference.getString("transd_id", ""));
            Log.d("swwdwdwd", json1.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String url = RootURL.CHATWALLETDEDUCT;

        //Toast.makeText(this, url + json1.toString(), Toast.LENGTH_LONG).show();
        Log.i("gdsdasdasdfgvgbnn", url + json1.toString());
        ProgressDialog finalProgressDialog = progressDialog;
        ProgressDialog finalProgressDialog1 = progressDialog;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            finalProgressDialog.dismiss();
                            //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                            Log.d("ghsdsdaserererdasdsdag", response.toString());
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            if (jsonObject.getString("status").equals("1")) {
                                wallet_status = true;
                                String Wallet = jsonObject.getString("updated_wallet").toString();
                                Wallet=Wallet.substring(0, Wallet.length()-3);
                                int av = Integer.parseInt(Wallet);
                                Log.d("checkupdatewalet", String.valueOf(av));
                                if (av <= 0) {
                                    Toast.makeText(ChatActivity.this, "You have to recharge your wallet", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), RechargeWallet.class);
//                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(ChatActivity.this, "Your Balance left : " + jsonObject.getString("updated_wallet"), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                wallet_status = false;
                                Toast.makeText(ChatActivity.this, "Your Have not Balance Now: " + jsonObject.getString("updated_wallet"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            finalProgressDialog.dismiss();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                finalProgressDialog1.dismiss();

                Log.i("Dfsdfsdf", error.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //imageEncoded=getStringImage(bitmap);
                //Log.i("image", encodedImageList.toString());
                Map<String, String> params = new Hashtable<String, String>();
//                params.put("imagefile", encodedImageList.toString());
//                 image = getStringImage(bitmap);
//                  System.out.print("Check" + image);
//                params.put("mobile", "9799569458");
//                 params.put("",json1.toString());
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);

    }


    private void fetchServerTime() {
        //RequestQueue initialized

        ishit=true;
        mRequestQueue = Volley.newRequestQueue(this);
        String url= RootURL.FETCHCServerTime;

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    currentDateAndTime="";
                    Log.d("ghsdsdasdasdsdag", response.toString());

                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    String DateTime = jsonObject.getString("datetime");
                    currentDateAndTime = DateTime;
                    LocalDataBase.currentDateTimeL=currentDateAndTime;
                    ishit=false;

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    //Do something after 2 second
//                                    refresh();
//                                }
//                            }, 2000);
//                        }
//                    });


                    walletdeduct();
//                    fetchServerTime();

                } catch (Exception e) {
                    e.printStackTrace();
                    currentDateAndTime="";
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //   Toast.makeText(RegisterUser1.this, error.toString(), Toast.LENGTH_LONG).show();
//                Log.i(TAG,"Error :" + error.toString());
                currentDateAndTime="";
            }
        });

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(ChatActivity.this).add(mStringRequest);
        mRequestQueue.add(mStringRequest);
    }




    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.camera, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        TextView camera = dialogView.findViewById(R.id.camera);
        TextView gallery = dialogView.findViewById(R.id.gallery);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String permission = Manifest.permission.CAMERA;
                int res = getApplicationContext().checkCallingOrSelfPermission(permission);
                if(res == PackageManager.PERMISSION_GRANTED)
                {

                    String permission1 = Manifest.permission.READ_EXTERNAL_STORAGE;
                    int res1 = getApplicationContext().checkCallingOrSelfPermission(permission1);
                    if(res1 == PackageManager.PERMISSION_GRANTED)
                    {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 0);
                        alertDialog.dismiss();
                    }
                    else
                    {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(ChatActivity.this);
                        builder1.setMessage("Please allow storage permission and try again.");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton(
                                "Go to settings",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        startActivityForResult(new Intent(Settings.ACTION_APPLICATION_SETTINGS), 0);
                                        dialog.cancel();
                                    }
                                });

                        builder1.setNegativeButton(
                                "Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }

                }
                else
                {

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ChatActivity.this);
                    builder1.setMessage("Please allow camera and storage permission and try again.");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Go to settings",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivityForResult(new Intent(Settings.ACTION_APPLICATION_SETTINGS), 0);
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, 0);




            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String permission = Manifest.permission.READ_EXTERNAL_STORAGE;
                int res = getApplicationContext().checkCallingOrSelfPermission(permission);
                if(res == PackageManager.PERMISSION_GRANTED)
                {
                    Intent galleryIntent = new Intent();
                    galleryIntent.setType("image/*");
                    galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(galleryIntent, "Select Image"), 1);
                    alertDialog.dismiss();
                }
                else
                {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ChatActivity.this);
                    builder1.setMessage("Please allow storage permission and try again.");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Go to settings",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivityForResult(new Intent(Settings.ACTION_APPLICATION_SETTINGS), 0);
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        });
    }

    private void getNewRequest(String strin,int sec) {
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        String userid = sharedpreferences.getString("id", "");
        Log.d("dsdsffefef", "swswsws=" + sharedpreferences.getString("id", ""));
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("CurrentRequest");//.child(userid);
        Query query = reference.orderByChild("sid").equalTo(userid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // mChat.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    try {
                        /*GroupChatModel model = new GroupChatModel();
                        model.setId(ds.getKey());
                        model.setSender_name(ds.child("sender_name").getValue().toString());*/

                        Log.d("dwsdwdwd", "=" + ds.child("name").getValue().toString());
                        String name = ds.child("name").getValue().toString();
                        String date = ds.child("date").getValue().toString();
                        astro_id = ds.child("rid").getValue().toString();
                        String sid = ds.child("sid").getValue().toString();
                        chat_reqiest_status = ds.child("status").getValue().toString();
                        String image = ds.child("pic").getValue().toString();
                        String mesg = ds.child("msg").getValue().toString();
                        Log.d("timeinminuts", "=" + sid);

                    } catch (NullPointerException e) {
                        Log.d("resookdkde", "=" + e.getMessage());
                    }
                }

                if (chat_reqiest_status.equals("Accept") || chat_reqiest_status.equals("Start") || chat_reqiest_status.equals("Continue")) {

                    if(!check_timmer)
                    {
                        timerStart(strin,sec);
                    }

                } else if (chat_reqiest_status.equals("Pending")) {
                    Toast.makeText(ChatActivity.this, "Chat not started  by astrologer yet ", Toast.LENGTH_SHORT).show();
                } else if (chat_reqiest_status.equals("Reject")) {
                    endChat();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("resookdkde", "=" + databaseError.getDetails());

            }
        });

    }



    private void updateRequestStatus() {
        progressDialog.show();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("CurrentRequest").child(astro_id);
        //Query query1 = reference.orderByChild("admin_id").equalTo(user_id);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot tasksSnapshot) {
                progressDialog.dismiss();
                for (DataSnapshot snapshot : tasksSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    Log.d("keyiis", key);// this key is `K1NRz9l5PU_0CFDtgXz`
                    String path = "/" + key;
                    Log.d("pathhhh", path);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("status", "End");
                    reference.updateChildren(hashMap);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                Log.d("errorisxx", databaseError.getMessage());
            }

        });
    }

    private void alertBackChat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
        builder.setMessage("Do you want End chat? if 'Yes' then press Yes Button");
        builder.setCancelable(false);
        builder.create();
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (cdt != null) {
                    cdt.onFinish();
                    cdt.cancel();
                }

//                fetchServerTime();
                walletdeduct();
                chathistory();
                updateRequestStatus();

                endChat();

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }


    private void endChat() {
//        if (cdt != null) {
//            cdt.onFinish();
//            cdt.cancel();
//        }


        ratting=true;

        if (chat_reqiest_status.equals("Reject")) {
            chathistory();
            updateRequestStatus();
            walletdeduct();
//            fetchServerTime();
        }


        Ratting();
    }

    public void Ratting()
    {
        //progressDialog.show();
        messageEditText.setText("User ended the chat.");
        if (wallet_status == true) {
            sendMessage1();
        } else {
            Toast.makeText(ChatActivity.this, "You have not enough balance!!!", Toast.LENGTH_SHORT).show();
        }
    }




}