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

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity implements OnMessagePostListener {

    private TextView messageView;
    private PostGroupeMessagesTask postMessageTask = null;
    private String message;
    private String author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        author=getIntent().getStringExtra("author");
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

        GetGroupeMessagesTask getGroupMessages = new GetGroupeMessagesTask();
        getGroupMessages.execute();




        List<String> messages = new ArrayList<>();

        messages.add("Hello world");
        messages.add("Hello world");
        messages.add("Hello world");
        messages.add("Hello world");
        messages.add("Hello world");
        messages.add("Hello world");

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


    }

}