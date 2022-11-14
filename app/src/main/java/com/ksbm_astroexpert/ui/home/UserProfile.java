package com.ksbm_astroexpert.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {

    View view;
    TextView name,email,mobile;
    EditText editname,editemail,editnumber,upi;
    CircleImageView ivProfile;
    Bitmap bitmap;
    private ImageView ivGallery, ivCamera;
    private final int PICK_IMAGE = 12345;
    private final int TAKE_PICTURE = 6352;
    private Button btnSubmit ,btnUpload;
    ByteArrayOutputStream byteArrayOutputStream;
    ImageView image;
    TextView plandetail,startdate,enddate,validity,trialdays;
    CardView cardmet;
    private String currentUserId, otherUserId;
    private static final int REQUEST_CAMERA_ACCESS_PERMISSION = 5674;
    public DatabaseReference userDatabase, requestsDatabase, friendsDatabase;
    private final String TAG = "CA/ProfileActivity";
    String uploadimg;
    private ValueEventListener userListener, requestsListener, friendsListerner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Allid();
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);

        Log.i("dfsdfsfdsdfsdf",currentUserId);
        editnumber.setText(sharedpreferences.getString("number",""));
        if (!sharedpreferences.getString("username","").equals("null")){
            editname.setText(sharedpreferences.getString("username",""));
            editemail.setText(sharedpreferences.getString("email",""));
            name.setText(sharedpreferences.getString("username",""));

            Picasso.get().load(sharedpreferences.getString("user_profile_image", ""))
                    .placeholder(R.drawable.splash)
                    .into(ivProfile);
        }

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PICK_IMAGE);

            }
        });

        /*if (!sharedpreferences.getString("user_subscribed","").equals("1")){
            cardmet.setVisibility(View.GONE);
        }else {
            cardmet.setVisibility(View.VISIBLE);
        }

        if (sharedpreferences.getString("image","").equals(null)){

        }else {
        }*/
        //btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
       /* ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });*/
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateemail(editemail.getText().toString())&&validatename(editname.getText().toString())&&validatemobile(editnumber.getText().toString())){
                    UploadData();
                }

            }
        });
    }

    private void Allid() {
        name = findViewById(R.id.name);
        editnumber = findViewById(R.id.mobile);
        editname = findViewById(R.id.editname);
        editemail = findViewById(R.id.editemail);
        btnSubmit = findViewById(R.id.btnSubmit);
        ivProfile = findViewById(R.id.ivProfile);
    }


    private void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    ivProfile.setImageBitmap(bitmap);
                    uploadimg=getStringImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));
                    getStringImage(bitmap);
                    //Firebase code

                    Uri url = data.getData();
                    //Uploading selected picture

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        } else if (requestCode == TAKE_PICTURE) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle extras = data.getExtras();
                bitmap = (Bitmap) extras.get("data");
                ivProfile.setImageBitmap(bitmap);
                uploadimg=getStringImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));
                getStringImage(bitmap);
            }
        }

    }
    private boolean validatename(String string) {
        if (string.equals("")) {
            editname.setError("Enter user name");
            return false;
        }
        return true;

    }

    private boolean validateemail(String string) {
        if (string.equals("")) {
            editemail.setError("Please Enter your Mail ID");
            return false;
        }
        return true;

    }
    private boolean validatemobile(String string) {
        if (string.equals("")) {
            editnumber.setError("Please Enter your Mobile Number");
            return false;
        }
        return true;

    }

    private void UploadData() {
        SharedPreferences sharedpreferences =getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
        final ProgressDialog progress = new ProgressDialog(UserProfile.this);
        progress.setMessage("Please Wait..");
        progress.setCancelable(false);
        progress.show();
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        final String URLALL=RootURL.UPDATEPROFILE
                +"username="+editname.getText().toString().replaceAll(" ","%20")
                +"&email="+editemail.getText().toString()
                +"&mobile="+editnumber.getText().toString()
                +"&id="+sharedpreferences.getString("id", "")
                +"&gender=Male"
                +"&upi=djknj";

        Log.d("ABCsds",URLALL);
        StringRequest request=new StringRequest(Request.Method.DEPRECATED_GET_OR_POST,URLALL.replaceAll(" ","%20"),
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        try
                        {
                            Log.d("ABC",response);
                            JSONObject jsonObject=new JSONObject(response);
                            if (jsonObject.getString("status").equals("1"))
                            {
                                progress.dismiss();
                                JSONObject json = jsonObject.getJSONObject("info");
                                SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences(RootURL.PREFACCOUNT, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("username",json.getString("username"));
                                editor.putString("email",json.getString("email"));
                                editor.putString("number",json.getString("phone"));
                                editor.commit();
                                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                Map map = new HashMap<>();
                                map.put("token", FirebaseInstanceId.getInstance().getToken());
                                map.put("name",json.getString("username") );
                                map.put("email", json.getString("email"));
                                map.put("phone", json.getString("phone"));
                                map.put("status", "Welcome to my Profile!");
                                map.put("image", "default");
                                map.put("cover", "default");
                                map.put("date", ServerValue.TIMESTAMP);

                                // Uploading user data

                                FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        if(task.isSuccessful())
                                        {
                                   /* Toast.makeText(SignupActivity.this, "sfsfdfsf", Toast.LENGTH_SHORT).show();
                                    //registerButton.setProgress(100);

                                    Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                                    startActivity(intent);
                                    finish();*/
                                            //login_user();
                                        }
                                        else
                                        {
                                            //  login_user();
                                            Log.d(TAG, "registerData failed: " + task.getException().getMessage());
                                        }
                                    }
                                });

                            }
                            else
                            {
                                progress.dismiss();
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {


                        progress.dismiss();
                        Toast.makeText(getApplicationContext(), "No internet connection"+error, Toast.LENGTH_LONG).show();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //image=getStringImage(bitmap);

                //Log.i("image", image);

                Map<String, String> params = new Hashtable<String, String>();

                params.put("profile_image", uploadimg);

                return params;
            }
        };
        requestQueue.add(request);


    }
    public String getStringImage(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.d("abcd",encodedImage);
        return encodedImage;

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_ACCESS_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}