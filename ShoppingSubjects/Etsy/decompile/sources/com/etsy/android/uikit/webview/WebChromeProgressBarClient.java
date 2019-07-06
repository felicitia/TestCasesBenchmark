package com.etsy.android.uikit.webview;

import android.os.Handler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class WebChromeProgressBarClient extends WebChromeClient {
    private static final int COMPLETE = 100;
    private static final int PROGRESSBAR_HIDE_DELAY_MILLIS = 200;
    private Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;

    public WebChromeProgressBarClient(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        this.mProgressBar.setProgress(i);
        if (i == 100) {
            hideDelayed();
        }
    }

    private void hideDelayed() {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (WebChromeProgressBarClient.this.mProgressBar != null) {
                    WebChromeProgressBarClient.this.mProgressBar.setVisibility(8);
                }
            }
        }, 200);
    }
}
