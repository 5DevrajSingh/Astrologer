package com.ksbm_astroexpert.ui.RemidesDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
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
import com.ksbm_astroexpert.Adapter.AllRemediesAdapter;
import com.ksbm_astroexpert.Chat.utils.ParseManager;
import com.ksbm_astroexpert.Constant.RootURL;
import com.ksbm_astroexpert.ModelClass.AllRemidesModel;
import com.ksbm_astroexpert.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RemidesDetails extends AppCompatActivity {

    String astrotitle, astrocontent, icon2;
    ImageView backarrow;
    TextView txtBlogTitle, txtBlogDescription;
    RecyclerView recyclerRemedies;
    Context context = this;
    TextView textTitle,textNoRecord;
    ArrayList<AllRemidesModel.Response> arrListRemides = new ArrayList<>();
    int image[] = {R.drawable.images_user, R.drawable.images_user, R.drawable.images_user, R.drawable.images_user, R.drawable.images_user, R.drawable.images_user,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remides_details);
        init();
    }

    private void init() {
        astrotitle = getIntent().getStringExtra("astrotitle");
        astrocontent = getIntent().getStringExtra("astrocontent").trim();
        icon2 = getIntent().getStringExtra("icon2");

        backarrow = findViewById(R.id.img_back);
        textTitle = findViewById(R.id.text_remides_title);
        textNoRecord = findViewById(R.id.text_norecord);
        recyclerRemedies = findViewById(R.id.recycler_remedies);
        textTitle.setText(astrotitle);


        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        hitRemidesApi( getIntent().getStringExtra("remides_id"));
    }


    private void hitRemidesApi( String remidesId) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RootURL.ALL_REMIDES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("checkressponseee", response);
                try {
                    AllRemidesModel model = ParseManager.getInstance().fromJSON(response, AllRemidesModel.class);
                    if (model != null) {
                        if (model.status.equals("1")){
                            arrListRemides=model.response;
                            Log.d("chearrlist", String.valueOf(arrListRemides.size()));
                            if (!arrListRemides.isEmpty()){
                                setRecyclerRemedies();
                            }else {
                                textNoRecord.setVisibility(View.VISIBLE);
                            }

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
                Log.d("dsdjld",volleyError.getMessage());
                // progressbar.hideProgress();


            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("remedies_id", remidesId);
                Log.d("ddddfsad", String.valueOf(hashMap));
                return hashMap;
            }
        };
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }
    void setRecyclerRemedies(){
        recyclerRemedies.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 1);
        recyclerRemedies.setLayoutManager(layoutManager);
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.recyclerview_anim_layout);
        recyclerRemedies.setLayoutAnimation(layoutAnimationController);
        AllRemediesAdapter adapter = new AllRemediesAdapter(context, arrListRemides);
        recyclerRemedies.setAdapter(adapter);
    }

}