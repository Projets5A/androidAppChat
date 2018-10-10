package com.example.kruse.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUp extends AppCompatActivity implements OnLoginChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button buttonClick = findViewById(R.id.signup);

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick();
            }
        });
    }

    private void onButtonClick() {
        TextView email = findViewById(R.id.email);
        TextView pseudo = findViewById(R.id.pseudo);
        TextView password1 = findViewById(R.id.password1);
        TextView password2 = findViewById(R.id.password2);

        UserSignupTask Signin = new UserSignupTask(email.getText().toString() , pseudo.getText().toString() , password1.getText().toString() , this);
        Signin.doInBackground();
    }

    @Override
    public void signupChange(Boolean success) {

    }
}
