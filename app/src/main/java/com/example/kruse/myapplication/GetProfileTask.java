package com.example.kruse.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetProfileTask extends AsyncTask<Void, Void, String> {

    private final OnGetObjectComplete listener;
    private final String pseudo;

    GetProfileTask(String pseudo, OnGetObjectComplete listener) {
        this.listener = listener;
        this.pseudo = pseudo;
    }

    @Override
    protected String doInBackground(Void... params) {
        int responseCode = 401;
        InputStream contentStream = null;
        String content = null;
        try {
            String myUrl = "http://appandroidserverjs.us-east-2.elasticbeanstalk.com/getUserInfos?pseudo=" + pseudo;
            URL url = new URL(myUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            responseCode = urlConnection.getResponseCode();
            Log.i("responseCodeProfile", responseCode + "");
            if(responseCode == 200) {
                contentStream = urlConnection.getInputStream();
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(contentStream));
                content =  builder.append(reader.readLine()).toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    @Override
    protected void onPostExecute(final String profile) {
        listener.getObject(profile);
    }
}
