package com.example.kruse.myapplication;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetUsersTask extends AsyncTask<Void, Void, String> {

    private final OnGetObjectComplete listener;

    GetUsersTask(OnGetObjectComplete listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        InputStream contentStream;
        String content = null;
        try {
            String myUrl = "http://appandroidserverjs.us-east-2.elasticbeanstalk.com/getUsers";
            URL url = new URL(myUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            contentStream = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(contentStream));
            content =  builder.append(reader.readLine()).toString();
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
