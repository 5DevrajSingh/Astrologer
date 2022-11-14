package com.ksbm_astroexpert.Astrologer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.service.quicksettings.Tile;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
import com.google.gson.Gson;
import com.ksbm_astroexpert.Astrologer.CallRequest.Call_request;
import com.ksbm_astroexpert.Chat.MainActivityChat;
import com.ksbm_astroexpert.Chat.activity.ChatActivity;
import com.ksbm_astroexpert.Chat.adapter.MessageAdapter;
import com.ksbm_astroexpert.Chat.models.Message;
import com.ksbm_astroexpert.Chat.utils.ParseManager;
import com.ksbm_astroexpert.Chat.utils.SharedPreference;
import com.ksbm_astroexpert.Constant.LocalDataBase;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.ModelClass.AcceptModel;
import com.ksbm_astroexpert.ModelClass.AcceptModel;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.network.APIClient;
import com.ksbm_astroexpert.network.APIInterface;
import com.ksbm_astroexpert.notification.MyFirebaseMessagingService;
import com.ksbm_astroexpert.ui.Astrodetails.Astrologerdetails;
import com.ksbm_astroexpert.ui.Astrodetails.ModelAstrologerList;
import com.ksbm_astroexpert.ui.Astrodetails.SlidingAstroDetailsLIst;
import com.ksbm_astroexpert.ui.Wallet.RechargeWallet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.ksbm_astroexpert.ui.home.AstroAdapter;
import com.ksbm_astroexpert.ui.home.HomeActivity;
import com.ksbm_astroexpert.ui.utils.UserConstants;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ksbm_astroexpert.Constant.Constant.astroModelList;
import static com.ksbm_astroexpert.network.ApiCallConsent.apiInterface;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;

public class Astrologer extends AppCompatActivity {

    LinearLayout timer_popUp;
    TextView count_down,stateConnected,text1,text2;
    TextView popUpOk;
    CircleImageView popUpAstroProfile;
    TextView popUpAstroName;
    CountDownTimer cTimer = null;
    ScrollView mainLL;


    private APIInterface apiInterface;
    private CompositeDisposable compositeDisposable;



    SharedPreference sharedPreference;
    EditText txt_email, txt_number, txt_ownername, placeofbirth, occupation, martialstatus,City;
    TextView txt_dob, txt_time_dob;
    Button convo;
    int counter = 0;
    CountDownTimer timerCount;
    int count=0;
    private DatabaseReference Adduser_to_inbox;
    String email, otherUserId = "", userbalance, callprice, chatprice, type, message = "",
            country, state, text_gender, marital_status, topic_of_concern;
    String pass = "12345678";
    Context context=this;
    String genderstr = "", concernstr = "", astroid = "";
    Spinner genderspiner, concern;
    String[] gender = {"Select your gender", "Male", "Female", "other"};
    boolean astro_status = true;
    String[] maritalstat = {"Select your Marital status", "Married", "Unmarried", "other"};

    private final String TAG = "CA/WelcomeActivity";
    String id;
    String currentUserId, lang;
    ArrayAdapter adapter_country;

    int pos=0;

    String image;
    public String Legacy_SERVER_KEY = "AIzaSyCRgqSMUElg2B0uG8mY3lEjYlknzs7EJrc";
    public String otherdeviceidtoken = "", currentuseridname, otherdeviceidimage, astroname;

    String[] concernlist = {"Select your topic", "Marriage", "Love and Relationship", "Wealth and proverty", "Education", "legal matters", "Child name consultation",
            "Commodity", "Match macking", "Birth time rectification", "Name correction", "Travel abroad", "remedy", "Health", "Other"};

    String chatcommision, callcommission, astro_token = "";

    private Query messagesDatabase;
    private ChildEventListener messagesListener;

    private MessageAdapter messagesAdapter;
    private final List<Message> messagesList = new ArrayList<>();

    ImageView ivProfile;
    TextView name;
    Button btncancel, btnaccept;
    LinearLayout acceptlayout;
    private ArrayList<String> countryctr = new ArrayList<>();
    private ArrayList<HashMap<String, String>> countryctrlist = new ArrayList<>();
    String countryidstr, countrynamestr = "", statenamestr = "", stateidstr, maritalstatstr;
    Spinner countryspinnser, statespinner, maritalstatusd;
    int userid = 0;
    String user_mobile = "0";

