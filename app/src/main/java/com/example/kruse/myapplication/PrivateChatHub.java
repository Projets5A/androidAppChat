package com.example.kruse.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class PrivateChatHub extends AppCompatActivity implements OnGetObjectComplete {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_chat_hub);

        GetUsersTask getUsers = new GetUsersTask(this);
        getUsers.execute();
    }

    public void getObject(String users) {
        Log.i("users", users);
    }
}
