package com.onfido.c.a;

import android.util.Base64;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class f {
    private String a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Basic ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(":");
        sb.append(Base64.encodeToString(sb2.toString().getBytes(), 2));
        return sb.toString();
    }

    public HttpURLConnection attribution(String str) {
        HttpURLConnection openConnection = openConnection("https://mobile-service.segment.com/v1/attribution");
        openConnection.setRequestProperty("Authorization", a(str));
        openConnection.setRequestMethod("POST");
        openConnection.setDoOutput(true);
        return openConnection;
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection openConnection(String str) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    public HttpURLConnection projectSettings(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://cdn-settings.segment.com/v1/projects/");
        sb.append(str);
        sb.append("/settings");
        return openConnection(sb.toString());
    }

    public HttpURLConnection upload(String str) {
        HttpURLConnection openConnection = openConnection("https://api.segment.io/v1/import");
        openConnection.setRequestProperty("Authorization", a(str));
        openConnection.setRequestProperty("Content-Encoding", "gzip");
        openConnection.setDoOutput(true);
        openConnection.setChunkedStreamingMode(0);
        return openConnection;
    }
}
