package com.ksbm_astroexpert.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ksbm_astroexpert.Chat.activity.ChatActivity;
import com.ksbm_astroexpert.Chat.holders.ChatHolder;
import com.ksbm_astroexpert.Chat.models.Chat;
import com.ksbm_astroexpert.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Chatactivitypro extends AppCompatActivity {

    private FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatactivitypro);

        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize Chat Database

        DatabaseReference chatDatabase = FirebaseDatabase.getInstance().getReference().child("Chat").child(currentUserId);
        chatDatabase.keepSynced(true); // For offline use

        // RecyclerView related

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Chatactivitypro.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        RecyclerView recyclerView = findViewById(R.id.chat_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);

        // Initializing adapter

        FirebaseRecyclerOptions<Chat> options = new FirebaseRecyclerOptions.Builder<Chat>().setQuery(chatDatabase.orderByChild("timestamp"), Chat.class).build();

        adapter = new FirebaseRecyclerAdapter<Chat, ChatHolder>(options) {
            @Override
            public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user, parent, false);

                return new ChatHolder(Chatactivitypro.this, view, getApplicationContext());
            }

            @Override
            protected void onBindViewHolder(final ChatHolder holder, int position, final Chat model)
            {
                final String userid = getRef(position).getKey();

                holder.setHolder(userid, model.getMessage(), model.getTimestamp(), model.getSeen());
                holder.getView().setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent chatIntent = new Intent(Chatactivitypro.this, ChatActivity.class);
                        chatIntent.putExtra("userid", userid);
                        startActivity(chatIntent);
                    }
                });
            }

            @Override
            public void onDataChanged()
            {
                super.onDataChanged();

                LinearLayout text = findViewById(R.id.f_chat_text);

                if(adapter.getItemCount() == 0)
                {
                    text.setVisibility(View.VISIBLE);
                }
                else
                {
                    text.setVisibility(View.GONE);
                }
            }
        };

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        adapter.startListening();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop()
    {
        super.onStop();

        adapter.stopListening();
    }

    public void onbe(View view) {
        onBackPressed();
    }
}