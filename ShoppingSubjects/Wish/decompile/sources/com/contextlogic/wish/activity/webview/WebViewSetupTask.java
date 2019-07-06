package com.contextlogic.wish.activity.webview;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import com.contextlogic.wish.http.HttpCookieManager;

public class WebViewSetupTask extends AsyncTask<Void, Void, Boolean> {
    private WebViewSetupCallback mCallback;
    private WebView mWebView;

    public interface WebViewSetupCallback {
        void onWebviewReady();
    }

    public WebViewSetupTask(WebView webView, WebViewSetupCallback webViewSetupCallback) {
        this.mCallback = webViewSetupCallback;
        this.mWebView = webView;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        HttpCookieManager.getInstance().prepareToSetupWebviewCookies();
        super.onPreExecute();
    }

    /* access modifiers changed from: protected */
    public Boolean doInBackground(Void... voidArr) {
        SystemClock.sleep(500);
        return Boolean.valueOf(false);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Boolean bool) {
        HttpCookieManager.getInstance().setupWebviewCookies();
        if (this.mCallback != null) {
            this.mCallback.onWebviewReady();
            this.mCallback = null;
        }
        if (this.mWebView != null) {
            this.mWebView.getSettings().setJavaScriptEnabled(true);
            this.mWebView.getSettings().setPluginState(PluginState.ON);
            this.mWebView.getSettings().setDomStorageEnabled(true);
            this.mWebView.getSettings().setAllowFileAccess(true);
            this.mWebView.getSettings().setLoadWithOverviewMode(true);
            this.mWebView.getSettings().setUseWideViewPort(true);
            this.mWebView.setScrollBarStyle(33554432);
        }
    }
}
