package com.example.kruse.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostGroupeMessagesTask extends AsyncTask<Void, Void, Boolean> {

    private final String mMessage;
    private final OnMessagePostListener listener;

    PostGroupeMessages(String message, OnMessagePostListener listener) {
        mMessage = message;
        this.listener = listener;
    }

    protected Boolean doInBackground(Void... params) {
        int responseCode = 401;
        try {
            String myUrl = "http://appandroidserverjs.us-east-2.elasticbeanstalk.com/PostGroupeMessages?message=" + mMessage ;
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
        listener.postmessage(success);
    }
}