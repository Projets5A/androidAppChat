package com.example.kruse.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetGroupeMessagesTask extends AsyncTask<Void, Void, String> {
    private final OnGetObjectComplete listener;

    public GetGroupeMessagesTask(OnGetObjectComplete listener) {
        this.listener = listener;
    }

    protected String doInBackground(Void... params) {
        InputStream contentStream = null;
        String content = null;
        try {
            String myUrl = "http://appandroidserverjs.us-east-2.elasticbeanstalk.com/getGroupeMessages";
            URL url = new URL(myUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            contentStream = urlConnection.getInputStream();

            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(contentStream));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            content = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  content;
    }

    @Override
    protected void onPostExecute(final String content) {
        listener.getObject(content);
    }
}