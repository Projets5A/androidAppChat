package com.example.kruse.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostGroupeMessagesTask extends AsyncTask<Void, Void, Boolean> {

    private final String mMessage;
    private final String mAuthor;
    private final OnMessagePostListener listener;

    PostGroupeMessagesTask(String message, String author, OnMessagePostListener listener) {
        mMessage = message;
        mAuthor = author;
        this.listener = listener;
    }

    protected Boolean doInBackground(Void... params) {
        int responseCode = 401;
        try {
            String myUrl = "http://appandroidserverjs.us-east-2.elasticbeanstalk.com/sendGroupeMessages?message=" + mMessage + "&author=" + mAuthor;
            URL url = new URL(myUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            responseCode = urlConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean isConnected = responseCode == 201;
        return isConnected;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        listener.postmessage(success);
    }
}