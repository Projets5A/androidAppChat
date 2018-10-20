package com.example.kruse.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class UserSignupTask extends AsyncTask<Void, Void, Boolean> {

    private final String email;
    private final String pseudo;
    private final String password;
    private final OnTaskComplete listener;

    UserSignupTask(String email, String pseudo, String password, OnTaskComplete listener) {
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        int responseCode = 401;
        HttpURLConnection urlConnection = null;
        try {
            String myUrl = "http://appandroidserverjs.us-east-2.elasticbeanstalk.com/createUser?email=" + email + "&pseudo=" + pseudo + "&password=" + password;
            URL url = new URL(myUrl);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");

            responseCode = urlConnection.getResponseCode();

        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("response code LA!",String.valueOf(responseCode));
        boolean isConnected = responseCode == 201;
        return isConnected;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        listener.taskComplete(success);
    }
}