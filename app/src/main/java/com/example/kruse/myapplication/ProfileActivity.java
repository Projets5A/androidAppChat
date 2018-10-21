package com.example.kruse.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements OnTaskComplete {

    private String author;

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

        TextView email = findViewById(R.id.email);
        TextView pseudo = findViewById(R.id.pseudo);

        GetProfileTask getprofiletask = new GetProfileTask(this);

        email.setText("Votre email est: Thibault@truc.fr");
        pseudo.setText("Votre pseudo est :" + author);
    }

    private void onButtonChatClick() {
        Intent intent = new Intent(this, Chat.class);
        intent.putExtra("EMAIL",author);
        startActivity(intent);
    }

    public void taskComplete(Boolean sucesss) {

    }
}
