package com.example.kruse.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnTaskComplete {

    private EditText pseudoView;
    private EditText passwordView;
    private String pseudo;
    private String password;
    private UserLoginTask loginTask = null;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pseudoView = (EditText) findViewById(R.id.editUserSignIn);
        passwordView = (EditText) findViewById(R.id.editPasswordSignIn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Button buttonClick = findViewById(R.id.login);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        TextView signup = findViewById(R.id.goSignup);
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

    private void attemptLogin() {
        if (loginTask != null) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        pseudoView.setError(null);
        passwordView.setError(null);

        pseudo = pseudoView.getText().toString();
        password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(passwordView.getText().toString())) {
            passwordView.setError(getString(R.string.error_field_required));
            focusView = passwordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(pseudoView.getText().toString())) {
            pseudoView.setError(getString(R.string.error_field_required));
            focusView = pseudoView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            checkLoginInformations();
        }
    }

    private void checkLoginInformations() {
        loginTask = new UserLoginTask(pseudo, password, this);
        loginTask.execute((Void) null);
    }

    public void taskComplete(Boolean success) {
        progressBar.setVisibility(View.INVISIBLE);
        if(success) {
            //TODO store value pseudo
            Intent intent = new Intent(this, Chat.class);
            SharedPreferences prefs = this.getSharedPreferences(
                    "account", this.MODE_PRIVATE);
            prefs.edit().putString("account.pseudo", pseudo).apply();
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Account not found", Toast.LENGTH_LONG).show();
        }
    }
}