    CountDownTimer caont;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    int country_pos = 0, state_pos = 0, text_gender_pos = 0, marital_status_pos = 0, topic_of_concern_pos = 0;
    private ArrayList<String> statecodeme = new ArrayList<>();
    private ArrayList<HashMap<String, String>> statecodelist = new ArrayList<>();
    ArrayAdapter adpthree, adapter_gender, adapter_marital, adapter_concern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astrologer);

        timer_popUp = findViewById(R.id.timer_popUp);
        mainLL = findViewById(R.id.mainLL);
        count_down = findViewById(R.id.count_down);
        popUpOk = findViewById(R.id.popUpOk);
        stateConnected = findViewById(R.id.stateConnected);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);

        popUpAstroProfile = findViewById(R.id.popUpAstroProfile);
        popUpAstroName = findViewById(R.id.popUpAstroName);

        Adduser_to_inbox = FirebaseDatabase.getInstance().getReference();
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        userid = Integer.valueOf(sharedpreferences.getString("id", "0"));
        user_mobile = sharedpreferences.getString("number", "0");

        otherUserId = getIntent().getStringExtra("userid");
        Log.d("dsssssss", otherUserId);
        chatprice = getIntent().getStringExtra("chatprice");
        callprice = getIntent().getStringExtra("callprice");
        userbalance = getIntent().getStringExtra("userbalance");
        astroid = getIntent().getStringExtra("astroid");
        Log.d("checkastroid", astroid);
        lang = getIntent().getStringExtra("lang");

        pos = getIntent().getIntExtra("position",0);

        Log.d("language",lang);
        image = getIntent().getStringExtra("image");
        astroname = getIntent().getStringExtra("astroname");
        chatcommision = getIntent().getStringExtra("chat_commission");
        callcommission = getIntent().getStringExtra("call_commission");
        id = getIntent().getStringExtra("id");
        Log.d("dddddddd", id);
        type = getIntent().getStringExtra("type");
        astro_token = getIntent().getStringExtra("astro_token");
        sharedPreference=SharedPreference.getInstance(this);
        try {
            currentUserId = FirebaseAuth.getInstance().getUid();
            Log.d("cuswwwrIdswswswsw", currentUserId + ",Otheriserid=" + astroid);
        } catch (Exception e) {
            Log.d("exceptionis", e.getMessage());
        }
        Log.i("fdfgdfgdfgdf", otherUserId);
        txt_email = findViewById(R.id.txt_email);
        txt_number = findViewById(R.id.txt_number);
        txt_ownername = findViewById(R.id.txt_ownername);
        txt_dob = findViewById(R.id.txt_dob);
        maritalstatusd = findViewById(R.id.maritalstatusd);
        txt_time_dob = findViewById(R.id.txt_time_dob);
        placeofbirth = findViewById(R.id.placeofbirth);
        statespinner = findViewById(R.id.statespinner);
        countryspinnser = findViewById(R.id.countryspinnser);
        occupation = findViewById(R.id.occupation);
        concern = findViewById(R.id.concern);
        martialstatus = findViewById(R.id.martialstatus);
        City = findViewById(R.id.city);
        genderspiner = findViewById(R.id.genderspiner);
        convo = findViewById(R.id.convo);
        ivProfile = findViewById(R.id.ivProfile);
        name = findViewById(R.id.name);
        btncancel = findViewById(R.id.btncancel);
        btnaccept = findViewById(R.id.btnaccept);
        acceptlayout = findViewById(R.id.acceptlayout);

        Picasso.get().load(image)
                .placeholder(R.drawable.splash)
                .into(ivProfile);
        name.setText(astroname);


        btnaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendatatpserver();
                acceptlayout.setVisibility(View.GONE);
            }
        });


        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptlayout.setVisibility(View.GONE);
            }
        });


        popUpOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimer();
                timer_popUp.setVisibility(View.GONE);

                mainLL.setVisibility(View.VISIBLE);
                caont.cancel();
                //chathistory();
            }
        });


        txt_time_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog picker;
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(Astrologer.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                Time time = new Time(sHour, sMinute, 0);

                                //little h uses 12 hour format and big H uses 24 hour format
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:mm");

                                //format takes in a Date, and Time is a sublcass of Date
                                String s = simpleDateFormat.format(time);
                                txt_time_dob.setText(s);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        txt_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog picker;
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Astrologer.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txt_dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

      //  CallMultipleToast();
        //Creating the ArrayAdapter instance having the country list
        adapter_marital = new ArrayAdapter(this, android.R.layout.simple_spinner_item, maritalstat);
        adapter_marital.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        maritalstatusd.setAdapter(adapter_marital);

        maritalstatusd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    // ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    //Toast.makeText(getApplicationContext(), maritalstat[position], Toast.LENGTH_LONG).show();
                    maritalstatstr = maritalstat[position];

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Creating the ArrayAdapter instance having the country list
        adapter_gender = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);
        adapter_gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        genderspiner.setAdapter(adapter_gender);


        genderspiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    // ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                   // Toast.makeText(getApplicationContext(), gender[position], Toast.LENGTH_LONG).show();
                    genderstr = gender[position];


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Creating the ArrayAdapter instance having the country list
        adapter_concern = new ArrayAdapter(this, android.R.layout.simple_spinner_item, concernlist);
        adapter_concern.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        concern.setAdapter(adapter_concern);

        concern.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    // ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                   // Toast.makeText(getApplicationContext(), concernlist[position], Toast.LENGTH_LONG).show();
                    concernstr = concernlist[position];

                    Log.i("fdsdfsdfsd", concernstr);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        convo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateEmail(txt_email.getText().toString())
                        && validatetxt_ownername(txt_ownername.getText().toString())
                        && validatetxt_number(txt_number.getText().toString())
                        && validateplaceofbirth(placeofbirth.getText().toString())
                        && validatestate(statenamestr)
                        && validatecountry(countrynamestr)
                        && validateoccupation(occupation.getText().toString())
                        && validatecomodity(concernstr)
                        && validatemartal(martialstatus.getText().toString())
                        && validategender(genderstr)

                ) {

                    // register_user();
                    //calltoastro();
                   /* Intent intent = new Intent(Astrologer.this, MainActivityChat.class);
                    // intent.putExtra("users_mobile",phone);
                    startActivity(intent);*/

                    // login_user();
                    // sendfcmreq();
                    //  loadMessages();

                    // showCustomDialog();
                    Log.d("getNewRequest", "onClick: " + "=" + getNewRequest());
                    if (getNewRequest()) {
                        showCustomDialog();
                    } else {
                        Toast t = Toast.makeText(Astrologer.this, "Astrologer busy another Chat process!!", Toast.LENGTH_LONG);
                        t.setGravity(Gravity.CENTER, 0, 0);
                        t.show();
                    }

                }
            }
        });


        fetchdata();

