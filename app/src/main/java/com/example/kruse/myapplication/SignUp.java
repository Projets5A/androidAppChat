package com.example.kruse.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements OnTaskComplete {

    private TextView emailView;
    private TextView pseudoView;
    private TextView passwordView;
    private TextView passwordVerifiedView;
    private UserSignupTask signUpTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailView = findViewById(R.id.email);
        pseudoView = findViewById(R.id.pseudo);
        passwordView = findViewById(R.id.password1);
        passwordVerifiedView = findViewById(R.id.password2);

        Button buttonClick = findViewById(R.id.signup);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });
    }

    private void attemptSignUp() {
        if (signUpTask != null) {
            return;
        }

        emailView.setError(null);
        pseudoView.setError(null);
        passwordView.setError(null);
        passwordVerifiedView.setError(null);

        String email = emailView.getText().toString();
        String pseudo = pseudoView.getText().toString();
        String password = passwordView.getText().toString();
        String passwordVerified = passwordVerifiedView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        if (!password.equals(passwordVerified)) {
            passwordVerifiedView.setError(getString(R.string.error_not_same_password));
            focusView = passwordVerifiedView;
            cancel = true;
        }

        if (TextUtils.isEmpty(pseudo)) {
            pseudoView.setError(getString(R.string.error_pseudo_empty));
            focusView = pseudoView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            checkSignUpInformations(email, pseudo, password);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 5;
    }

    private void checkSignUpInformations(String email, String pseudo, String password) {
        signUpTask = new UserSignupTask(email, pseudo, password, this);
        signUpTask.execute((Void) null);
    }

    @Override
    public void taskComplete(Boolean success) {
        if(success) {
            Toast.makeText(getApplicationContext(), "Account created ! Redirection to the login page", Toast.LENGTH_LONG).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                    startActivity(intent);
                }
            }, 3000);
        } else {
            Toast.makeText(getApplicationContext(), "Account not created, pseudo or email and password " +
                    "already exist, please enter new informations", Toast.LENGTH_LONG).show();
        }

    }
}
