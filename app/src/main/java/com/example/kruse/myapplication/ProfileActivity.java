package com.example.kruse.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements OnLoginChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button buttonProfile = findViewById(R.id.GroupeMessage);

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonProfileClick();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView email = findViewById(R.id.email);
        TextView pseudo = findViewById(R.id.pseudo);

        GetProfileTask getprofiletask = new GetProfileTask(this);

        email.setText("Votre email est: Thibault@truc.fr");
        pseudo.setText("Votre pseudo est: AbdelRappeur");
    }

    private void onButtonProfileClick() {
        Intent intent = new Intent(this, Chat.class);
        startActivity(intent);
    }

    public void signupChange(Boolean sucesss) {

    }
}
