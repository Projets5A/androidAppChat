package com.example.kruse.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetGroupeMessagesTask extends AsyncTask<Void, Void, Boolean> {
    protected Boolean doInBackground(Void... params) {
        int responseCode = 401;
        try {
            String myUrl = "http://appandroidserverjs.us-east-2.elasticbeanstalk.com/getGroupeMessages";
            URL url = new URL(myUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            responseCode = urlConnection.getResponseCode();
            Log.i("tag", "" + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean isConnected = responseCode == 200;
        return isConnected;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        Log.i("tag", "getmessagesgroup success!");
    }
}