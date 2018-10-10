package com.example.kruse.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

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
}