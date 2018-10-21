package com.example.kruse.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity implements OnMessagePostListener, OnGetObjectComplete {

    private TextView messageView;
    private PostGroupeMessagesTask postMessageTask = null;
    private String message;
    private String author;
    private List<String> messages = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        author=getIntent().getStringExtra("EMAIL");
        Button buttonProfile = findViewById(R.id.profile);
        Button buttonWriteMessage = findViewById(R.id.WriteMessage);

        messageView = findViewById(R.id.messageChat);

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonProfileClick();
            }
        });

        buttonWriteMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonWriteMessageClick();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_id);

        GetGroupeMessagesTask getGroupMessages = new GetGroupeMessagesTask(this);
        getGroupMessages.execute();

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        ItemAdapter adapter = new ItemAdapter(messages, this);
        recyclerView.setAdapter(adapter);
    }

    private void onButtonProfileClick() {

        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("author",author);
        startActivity(intent);
    }

    private void onButtonWriteMessageClick() {
        message = messageView.getText().toString();
        PostGroupeMessagesTask postGroupMessages = new PostGroupeMessagesTask(message, author,this);
        postGroupMessages.execute();
    }

    @Override
    public void postmessage(Boolean success) {
        if(success) {
            //TODO store value pseudo
            GetGroupeMessagesTask getGroupMessages = new GetGroupeMessagesTask(this);
            getGroupMessages.execute();

        } else {
            Toast.makeText(getApplicationContext(), "Message not post", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void getObject(String content) {
        String[] strArr = null;
        try {
            JSONArray jsonArray = new JSONArray(content);
            strArr = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject obj = new JSONObject(jsonArray.getString(i));
                    Log.i("obj", obj.toString());
                    String author = obj.getString("author");
                    String message = obj.getString("content");
                    messages.add(author + ": " + message);
                } catch (Throwable t) {
                    Log.e("element", "Element not parsed");
                }
            }
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + content + "\"");
        }
    }
}