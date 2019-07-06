package com.android.riskifiedbeacon;

import android.os.AsyncTask;
import android.util.Log;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class clientInfosRestClientUsingUrlHttpConnection {

    private class SendRequestJob extends AsyncTask<String, Void, String> {
        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
        }

        private SendRequestJob() {
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String[] strArr) {
            try {
                return sendRequest(strArr[0]);
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        private String sendRequest(String str) throws IOException {
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
            try {
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                return Integer.valueOf(httpURLConnection.getResponseCode()).toString();
            } finally {
                httpURLConnection.disconnect();
            }
        }
    }

    public void logRequest(String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("URL: ");
        sb.append(str);
        Log.d("CLIENT INFO CLIENT", sb.toString());
        new SendRequestJob().execute(new String[]{str});
    }
}
