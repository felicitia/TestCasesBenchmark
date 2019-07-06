package com.qualtrics.qsiframework;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.qualtrics.qsiframework.c.a;
import com.qualtrics.qsiframework.c.b;

public class QualtricsActivity extends AppCompatActivity {
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(b.qualtrics_activity);
        if (bundle == null) {
            Bundle extras = getIntent().getExtras();
            str = extras != null ? extras.getString("Q_URL") : null;
        } else {
            str = (String) bundle.getSerializable("Q_URL");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Starting activity, survey URL: ");
        sb.append(str);
        Log.i("QPUSH", sb.toString());
        if (str != null) {
            WebView webView = (WebView) findViewById(a.survey_webview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setOverScrollMode(2);
            webView.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView webView, String str) {
                }

                public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                }

                public void onReceivedError(WebView webView, int i, String str, String str2) {
                }

                public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                }
            });
            webView.loadUrl(str);
        }
    }
}
