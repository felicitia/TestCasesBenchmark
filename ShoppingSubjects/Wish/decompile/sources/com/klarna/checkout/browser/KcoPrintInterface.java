package com.klarna.checkout.browser;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.print.PrintAttributes.Builder;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class KcoPrintInterface {
    /* access modifiers changed from: private */
    public final WebView a;

    public KcoPrintInterface(WebView webView) {
        this.a = webView;
    }

    @JavascriptInterface
    public void print() {
        if (VERSION.SDK_INT >= 19) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @TargetApi(19)
                public final void run() {
                    PrintManager printManager = (PrintManager) KcoPrintInterface.this.a.getContext().getSystemService("print");
                    PrintDocumentAdapter createPrintDocumentAdapter = KcoPrintInterface.this.a.createPrintDocumentAdapter();
                    String title = KcoPrintInterface.this.a.getTitle();
                    if (title == null) {
                        title = "Untitled Web Document";
                    }
                    printManager.print(title, createPrintDocumentAdapter, new Builder().build());
                }
            });
        }
    }
}
