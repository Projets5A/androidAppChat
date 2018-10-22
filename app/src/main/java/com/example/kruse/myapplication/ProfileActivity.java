package com.example.kruse.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity implements OnGetObjectComplete {

    private String author;
    private TextView emailView;
    private TextView pseudoView;
    private TextView statutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        author=getIntent().getStringExtra("author");
        Button buttonChat = findViewById(R.id.GroupeMessage);

        buttonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonChatClick();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emailView = findViewById(R.id.emailProfile);
        pseudoView = findViewById(R.id.pseudoProfile);
        statutView = findViewById(R.id.statusProfile);

        GetProfileTask getprofiletask = new GetProfileTask(author, this);
        getprofiletask.execute();
    }

    private void onButtonChatClick() {
        Intent intent = new Intent(this, Chat.class);
        intent.putExtra("EMAIL",author);

        startActivity(intent);
    }

    public void getObject(String profile) {
        Log.i("profile", profile);
        try {
            JSONObject obj = new JSONObject(profile);
            String email = obj.getString("email");
            String pseudo = obj.getString("pseudo");

            emailView.setText("Votre email est: " + email);
            pseudoView.setText("Votre pseudo est :" + pseudo);
            if(obj.getString("connected").equals("true")) {
                statutView.setTextColor(Color.BLUE);
                statutView.setText("Connected");
            } else {
                statutView.setTextColor(Color.RED);
                statutView.setText("Disconnected");
            }

        }  catch (Throwable t) {
            Log.e("element", "Error : Element not parsed");
        }
    }
}
