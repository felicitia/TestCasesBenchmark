package com.paytm.pgsdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Iterator;
import org.json.JSONObject;

@TargetApi(21)
public class PaytmWebView extends WebView {
    /* access modifiers changed from: private */
    public final PaytmPGActivity mContext;
    /* access modifiers changed from: private */
    public volatile boolean mbMerchantCallbackURLLoaded;

    private class PaytmJavaScriptInterface {
        private PaytmJavaScriptInterface() {
        }

        @JavascriptInterface
        public synchronized void processResponse(String str) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("Merchant Response is ");
                sb.append(str);
                PaytmUtility.debugLog(sb.toString());
                Bundle access$600 = PaytmWebView.this.parseResponse(str);
                String str2 = (String) PaytmPGService.getService().mOrder.getRequestParamMap().get("CALLBACK_URL");
                if (!TextUtils.isEmpty(str2)) {
                    if (!PaytmWebView.this.mbMerchantCallbackURLLoaded) {
                        PaytmUtility.debugLog("Merchant Specific URL is present, So posting all parameters to Merchant specific URL");
                        PaytmWebView.this.postUrl(str2, PaytmUtility.getURLEncodedStringFromBundle(access$600).getBytes());
                    }
                }
                PaytmUtility.debugLog("Returning the response back to Merchant Application");
                returnResponse(access$600);
            } catch (Exception e) {
                PaytmUtility.printStackTrace(e);
            }
            return;
        }

        private synchronized void returnResponse(final Bundle bundle) {
            try {
                ((Activity) PaytmWebView.this.getContext()).runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            ((Activity) PaytmWebView.this.getContext()).finish();
                            PaytmPGService.getService().getmPaymentTransactionCallback().onTransactionResponse(bundle);
                        } catch (Exception e) {
                            PaytmUtility.printStackTrace(e);
                        }
                    }
                });
            } catch (Exception e) {
                PaytmUtility.printStackTrace(e);
            }
            return;
        }
    }

    private class PaytmWebViewClient extends WebViewClient {
        private PaytmWebViewClient() {
        }

        public synchronized void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            StringBuilder sb = new StringBuilder();
            sb.append("Page started loading ");
            sb.append(str);
            PaytmUtility.debugLog(sb.toString());
            PaytmWebView.this.startProgressDialog();
        }

        /* JADX INFO: finally extract failed */
        public synchronized void onPageFinished(WebView webView, String str) {
            Intent intent;
            PaytmPGActivity access$500;
            super.onPageFinished(webView, str);
            if (PaytmPGService.getService() == null || PaytmPGService.getService().mOrder == null) {
                PaytmUtility.debugLog("Transaction cancelled before loading web view completely.");
                return;
            }
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("Page finished loading ");
                sb.append(str);
                PaytmUtility.debugLog(sb.toString());
                PaytmWebView.this.stopProgressDialog();
                if (str.equalsIgnoreCase(((String) PaytmPGService.getService().mOrder.getRequestParamMap().get("CALLBACK_URL")).toString())) {
                    PaytmUtility.debugLog("Merchant specific Callback Url is finished loading. Extract data now. ");
                    PaytmWebView.this.mbMerchantCallbackURLLoaded = true;
                    PaytmWebView.this.loadUrl("javascript:window.HTMLOUT.processResponse(document.getElementById('response').value);");
                } else if (str.endsWith("/CAS/Response")) {
                    PaytmUtility.debugLog("CAS Callback Url is finished loading. Extract data now. ");
                    PaytmWebView.this.loadUrl("javascript:window.HTMLOUT.processResponse(document.getElementById('response').value);");
                }
                if (PaytmPGService.getService().mOrder.getRequestParamMap().get("postnotificationurl") != null) {
                    intent = new Intent(PaytmWebView.this.mContext, IntentServicePostNotification.class);
                    intent.putExtra("url", (String) PaytmPGService.getService().mOrder.getRequestParamMap().get("postnotificationurl"));
                    access$500 = PaytmWebView.this.mContext;
                    access$500.startService(intent);
                }
            } catch (Exception e) {
                try {
                    PaytmUtility.printStackTrace(e);
                    if (PaytmPGService.getService().mOrder.getRequestParamMap().get("postnotificationurl") != null) {
                        intent = new Intent(PaytmWebView.this.mContext, IntentServicePostNotification.class);
                        intent.putExtra("url", (String) PaytmPGService.getService().mOrder.getRequestParamMap().get("postnotificationurl"));
                        access$500 = PaytmWebView.this.mContext;
                    }
                } catch (Throwable th) {
                    if (PaytmPGService.getService().mOrder.getRequestParamMap().get("postnotificationurl") != null) {
                        Intent intent2 = new Intent(PaytmWebView.this.mContext, IntentServicePostNotification.class);
                        intent2.putExtra("url", (String) PaytmPGService.getService().mOrder.getRequestParamMap().get("postnotificationurl"));
                        PaytmWebView.this.mContext.startService(intent2);
                    }
                    throw th;
                }
            }
        }

        public synchronized void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            StringBuilder sb = new StringBuilder();
            sb.append("Error occured while loading url ");
            sb.append(str2);
            PaytmUtility.debugLog(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Error code is ");
            sb2.append(i);
            sb2.append("Description is ");
            sb2.append(str);
            PaytmUtility.debugLog(sb2.toString());
            if (i == -6) {
                ((Activity) PaytmWebView.this.getContext()).finish();
                PaytmPaymentTransactionCallback paytmPaymentTransactionCallback = PaytmPGService.getService().getmPaymentTransactionCallback();
                if (paytmPaymentTransactionCallback != null) {
                    paytmPaymentTransactionCallback.onErrorLoadingWebPage(i, str, str2);
                }
            }
        }

        public synchronized void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            StringBuilder sb = new StringBuilder();
            sb.append("SSL Error occured ");
            sb.append(sslError.toString());
            PaytmUtility.debugLog(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("SSL Handler is ");
            sb2.append(sslErrorHandler);
            PaytmUtility.debugLog(sb2.toString());
            if (sslErrorHandler != null) {
                sslErrorHandler.cancel();
            }
        }
    }

    public PaytmWebView(Context context, Bundle bundle) {
        super(context);
        this.mContext = (PaytmPGActivity) context;
        setWebViewClient(new PaytmWebViewClient());
        setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                StringBuilder sb = new StringBuilder();
                sb.append("JavaScript Alert ");
                sb.append(str);
                PaytmUtility.debugLog(sb.toString());
                return super.onJsAlert(webView, str, str2, jsResult);
            }
        });
        getSettings().setJavaScriptEnabled(true);
        if (VERSION.SDK_INT >= 21) {
            getSettings().setMixedContentMode(0);
        }
        addJavascriptInterface(new PaytmJavaScriptInterface(), "HTMLOUT");
    }

    /* access modifiers changed from: private */
    public synchronized void startProgressDialog() {
        try {
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                public void run() {
                    PaytmWebView.this.mContext.mProgress.setVisibility(0);
                    PaytmUtility.debugLog("Progress dialog started");
                }
            });
        } catch (Exception e) {
            PaytmUtility.printStackTrace(e);
        }
        return;
    }

    /* access modifiers changed from: private */
    public synchronized void stopProgressDialog() {
        try {
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                public void run() {
                    PaytmWebView.this.mContext.mProgress.setVisibility(8);
                    PaytmUtility.debugLog("Progress dialog ended");
                }
            });
        } catch (Exception e) {
            PaytmUtility.printStackTrace(e);
        }
        return;
    }

    /* access modifiers changed from: private */
    public synchronized Bundle parseResponse(String str) {
        Bundle bundle;
        PaytmUtility.debugLog("Parsing the Merchant Response");
        bundle = new Bundle();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != null && jSONObject.length() > 0) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    String string = jSONObject.getString(str2);
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append(" = ");
                    sb.append(string);
                    PaytmUtility.debugLog(sb.toString());
                    bundle.putString(str2, string);
                }
            }
        } catch (Exception e) {
            PaytmUtility.debugLog("Error while parsing the Merchant Response");
            PaytmUtility.printStackTrace(e);
        }
        return bundle;
    }
}
