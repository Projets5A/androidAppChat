package com.example.kruse.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetGroupeMessagesTask extends AsyncTask<Void, Void, Object> {
    private final OnGetObjectComplete listener;

    public GetGroupeMessagesTask(OnGetObjectComplete listener) {
        this.listener = listener;
    }
    protected Object doInBackground(Void... params) {
        Object content = null;
        try {
            String myUrl = "http://appandroidserverjs.us-east-2.elasticbeanstalk.com/getGroupeMessages";
            URL url = new URL(myUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            content = urlConnection.getContent();
            Log.i("content", "" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    @Override
    protected void onPostExecute(final Object content) {
        listener.getObject(content);
    }
}