package com.example.kruse.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonClick = findViewById(R.id.login);

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick();
            }
        });

        TextView signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUpClick();
            }
        });
    }

    private void onSignUpClick() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    private void onButtonClick() {
        TextView email = findViewById(R.id.editText2);
        TextView password = findViewById(R.id.editText3);
        if( email.getText().toString().equals("thib") && password.getText().toString().equals("thib") ) {
            Intent intent = new Intent(this, Chat.class);
            startActivity(intent);
        } else {
            Log.i("TAG", "Bad login informations!");
        }
    }
}