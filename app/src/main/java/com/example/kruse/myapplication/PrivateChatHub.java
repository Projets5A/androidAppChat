package com.example.kruse.myapplication;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PrivateChatHub extends AppCompatActivity implements OnGetObjectComplete {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_chat_hub);

        GetUsersTask getUsers = new GetUsersTask(this);
        getUsers.execute();

        recyclerView = findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> emptyList = new ArrayList<>();
        emptyList.add("init");
        adapter = new ItemAdapter(emptyList,this);
        recyclerView.setAdapter(adapter);
    }

    public void getObject(String users) {
        try {
            JSONObject jsonUsers = new JSONObject(users);
            String arrayUsers = jsonUsers.getString("users");
            JSONArray jsonArray = new JSONArray(arrayUsers);
            List<String> listUsers = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    Log.i("test", jsonArray.getString(i));
                    JSONObject obj = new JSONObject(jsonArray.getString(i));
                    String pseudo = obj.getString("pseudo");
                    String connected = obj.getString("connected");
                    listUsers.add(pseudo + ": " + connected);
                } catch (Throwable t) {
                    Log.e("element", "Error : Element not parsed");
                }
            }
            adapter = new ItemAdapter(listUsers, this);
            recyclerView.setAdapter(adapter);
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + users + "\"");
        }
    }
}
