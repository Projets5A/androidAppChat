package com.example.kruse.myapplication;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserSignupTask extends AsyncTask<Void, Void, Boolean> {

    private final String mEmail;
    private final String mPseudo;
    private final String mPassword;
    private final OnLoginChangeListener listener;

    UserSignupTask(String email, String pseudo, String password, OnLoginChangeListener listener) {
        mEmail = email;
        mPseudo = pseudo;
        mPassword = password;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        int responseCode = 401;
        try {
            String myUrl = "http://appandroidserverjs.us-east-2.elasticbeanstalk.com/createUser?email=" + mEmail + "?pseudo=" + mPseudo + "?password=" + mPassword;
            URL url = new URL(myUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            responseCode = urlConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean isConnected = responseCode == 200;
        return isConnected;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        listener.signupChange(success);
    }
}