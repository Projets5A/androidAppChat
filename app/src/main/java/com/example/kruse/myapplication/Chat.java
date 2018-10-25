package com.example.kruse.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
//TODO changer les titres des pages
//TODO peut être mettre l heure ?
//TODO deconnexion sur la page profil ?
public class Chat extends AppCompatActivity implements OnMessagePostListener, OnGetObjectComplete {

    private TextView messageView;
    private String message;
    private String author;
    private List<String> messages = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private GetGroupeMessagesTask getGroupMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        SharedPreferences prefs = this.getSharedPreferences(
                "account", this.MODE_PRIVATE);
        author = prefs.getString("account.pseudo", null);

        final Button buttonProfile = findViewById(R.id.profile);
        final Button buttonWriteMessage = findViewById(R.id.WriteMessage);
        final Button buttonPrivateChat = findViewById(R.id.privateChat);

        messageView = findViewById(R.id.messageChat);

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonProfileClick();
            }
        });

        buttonPrivateChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPrivateChatClick();
            }
        });

        buttonWriteMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonWriteMessageClick();
            }
        });

        recyclerView = findViewById(R.id.recycler_view_id);

        getGroupMessages = new GetGroupeMessagesTask(this);
        getGroupMessages.execute();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ItemAdapter(messages, this);
        recyclerView.setAdapter(adapter);
    }

    private void onButtonProfileClick() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void onButtonWriteMessageClick() {
        message = messageView.getText().toString();
        PostGroupeMessagesTask postGroupMessages = new PostGroupeMessagesTask(message, author,this);
        postGroupMessages.execute();
    }

    private void onButtonPrivateChatClick() {
        Intent intent = new Intent(this, PrivateChatHub.class);
        startActivity(intent);
    }

    @Override
    public void postmessage(Boolean success) {
        if(success) {
            GetGroupeMessagesTask getGroupMessages = new GetGroupeMessagesTask(this);
            getGroupMessages.execute();
        } else {
            Toast.makeText(getApplicationContext(), "Message not post", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void getObject(String content) {
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<String> newMessages = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject obj = new JSONObject(jsonArray.getString(i));
                    String author = obj.getString("author");
                    String message = obj.getString("content");
                    newMessages.add(author + ": " + message);
                } catch (Throwable t) {
                    Log.e("element", "Error : Element not parsed");
                }
            }
            adapter = new ItemAdapter(newMessages, this);
            recyclerView.setAdapter(adapter);
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + content + "\"");
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                getGroupMessages = new GetGroupeMessagesTask(Chat.this);
                getGroupMessages.execute();
            }
        }, 2000);
    }
}
