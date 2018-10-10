package com.example.kruse.myapplication;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetProfileTask extends AsyncTask<Void, Void, Boolean> {

    private final OnLoginChangeListener listener;

    GetProfileTask(OnLoginChangeListener listener) {
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        int responseCode = 401;
        try {
            String myUrl = "http://localhost:3000/getUserInfos";
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
