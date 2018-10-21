package com.example.kruse.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnTaskComplete {

    //TODO faire en sorte que le chargement visuel se fasse!

    private EditText emailView;
    private EditText passwordView;
    private String email;
    private String password;
    private UserLoginTask loginTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailView = (EditText) findViewById(R.id.editUserSignIn);
        passwordView = (EditText) findViewById(R.id.editPasswordSignIn);


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

        email = emailView.getText().toString();
        password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(passwordView.getText().toString())) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(emailView.getText().toString())) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(emailView.getText().toString())) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            checkLoginInformations();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private void checkLoginInformations() {
        loginTask = new UserLoginTask(email, password, this);
        loginTask.execute((Void) null);
    }

    public void taskComplete(Boolean success) {
        if(success) {
            //TODO store value pseudo
            Intent intent = new Intent(this, Chat.class);
            intent.putExtra("EMAIL",email);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Account not found", Toast.LENGTH_LONG).show();
        }

    }
}