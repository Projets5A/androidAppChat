package com.example.kruse.myapplication;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserLogoutTask extends AsyncTask<Void, Void, Boolean> {

    private final String pseudo;
    private final OnTaskComplete listener;

    UserLogoutTask(String pseudo, String password, OnTaskComplete listener) {
        this.pseudo = pseudo;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        int responseCode = 401;
        try {
            //TODO changer en pseudo quand le serveur est fix
            String myUrl = "http://appandroidserverjs.us-east-2.elasticbeanstalk.com/signOut?pseudo=" + pseudo;
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
        listener.taskComplete(success);
    }
}