/*
        timerCount = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish(){
                count++;
                if(count==3){
                    timerCount.cancel();
                }else {
                    timerCount.start();
                    Log.d("fkdsjfkds","theradddddd");
                    sendatatpserver();
                }
            }
        }.start();
*/
       // receiveData();

        //country();

        countryspinnser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    HashMap<String, String> ma = countryctrlist.get(position - 1);
                    countryidstr = ma.get("ii");

                    countrynamestr = ma.get("name").trim();

                    statespinner.setVisibility(View.VISIBLE);
                    statespinnerjson();

                    // Log.d("Check", str_BusinessId);
                    // str_BusinessName = skillsspinnser.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        statespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    HashMap<String, String> ma = statecodelist.get(position - 1);
                    stateidstr = ma.get("ii");
                    statenamestr = ma.get("name").trim();
                    // Log.d("Check", str_BusinessId);
                    // str_BusinessName = skillsspinnser.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        try {
            Bundle bundle;
            bundle = getIntent().getExtras();
            if (bundle != null) {
                String title1 = bundle.getString("title");
                if (title1.equals("Received Accept")) {
//                    chathistory();

                    Intent chatIntent = new Intent(Astrologer.this, ChatActivity.class);

                    SharedPreferences sharedPreferences = getSharedPreferences("RequestDetail", Context.MODE_PRIVATE);

                    if (sharedPreferences.getBoolean("IsAdded", false)) {
                        try {
                            chatIntent.putExtra("userid", sharedPreferences.getString("userId", "").toString());
                            chatIntent.putExtra("userbalance", sharedPreferences.getString("userbanlance", "").toString());
                            chatIntent.putExtra("chatprice", sharedPreferences.getString("chat_price_m", "").toString());
                            chatIntent.putExtra("chattypehistroy", "0");
                            chatIntent.putExtra("id", sharedPreferences.getString("id1", "").toString());
                            chatIntent.putExtra("image", sharedPreferences.getString("image", "").toString());
                            chatIntent.putExtra("astroname", sharedPreferences.getString("astroname", "").toString());
                            chatIntent.putExtra("chat_id", sharedPreferences.getString("chatId", "").toString());
                            chatIntent.putExtra("chat_commission", sharedPreferences.getString("chat_commission", "").toString());
                            chatIntent.putExtra("call_commission", sharedPreferences.getString("call_commission", "").toString());
                            chatIntent.putExtra("astro_token", sharedPreferences.getString("astro_token", "").toString());
                            startActivity(chatIntent);

                        } catch (Exception e) {
                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        chatIntent.putExtra("userid", LocalDataBase.userid11);
                        chatIntent.putExtra("userbalance", LocalDataBase.userbalance11);
                        chatIntent.putExtra("chatprice", LocalDataBase.chatprice11);
                        chatIntent.putExtra("chattypehistroy", "0");
                        chatIntent.putExtra("id", LocalDataBase.id11);
                        chatIntent.putExtra("image", LocalDataBase.image11);
                        chatIntent.putExtra("astroname", LocalDataBase.astroname111);
                        chatIntent.putExtra("chat_id", LocalDataBase.chat_id11);
                        chatIntent.putExtra("chat_commission", LocalDataBase.chat_commission11);
                        chatIntent.putExtra("call_commission", LocalDataBase.call_commission11);
                        chatIntent.putExtra("astro_token", LocalDataBase.astro_token11);
                        startActivity(chatIntent);

                    }

                }

            }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void sendfcmreq() {
        DatabaseReference notificationRef = FirebaseDatabase.getInstance().getReference().child("Notifications").child(otherUserId).push();
        String notificationId = notificationRef.getKey();

        // "Packing" request

        HashMap<String, String> notificationData = new HashMap<>();
        notificationData.put("from", currentUserId);
        notificationData.put("type", "request");

        HashMap map = new HashMap();
        map.put("Requests/" + otherUserId + "/" + currentUserId + "/type", "received");
        map.put("Requests/" + currentUserId + "/" + otherUserId + "/type", "sent");
        map.put("Notifications/" + otherUserId + "/" + notificationId, notificationData);

        // Updating data into database

        FirebaseDatabase.getInstance().getReference().updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Toast.makeText(Astrologer.this, "Please wait for accept your request", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "sendRequest failed: " + databaseError.getMessage());
                }
            }
        });
    }

    private void statespinnerjson() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, RootURL.STATE + "country_id=" + countryidstr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (statecodelist.size() > 0) {
                            statecodelist.clear();
                        }

                        try {
                            JSONObject object = new JSONObject(response);
                            /*if (object.getString("status").equals("ok")) {*/
                            JSONArray jsonArray = object.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                HashMap<String, String> map = new HashMap<>();
                                map.put("name", obj.getString("name"));
                                map.put("ii", obj.getString("id"));
                                statecodelist.add(map);
                            }
                            /* }*/
                            if (statecodeme.size() > 0) {
                                statecodeme.clear();
                            }
                            statecodeme.add("Select your State");
                            for (int i = 0; i < statecodelist.size(); i++) {
                                HashMap<String, String> map = new HashMap<>(statecodelist.get(i));
                                statecodeme.add(map.get("name"));
                            }
                            adpthree = new ArrayAdapter(Astrologer.this, android.R.layout.simple_spinner_item, statecodeme);
                            adpthree.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            statespinner.setAdapter(adpthree);
                            state_pos = adpthree.getPosition(state);
                            statespinner.setSelection(state_pos);

                        } catch (JSONException e) {

                            Toast.makeText(getApplicationContext(), "Error in reading data Try Again !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "Connection Time Out Error Try Again !!", Toast.LENGTH_SHORT).show();
                    }
                }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Astrologer.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }


