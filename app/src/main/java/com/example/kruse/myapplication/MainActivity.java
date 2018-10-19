package com.example.kruse.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText emailView;
    private EditText passwordView;
    private UserLoginTask loginTask = null;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailView = (EditText) findViewById(R.id.editUserSignIn);
        passwordView = (EditText) findViewById(R.id.editPasswordSignIn);

        email = emailView.getText().toString();
        password = passwordView.getText().toString();

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

        emailView.setError(null);
        passwordView.setError(null);

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password) || !isPasswordValid()) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid()) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            /*showProgress(true);
            loginTask = new UserLoginTask(email, password, this);
            loginTask.execute((Void) null);*/
            checkLoginInformations();
        }
    }

    private boolean isEmailValid() {
        return true;
        //return email.contains("@");
    }

    private boolean isPasswordValid() {
        return password.length() > 2;
    }

    private void checkLoginInformations() {
        if( emailView.getText().toString().equals("thib") && passwordView.getText().toString().equals("thib") ) {
            Intent intent = new Intent(this, Chat.class);
            intent.putExtra("pseudo", "");
            startActivity(intent);
        } else {
            Log.i("TAG", "Bad login informations!");
        }
    }
}