//    private void fetchdata() {
//        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
//
//        final JSONObject json1 = new JSONObject();
//        try {
//            json1.put("user_id", sharedpreferences.getString("id", ""));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        final String url = RootURL.FETCHCALLINTAKEDORM;
//        Log.i("gdfgvgbnn", url + json1.toString());
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, url, json1,
//                new com.android.volley.Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            Log.d("ghsdsdasdasdsdag", response.toString());
//
//                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
//
//                            JSONArray jsonArray = jsonObject.getJSONArray("records");
//
//                            if(String.valueOf(jsonArray.length()).equals("0"))
//                            {
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    JSONObject obj = jsonArray.getJSONObject(i);
//                                    txt_ownername.setText(obj.getString("firstname"));
//                                    txt_number.setText(obj.getString("phone"));
//                                    txt_dob.setText(obj.getString("dob"));
//                                    txt_time_dob.setText(obj.getString("tob"));
//                                    placeofbirth.setText(obj.getString("city"));
//                                    City.setText(obj.getString("place_birth"));
//                                    txt_email.setText(obj.getString("email"));
//                                    //  martialstatus.setText(obj.getString("marital"));
//                                    occupation.setText(obj.getString("occupation"));
//                                    country = obj.getString("country");
//                                    state = obj.getString("state");
//                                    text_gender = obj.getString("gender");
//                                    marital_status = obj.getString("marital");
//                                    topic_of_concern = obj.getString("topic");
//
//                                    Log.d("countrynameisis", country);
//
//                                    try {
//                                        int pos = adapter_gender.getPosition(text_gender);
//                                        genderspiner.setSelection(pos + 1);
//                                    } catch (Exception e) {
//                                        Log.d("exceptionis1", e.getMessage());
//                                    }
//                                    try {
//                                        int poss2 = adapter_marital.getPosition(marital_status);
//                                        maritalstatusd.setSelection(poss2);
//                                    } catch (Exception e) {
//                                        Log.d("exceptionis2", e.getMessage());
//                                    }
//                                    try {
//                                        int poss3 = adapter_concern.getPosition(topic_of_concern);
//                                        Log.d("adapter_concern-", topic_of_concern + "=" + poss3);
//                                        concern.setSelection(poss3);
//                                    } catch (Exception e) {
//                                        Log.d("exceptionis3", e.getMessage());
//                                    }
//
//
//                                }
//
//                            }
//                            else
//                            {
//
//                            }
//
//
//
//                            country();
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
//
//                Log.i("Dfsdfsdf", error.toString());
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                //imageEncoded=getStringImage(bitmap);
//                //Log.i("image", encodedImageList.toString());
//                Map<String, String> params = new Hashtable<String, String>();
//                //params.put("imagefile", encodedImageList.toString());
//                // image = getStringImage(bitmap);
//                //  System.out.print("Check" + image);
//                //params.put("mobile", "9799569458");
//                // params.put("",json1.toString());
//                return params;
//            }
//        };
//
//        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);
//
//    }

    private void fetchdata() {

        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        String url= RootURL.FETCHCALLINTAKEDORM+"?user_id="+sharedpreferences.getString("id", "");

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    Log.d("ghsdsdasdasdsdag", response.toString());

                    JSONObject jsonObject = new JSONObject(String.valueOf(response));

                    JSONArray jsonArray = jsonObject.getJSONArray("records");

                    if(!String.valueOf(jsonArray.length()).equals("0"))
                    {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            txt_ownername.setText(obj.getString("firstname"));
                            txt_number.setText(obj.getString("phone"));
                            txt_dob.setText(obj.getString("dob"));
                            txt_time_dob.setText(obj.getString("tob"));
                            placeofbirth.setText(obj.getString("city"));
                            City.setText(obj.getString("place_birth"));
                            txt_email.setText(obj.getString("email"));
                            //  martialstatus.setText(obj.getString("marital"));
                            occupation.setText(obj.getString("occupation"));
                            country = obj.getString("country");
                            state = obj.getString("state");
                            text_gender = obj.getString("gender");
                            marital_status = obj.getString("marital");
                            topic_of_concern = obj.getString("topic");

//                            Log.d("countrynameisis", country);
//
                            try {
                                int pos = adapter_gender.getPosition(text_gender);
                                genderspiner.setSelection(pos);
                            } catch (Exception e) {
                                Log.d("exceptionis1", e.getMessage());
                            }
                            try {
                                int poss2 = adapter_marital.getPosition(marital_status);
                                maritalstatusd.setSelection(poss2);
                            } catch (Exception e) {
                                Log.d("exceptionis2", e.getMessage());
                            }
                            try {
                                int poss3 = adapter_concern.getPosition(topic_of_concern);
                                Log.d("adapter_concern-", topic_of_concern + "=" + poss3);
                                concern.setSelection(poss3);
                            } catch (Exception e) {
                                Log.d("exceptionis3", e.getMessage());
                            }

                        }

                    }
                    else
                    {

                    }



                    country();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    country();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                country();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(mStringRequest);
        mRequestQueue.add(mStringRequest);
    }

    private void register_user() {
        //  private String phone,email,pass,name , fullname;
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, "12345678").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                    if (firebaseUser != null) {
                        String userid = firebaseUser.getUid();

                        // "Packing" user data
                        Log.i("dfsfsfsd", userid);


                        Map map = new HashMap<>();
                        map.put("token", FirebaseInstanceId.getInstance().getToken());
                        map.put("name", "Rohit");
                        map.put("email", email);
                        map.put("phone", "9799569458");
                        map.put("status", "Welcome to my Profile!");
                        map.put("image", "default");
                        map.put("cover", "default");
                        map.put("date", ServerValue.TIMESTAMP);

                        // Uploading user data

                        FirebaseDatabase.getInstance().getReference().child("Users").child(userid).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                   // Toast.makeText(Astrologer.this, "sfsfdfsf", Toast.LENGTH_SHORT).show();
                                    //registerButton.setProgress(100);

                                   /* Intent intent = new Intent(Signup.this, OTPClass.class);
                                    intent.putExtra("users_mobile",phone);
                                    startActivity(intent);
                                    finish();*/
                                    login_user();
                                } else {
                                    login_user();
                                    Log.d(TAG, "registerData failed: " + task.getException().getMessage());
                                }
                            }
                        });
                    }
                } else {
                    login_user();
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    Log.d(TAG, "createUser failed: " + task.getException().getMessage());


                }
            }
        });
    }

    private boolean validateEmail(String string) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(txt_email.getText().toString());
        check = m.matches();
        if (txt_email.getText().toString().equals("")) {
            txt_email.setError("Enter Your Email Address");
            return false;
        }
        if (!check) {
            txt_email.setError("Not Valid Email");
        }
        return check;

    }

    private boolean validatetxt_ownername(String string) {
        if (txt_email.getText().toString().equals("")) {
            txt_email.setError("Enter Your Name");
            return false;
        }


        return true;
    }

    private boolean validatetxt_number(String string) {
        if (txt_email.getText().toString().equals("")) {
            txt_email.setError("Enter Your Number");
            return false;
        }


        return true;
    }

    private boolean validateplaceofbirth(String string) {
        if (placeofbirth.getText().toString().equals("")) {
            placeofbirth.setError("Enter Your placeofbirth");
            return false;
        }


        return true;
    }

    private boolean validatestate(String string) {
        if (statenamestr.equals("")) {
            Toast.makeText(this, "Enter Your state", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private boolean validatecountry(String string) {
        if (concernstr.equals("")) {
            Toast.makeText(this, "Enter Your Country", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private boolean validatemartal(String string) {
        if (maritalstatstr.equals("")) {
            Toast.makeText(this, "Enter Your Marital status", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }


    private boolean validatecomodity(String string) {
        if (concernstr.equals("")) {
            Toast.makeText(this, "please enter your topic", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private boolean validategender(String string) {
        if (concernstr.equals("")) {
            Toast.makeText(this, "please select your gender", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private boolean validateoccupation(String string) {
        if (occupation.getText().toString().equals("")) {
            occupation.setError("Enter Your occupation");
            return false;
        }


        return true;
    }

    private void login_user() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String token = FirebaseInstanceId.getInstance().getToken();
                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Log.d("adjkskjl", userid);
                    // Updating user device token

                    FirebaseDatabase.getInstance().getReference().child("Users").child(userid).child("token").setValue(token).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Astrologer.this, MainActivityChat.class);
                                // intent.putExtra("users_mobile",phone);
                                startActivity(intent);
                              /*  if(sharedpreferences.getString("gender","").equals("null") && sharedpreferences.getString("dob","").equals("null") && sharedpreferences.getString("location","").equals("null"))
                                {
                                    Intent intent=new Intent(Login.this,UpdateProfile.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Intent intent=new Intent(Login.this, HomeMainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }*/
                                //  if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified())
                                /*{
                                    // Show animation and start activity

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Your email is not verified, we have sent you a new one.", Toast.LENGTH_LONG).show();
                                    // FirebaseAuth.getInstance().signOut();


                                }*/
                            } else {
                                Log.d(TAG, "uploadToken failed: " + task.getException().getMessage());
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    Log.d(TAG, "signIn failed: " + task.getException().getMessage());


                }
            }
        });
    }

    private void sendatatpserver() {
        String callmetype;
        if (type.equals("call")) {
            callmetype = "2";
        } else {
            callmetype = "1";
        }

        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        final ProgressDialog progress = new ProgressDialog(Astrologer.this);
        progress.setMessage("Please Wait..");
        progress.setCancelable(false);
        progress.show();
        progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progress.dismiss();
            }
        });

        final JSONObject json1 = new JSONObject();
        try {
            json1.put("user_id", sharedpreferences.getString("id", ""));
            json1.put("firstname", txt_ownername.getText().toString());
            json1.put("lastname", "");
            json1.put("countrycode", countryidstr);
            json1.put("phone", txt_number.getText().toString());
            json1.put("email", txt_email.getText().toString());
            json1.put("gender", genderstr);
            json1.put("astro_id", astroid);
            json1.put("chat_call", callmetype);
            json1.put("dob", txt_dob.getText().toString());
            json1.put("tob", txt_time_dob.getText().toString());
            json1.put("city", City.getText().toString());
            json1.put("place_city", placeofbirth.getText().toString());
            json1.put("state", statenamestr);
            json1.put("country", countrynamestr);
            json1.put("country_code", countryidstr);
            json1.put("marital", maritalstatstr);
            json1.put("occupation", occupation.getText().toString());
            json1.put("topic", concernstr);
            json1.put("partner_name", "");
            json1.put("partner_dob", "");
            json1.put("partner_tob", "");
            json1.put("partner_city", "");
            json1.put("partner_state", "");
            json1.put("partner_country", "");
            Log.d("JSONObjectdddfcedfcd", json1 + "");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String URL_PRODUCTS = RootURL.CALLINTAKESUBMIT;
        Log.i("Dfsdfsdfsdf", URL_PRODUCTS + json1);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, URL_PRODUCTS, json1,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progress.dismiss();
                            Log.d("ghcdcdcdg", response.toString());
                            Log.d("Dfsdfsdfds", URL_PRODUCTS + json1.toString());
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            if (jsonObject.getString("status").equals("1")) {
                                JSONObject jsonObject1 = jsonObject.optJSONObject("records");
                                Toast.makeText(Astrologer.this, "Please wait...", Toast.LENGTH_SHORT).show();
                                //editor.putString("online","1")
                                float chatpriced = Float.parseFloat(chatprice);
                                float userbalanced = Float.parseFloat(userbalance);
                                int a = Math.round(userbalanced);
                                int b = Math.round(chatpriced);
                                Log.i("dfsdfsdfsf", String.valueOf(b + "fsdf" + a));


                                convo.setEnabled(false);
                                convo.setAlpha(0.3f);


                                if (a >= b) {
                                    if (type.equals("call")) {
                                        calltoastro();
                                    } else {
                                        hitAcceptApi();
                                        sendRequestForChatToAstoLoger();
                                        sendCurrentRequestForChat();

                                        message = "Name = " + txt_ownername.getText().toString()
                                                + "\nDOB = " + txt_dob.getText().toString() + "\nTOB = " + txt_time_dob.getText().toString() +
                                                "\nCity = " + placeofbirth.getText().toString() + "\nState = " + statenamestr
                                                + "\nCountry = " + countrynamestr + "\n Marrital Status = " + martialstatus.getText().toString()
                                                + "\nOccupation = " + occupation.getText().toString() + "\n Topic = " + concernstr;

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


                                        sendFCMPush();

//// Added by shivam

                                        timer_popUp.setVisibility(View.VISIBLE);
                                        stateConnected.setVisibility(View.VISIBLE);
                                        mainLL.setVisibility(View.GONE);

                                        Picasso.get().load(image)
                                                .placeholder(R.drawable.splash)
                                                .into(popUpAstroProfile);
                                        popUpAstroName.setText(astroname);

                                        text1.setText("You will receive chat from");
                                        text2.setText("Charge will start when yours chat received by astrologer. If your chat not received due to some technical issue . Please try again.");

                                        startTimer();

                                        //start timer function

                                        chathistory1();

                                        countDown();

                                        Log.i("dfsdfdsfds", astroname);


                                    }

                                } else {
                                    rechargewallet();
                                }

                            }
                            else if (jsonObject.getString("status").equals("0"))
                            {
                                Toast.makeText(Astrologer.this, jsonObject.getString("message").toString(), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                Log.i("Dfsdfsdf", error.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                //params.put("imagefile", encodedImageList.toString());
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);


    }



   public void startTimer() {
        cTimer = new CountDownTimer(180000, 1000) {
            public void onTick(long millisUntilFinished) {

                count_down.setText(""+String.format("%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

            }
            public void onFinish() {
                count_down.setText("TimeOut");
            }
        };
        cTimer.start();
    }


    //cancel timer
   public void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }


    public void countDown()
    {
        caont =  new CountDownTimer(180000, 1000) {
            public void onTick(long millisUntilFinished) {
                //call to my UI thread every 2 seconds
                if(MyFirebaseMessagingService.CallRejected)
                {
                    count_down.setText("Caller is busy.");
                    stateConnected.setVisibility(View.GONE);
                    Toast.makeText(Astrologer.this, "Caller is busy, try again later.", Toast.LENGTH_LONG).show();
                    MyFirebaseMessagingService.CallRejected=false;
                    cancelTimer();
//                    timer_popUp.setVisibility(View.GONE);
//                    mainLL.setVisibility(View.VISIBLE);
                    caont.cancel();
                }
                else
                {
                    //Do nothimg
                    stateConnected.setVisibility(View.VISIBLE);
                }

            }
            public void onFinish() {
                //final call to my UI thread after 10 seconds
                count_down.setText("Caller Not responding.");
                stateConnected.setVisibility(View.GONE);
                Toast.makeText(Astrologer.this, "Caller not responding, try again later.", Toast.LENGTH_SHORT).show();
                cancelTimer();
//                timer_popUp.setVisibility(View.GONE);
//                mainLL.setVisibility(View.VISIBLE);
                caont.cancel();
            }
        }.start();
    }


//
//    private void calltoastro1() {
//        Toast.makeText(this, "Please wait for call is in progress...", Toast.LENGTH_SHORT).show();
//        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
//        final ProgressDialog progress = new ProgressDialog(Astrologer.this);
//        progress.setMessage("Please Wait..");
//        progress.setCancelable(false);
//        progress.show();
//        progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialogInterface) {
//                progress.dismiss();
//            }
//        });
//
//        final JSONObject json1 = new JSONObject();
//
//        try {
//            json1.put("user_id", sharedpreferences.getString("id", ""));
//            json1.put("astrologer_user_id", astroid);
//            json1.put("moblie_no", user_mobile);
//            Log.d("JSONObjectdddfcedfcd", json1 + "");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        final String URL_PRODUCTS = RootURL.callAstrolger;
//        Log.i("Dfsdfsdfsdf", URL_PRODUCTS + json1);
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DEPRECATED_GET_OR_POST, URL_PRODUCTS, json1,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject jsonObject) {
//
//                        String status = jsonObject.optString("status");
//                        if (status.equals("1")) {
//                            progress.dismiss();
//                            // JSONObject json = jsonObject.getJSONObject("info");
//                            Toast.makeText(getApplicationContext(), jsonObject.optString("message"), Toast.LENGTH_LONG).show();
//                        } else {
//                            progress.dismiss();
//                            Toast.makeText(getApplicationContext(), jsonObject.optString("message"), Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
//                Log.i("Dfsdfsdf", error.toString());
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new Hashtable<String, String>();
//                //params.put("imagefile", encodedImageList.toString());
//                return params;
//            }
//        };
//        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(200 * 30000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);
//
//
//    }




    private void calltoastro()
    {
        Toast.makeText(this, "Please wait for call is in progress...", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        final ProgressDialog progress = new ProgressDialog(Astrologer.this);
        progress.setMessage("Please Wait..");
        progress.setCancelable(false);
        progress.show();

        progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                progress.dismiss();
            }
        });
        StringRequest stringRequest=new StringRequest(Request.Method.POST, RootURL.callAstrolger, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                progress.dismiss();
                Log.d("responsis",response);
                try {
                    JSONObject jsonpObject=new JSONObject(response);
                    //Toast.makeText(Astrologer.this, response, Toast.LENGTH_SHORT).show();

                    timer_popUp.setVisibility(View.VISIBLE);
                    stateConnected.setVisibility(View.VISIBLE);
                    mainLL.setVisibility(View.GONE);

                    text1.setText("You will receive call from");
                    text2.setText("Charge will start when yours call received by astrologer. If your call not received due to some technical issue . Please try again.");

                    Picasso.get().load(image)
                            .placeholder(R.drawable.splash)
                            .into(popUpAstroProfile);
                    popUpAstroName.setText(astroname);
                    startTimer();
                    //start timer function
                    countDown();

                    if(jsonpObject.get("status").equals("0"))
                    {
                        count_down.setText("Caller is busy.");
                        stateConnected.setVisibility(View.GONE);
                        Toast.makeText(Astrologer.this, "Caller is busy, try again later.", Toast.LENGTH_LONG).show();
                        MyFirebaseMessagingService.CallRejected=false;
                        cancelTimer();
//                    timer_popUp.setVisibility(View.GONE);
//                    mainLL.setVisibility(View.VISIBLE);
                        caont.cancel();
                    }

                    Toast.makeText(Astrologer.this, ""+jsonpObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                Log.d("error",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                map.put("user_id", sharedpreferences.getString("id", ""));
                map.put("astrologer_user_id",astroid);
                map.put("moblie_no",user_mobile);
                Log.d("mapiss",map.toString());
                return map;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy( 200*30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }



private void chathistory(){
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        final String URLALL = RootURL.Base_URL+"/Astroksbmadmin/user/chat_in.php?u_id=" + sharedpreferences.getString("id", "")
                + "&a_id=" + astroid + "&form_id=1" + "&in_time=" + currentDateAndTime + "&message=Chat in time";

        Log.d("ABCsds", URLALL);
        StringRequest request = new StringRequest(Request.Method.GET, URLALL.replaceAll(" ", "%20"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ABddsdsCsds", response.toString());
                        final JSONObject[] jsonObject = {null};
                        try {

                            try {

                                jsonObject[0] = new JSONObject(String.valueOf(response));

                                LocalDataBase.userid11=otherUserId;
                                LocalDataBase.userbalance11= userbalance;
                                LocalDataBase.chatprice11=chatprice;
                                LocalDataBase.id11=id;
                                LocalDataBase.image11=image;
                                LocalDataBase.astroname111=astroname;
                                LocalDataBase.chat_id11=jsonObject[0].getString("chat_id");
                                LocalDataBase.chat_commission11=chatcommision;
                                LocalDataBase.call_commission11=callcommission;
                                LocalDataBase.astro_token11=astro_token;




                                Intent chatIntent = new Intent(Astrologer.this, ChatActivity.class);
                                chatIntent.putExtra("userid", otherUserId);
                                chatIntent.putExtra("userbalance", userbalance);
                                chatIntent.putExtra("chatprice", chatprice);
                                chatIntent.putExtra("chattypehistroy", "0");
                                chatIntent.putExtra("id", id);
                                chatIntent.putExtra("image", image);
                                chatIntent.putExtra("astroname", astroname);
                                chatIntent.putExtra("chat_id", jsonObject[0].getString("chat_id"));
                                chatIntent.putExtra("chat_commission", chatcommision);
                                chatIntent.putExtra("call_commission", callcommission);
                                chatIntent.putExtra("astro_token", astro_token);
                                Log.i("dfsdfsdfs", jsonObject[0].getString("chat_id"));
                                startActivity(chatIntent);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }




                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {

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

    private void chathistory1(){
        SharedPreferences sharedpreferences = getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        final String URLALL = RootURL.Base_URL+"/Astroksbmadmin/user/chat_in.php?u_id=" + sharedpreferences.getString("id", "")
                + "&a_id=" + astroid + "&form_id=1" + "&in_time=" + currentDateAndTime + "&message=Chat in time";

        Log.d("ABCsds", URLALL);
        StringRequest request = new StringRequest(Request.Method.GET, URLALL.replaceAll(" ", "%20"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ABddsdsCsds", response.toString());
                        final JSONObject[] jsonObject = {null};
                        try {

                            try {

//                                jsonObject[0] = new JSONObject(String.valueOf(response));
//                                Intent chatIntent = new Intent(Astrologer.this, ChatActivity.class);
//                                chatIntent.putExtra("userid", otherUserId);
//                                chatIntent.putExtra("userbalance", userbalance);
//                                chatIntent.putExtra("chatprice", chatprice);
//                                chatIntent.putExtra("chattypehistroy", "0");
//                                chatIntent.putExtra("id", id);
//                                chatIntent.putExtra("image", image);
//                                chatIntent.putExtra("astroname", astroname);
//                                chatIntent.putExtra("chat_id", jsonObject[0].getString("chat_id"));
//                                chatIntent.putExtra("chat_commission", chatcommision);
//                                chatIntent.putExtra("call_commission", callcommission);
//                                chatIntent.putExtra("astro_token", astro_token);
//                                Log.i("dfsdfsdfs", jsonObject[0].getString("chat_id"));
//                                startActivity(chatIntent);



                                jsonObject[0] = new JSONObject(String.valueOf(response));

                                LocalDataBase.userid11=otherUserId;
                                LocalDataBase.userbalance11= userbalance;
                                LocalDataBase.chatprice11=chatprice;
                                LocalDataBase.id11=id;
                                LocalDataBase.image11=image;
                                LocalDataBase.astroname111=astroname;
                                LocalDataBase.chat_id11=jsonObject[0].getString("chat_id");
                                LocalDataBase.chat_commission11=chatcommision;
                                LocalDataBase.call_commission11=callcommission;
                                LocalDataBase.astro_token11=astro_token;


                                SharedPreferences sharedPreferences = getSharedPreferences("RequestDetail", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("IsAdded",true);
                                editor.putString("userId",otherUserId);
                                editor.putString("astroid",astroid);
                                editor.putString("image1",image);
                                editor.putString("chatId",LocalDataBase.chat_id11);
                                editor.commit();
                                editor.apply();



                            } catch (Exception e) {
                                e.printStackTrace();
                            }




                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {

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

    public void Backd(View view) {
        getAstrologerList();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Astrologer.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void getAstrologerList() {

        ProgressDialog progressDialog=null;
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();

        //Log.d("sqqss", "doLogin: "+"login dara start here");
        compositeDisposable = new CompositeDisposable();
        APIInterface apiInterface = getApiInterface();
        Single<ModelAstrologerList> signInResponseModelObservable = apiInterface.astrodetails_list();
        ProgressDialog finalProgressDialog = progressDialog;
        signInResponseModelObservable.subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Object o) {
                        finalProgressDialog.dismiss();
                        ModelAstrologerList signInResponseModel = (ModelAstrologerList) o;
                        astroModelList=signInResponseModel.getRecords();
                        //  Log.d("dfsdfsdfsdf","="+new Gson().toJson(signInResponseModel));


                        Intent intent = new Intent(Astrologer.this, SlidingAstroDetailsLIst.class);
                        intent.putExtra("pos",pos);
                        intent.putExtra("astroid",astroModelList.get(pos).getId());
                        intent.putExtra("astro_token",astroModelList.get(pos).getDeviceToken());
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(Astrologer.this, e.toString(), Toast.LENGTH_LONG).show();
                        finalProgressDialog.dismiss();
                        onBackPressed();
                    }
                });

    }

    public APIInterface getApiInterface() {
        return APIClient.getClient(UserConstants.BASE_URL2).create(APIInterface.class);
    }



    private void sendFCMPush() {
        JSONObject obj = null;
        JSONObject objData = null;
        JSONObject dataobjData = null;
        // TO get device token of other user id

        if (otherUserId.equals("0")) {

        } else if (otherUserId.equals(null)) {

        } else {
            FirebaseDatabase.getInstance().getReference().child("Users").child(otherUserId).getRef().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    otherdeviceidtoken = dataSnapshot.child("token").getValue().toString();
                    otherdeviceidimage = dataSnapshot.child("token").getValue().toString();
                    Log.d("checdevicetoken",otherdeviceidtoken);
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
                    otherdeviceidtoken = dataSnapshot.child("token").getValue().toString();
                     currentuseridname = dataSnapshot.child("name").getValue().toString();
                    Log.d("currentdevicetoken",otherdeviceidtoken);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "Failed to read app title value.", databaseError.toException());
                }
            });


            Log.i("Fgdfgfdgdfsg", otherUserId);

            try {
                obj = new JSONObject();
                objData = new JSONObject();

                objData.put("body", message);
                objData.put("title", currentuseridname);
                objData.put("sound", "default");
                objData.put("icon", "http://gintonico.com/content/uploads/2015/03/fontenova.jpg"); //   icon_name image must be there in drawable
                objData.put("tag", otherdeviceidtoken);
                objData.put("priority", "high");
                dataobjData = new JSONObject();
                dataobjData.put("text", message);
                dataobjData.put("title", currentuseridname);
                obj.put("to", otherdeviceidtoken);
                //obj.put("priority", "high");
                obj.put("notification", objData);
                obj.put("data", dataobjData);
                Log.e("!_@rj@_@@_PASS:>", obj.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, "https://fcm.googleapis.com/fcm/send", obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("!_@@_SUCESS", response + "");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("!_@@_Errors--", error + "");
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "key=" + Legacy_SERVER_KEY);
                    params.put("Content-Type", "application/json");
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            int socketTimeout = 1000 * 60;//60 seconds
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsObjRequest.setRetryPolicy(policy);
            requestQueue.add(jsObjRequest);
        }
    }

    private void country() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, RootURL.COUNTRY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (countryctrlist.size() > 0) {
                            countryctrlist.clear();
                        }
                        try {
                            JSONObject object = new JSONObject(response);
                            /*if (object.getString("status").equals("ok")) {*/
                            JSONArray jsonArray = object.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                HashMap<String, String> map = new HashMap<>();
                                map.put("name", obj.getString("name"));
                                map.put("ii", obj.getString("id"));
                                map.put("phonecode", obj.getString("phonecode"));
                                countryctrlist.add(map);
                            }
                            /* }*/
                            if (countryctr.size() > 0) {
                                countryctr.clear();
                            }
                            countryctr.add("Select your country");
                            for (int i = 0; i < countryctrlist.size(); i++) {
                                HashMap<String, String> map = new HashMap<>(countryctrlist.get(i));
                                try {
                                    if (country.equals(map.get("name"))) {
                                        country_pos = i;
                                        Log.d("dwdwdwed", "matched = " + country + " , " + i + " ," + map.get("name"));
                                    }
                                } catch (NullPointerException e) {

                                }
                                countryctr.add(map.get("name"));
                            }
                            adapter_country = new ArrayAdapter(Astrologer.this, android.R.layout.simple_spinner_item, countryctr);
                            adapter_country.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            countryspinnser.setAdapter(adapter_country);

                            Log.d("countrypos", country + "," + adapter_country.getPosition(country));


                            countryspinnser.setSelection(country_pos + 1);


                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error in reading data Try Again !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "Connection Time Out Error Try Again !!", Toast.LENGTH_SHORT).show();
                    }
                }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Astrologer.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialogd, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        TextView netprice = dialogView.findViewById(R.id.astrolang);
        TextView gstamount = dialogView.findViewById(R.id.astoname);
        Button startBTN = dialogView.findViewById(R.id.cancel);
        Button chatting = dialogView.findViewById(R.id.chatting);
        netprice.setText(lang.replaceAll(",",", "));
        gstamount.setText(astroname);

        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendatatpserver();
               // CallMultipleToast();
                alertDialog.dismiss();
            }
        });

        chatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendatatpserver();
                // alertDialog.dismiss();
            }
        });


    }

    private void rechargewallet() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.rechargewallet, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        TextView rechargetitle = dialogView.findViewById(R.id.rechargetitle);
        Button cancel = dialogView.findViewById(R.id.cancel);
        Button recharge = dialogView.findViewById(R.id.recharge);
        int a = Integer.parseInt(chatcommision);
        int b = Integer.parseInt(chatprice);
        int c = a + b;

        rechargetitle.setText("Minmum balance of { INR :" + c + " } is requried to start chat with " + astroname);

        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                // Toast.makeText(Astrologer.this, "You have to Recharge your account", Toast.LENGTH_SHORT).show();
                Intent chatIntent = new Intent(Astrologer.this, RechargeWallet.class);
                startActivity(chatIntent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


    }

    private void loadMessages() {
        messagesList.clear();

        // Load/Update all messages between current and other user
        messagesDatabase = FirebaseDatabase.getInstance().getReference().child("Messages").child(currentUserId).child(otherUserId);
        messagesListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    // Message message = dataSnapshot.getValue(Message.class);
                     acceptlayout.setVisibility(View.VISIBLE);
                    //Toast.makeText(Astrologer.this, "dfsfsfsdf    " +message.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.d(TAG, "loadMessages(): messegesListener exception: " + e.getMessage());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //  messagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //messagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //messagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadMessages(): messegesListener failed: " + databaseError.getMessage());
            }
        };
        messagesDatabase.addChildEventListener(messagesListener);
    }

    private void sendRequestForChatToAstoLoger() {
        Date c = Calendar.getInstance().getTime();
        final String formattedDate = df.format(c);
        String inbox_sender_ref = "Request" + "/" + userid + "/" + astroid;
        String inbox_receiver_ref = "Request" + "/" + astroid + "/" + userid;

        HashMap sendermap = new HashMap<>();
        sendermap.put("rid", userid);
        sendermap.put("name", txt_ownername.getText().toString());
        sendermap.put("pic", "url");
        sendermap.put("msg", "message");
        sendermap.put("status", "pending");
        sendermap.put("timestamp", -1 * System.currentTimeMillis());
        sendermap.put("date", formattedDate);
        Log.d("sendermap", sendermap.toString());

        HashMap receivermap = new HashMap<>();
        receivermap.put("rid", astroid);
        receivermap.put("name", astroname);
        receivermap.put("pic", image);
        receivermap.put("msg", "");
        receivermap.put("status", "");
        receivermap.put("timestamp", -1 * System.currentTimeMillis());
        receivermap.put("date", formattedDate);
        Log.d("sendermap", receivermap.toString());
        HashMap both_user_map = new HashMap<>();
        both_user_map.put(inbox_sender_ref, receivermap);
        both_user_map.put(inbox_receiver_ref, sendermap);
        Adduser_to_inbox.updateChildren(both_user_map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

               /* Chat_Activity.SendPushNotification(rootref,Variables.user_name,message,
                        Variables.user_pic,
                        token,Receiverid,senderid);
                sendNotification(Receiverid, Receiverid, message,Receiverid);*/

            }
        });
    }

    private void sendCurrentRequestForChat() {
        Date c = Calendar.getInstance().getTime();
        final String formattedDate = df.format(c);
        String inbox_sender_ref = "CurrentRequest" + "/" + astroid;

        HashMap receivermap = new HashMap<>();
        receivermap.put("rid", astroid);
        receivermap.put("sid", String.valueOf(userid));
        receivermap.put("name", astroname);
        receivermap.put("pic", image);
        receivermap.put("msg", "Request send to " + astroname);
        receivermap.put("status", "Pending");
        receivermap.put("timestamp", -1 * System.currentTimeMillis());
        receivermap.put("date", formattedDate);
        Log.d("sendermapdwd", receivermap.toString());
        HashMap both_user_map = new HashMap<>();
        both_user_map.put(inbox_sender_ref, receivermap);
        Adduser_to_inbox.updateChildren(both_user_map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                // sendNotification(Receiverid, Receiverid, message,Receiverid);
            }
        });
    }

    public static SimpleDateFormat df =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);


    private boolean getNewRequest() {
        Log.d("astrouidud", "=" + astroid);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("CurrentRequest");//.child(userid);
        Query query = reference.orderByChild("rid").equalTo(astroid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
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
                        String rid = ds.child("rid").getValue().toString();
                        String sid = ds.child("sid").getValue().toString();
                        String status = ds.child("status").getValue().toString();
                        String image = ds.child("pic").getValue().toString();
                        String mesg = ds.child("msg").getValue().toString();
                        long time = dateStatus(date);
                        Log.d("timeinminuts", "=" + time);


                        if (status.equals("Reject") || status.equals("End")) {
                            // request will be sent
                            astro_status = true;
                        } else if (status.equals("Pending")) {
                            if (time > 3) {
                                // request will be sent
                                astro_status = true;
                            } else {
                                //Astrologer are busy another chat procces
                                astro_status = false;
                            }

                        } else {
                            astro_status = false;
                            //Astrologer are busy another chat procces
                        }


                    } catch (NullPointerException e) {
                        Log.d("resookdkde", "=" + e.getMessage());

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("resookdkde", "=" + databaseError.getDetails());

            }
        });

        return astro_status;

    }

    public long dateStatus(String date) {
        long status = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date strDate = null;
        try {
            strDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            long dif = System.currentTimeMillis() - strDate.getTime();
            status = TimeUnit.MILLISECONDS.toMinutes(dif) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(dif));
            // status = TimeUnit.MILLISECONDS.toMinutes(dif);
            Log.d("dwdwdwdw", "=" + status);

        } catch (Exception e) {
            status = 0;
        }


        return status;
    }

    private void hitAcceptApi() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url=RootURL.Base_URL+"/Astroksbmadmin/api/api/accept_chat";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("checkressponse", response);
                try {
                    AcceptModel model = ParseManager.getInstance().fromJSON(response, AcceptModel.class);
                    if (model != null) {
                        if (model.status.equals("200")){
                            String transid=model.result.transid;
                            sharedPreference.putString("transd_id",transid);
                        }else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

               // progressbar.hideProgress();


            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("user_id", String.valueOf(userid));
                hashMap.put("astro_id", astroid);
                Log.d("checkcidddd", String.valueOf(hashMap));
                return hashMap;
            }
        };
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }




    private void hitAstro_UserDetailforAppCloseRequestApi() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url=RootURL.Base_URL+"/Astroksbmadmin/api/api/accept_chat";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("checkressponse", response);
                try {
                    AcceptModel model = ParseManager.getInstance().fromJSON(response, AcceptModel.class);
                    if (model != null) {
                        if (model.status.equals("200")){
                            String transid=model.result.transid;
                            sharedPreference.putString("transd_id",transid);
                        }else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                // progressbar.hideProgress();
            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("userid", LocalDataBase.chatid1);
                hashMap.put("chatprice", LocalDataBase.chat_price_m1);
                hashMap.put("callprice", LocalDataBase.call_price_m1);
                hashMap.put("astroid", LocalDataBase.astroid1);
                hashMap.put("id", LocalDataBase.id1);
                hashMap.put("image", LocalDataBase.image1);
                hashMap.put("lang", LocalDataBase.lang1);
                hashMap.put("userbalance", LocalDataBase.userbanlance1);
                hashMap.put("astroname", LocalDataBase.astroname1);
                hashMap.put("type", "chat");
                hashMap.put("chat_commission", LocalDataBase.chat_commission1);
                hashMap.put("call_commission", LocalDataBase.call_commission1);
                hashMap.put("astro_token", LocalDataBase.astro_token1);
                return hashMap;
            }
        };
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }







}