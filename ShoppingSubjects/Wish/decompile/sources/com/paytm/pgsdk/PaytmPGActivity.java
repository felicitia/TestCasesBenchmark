package com.paytm.pgsdk;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.Iterator;
import org.json.JSONObject;

public class PaytmPGActivity extends Activity {
    private volatile AuthenticatorTask mAuthenticator;
    /* access modifiers changed from: private */
    public Dialog mDlg;
    /* access modifiers changed from: private */
    public volatile Bundle mParams;
    protected volatile ProgressBar mProgress;
    /* access modifiers changed from: private */
    public volatile PaytmWebView mWV;
    /* access modifiers changed from: private */
    public boolean mbChecksumGenerated;
    private boolean mbHideHeader;
    private boolean mbIsCancellingRequest;
    private boolean mbSendAllChecksumResponseParametersToPGServer;
    private String paramsString;
    private String urlString;

    private class AuthenticatorTask extends AsyncTask<String, Void, String> {
        private AuthenticatorTask() {
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x011c A[SYNTHETIC, Splitter:B:27:0x011c] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized java.lang.String doInBackground(java.lang.String... r7) {
            /*
                r6 = this;
                monitor-enter(r6)
                java.lang.String r0 = ""
                r1 = 0
                java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x010e }
                r3 = 0
                r7 = r7[r3]     // Catch:{ Exception -> 0x010e }
                r2.<init>(r7)     // Catch:{ Exception -> 0x010e }
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010e }
                r7.<init>()     // Catch:{ Exception -> 0x010e }
                java.lang.String r3 = "URL is "
                r7.append(r3)     // Catch:{ Exception -> 0x010e }
                java.lang.String r3 = r2.toString()     // Catch:{ Exception -> 0x010e }
                r7.append(r3)     // Catch:{ Exception -> 0x010e }
                java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x010e }
                com.paytm.pgsdk.PaytmUtility.debugLog(r7)     // Catch:{ Exception -> 0x010e }
                java.net.URLConnection r7 = r2.openConnection()     // Catch:{ Exception -> 0x010e }
                java.lang.Object r7 = com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(r7)     // Catch:{ Exception -> 0x010e }
                java.net.URLConnection r7 = (java.net.URLConnection) r7     // Catch:{ Exception -> 0x010e }
                java.lang.String r1 = "New Connection is created."
                com.paytm.pgsdk.PaytmUtility.debugLog(r1)     // Catch:{ Exception -> 0x010c }
                java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x010c }
                boolean r1 = android.webkit.URLUtil.isHttpsUrl(r1)     // Catch:{ Exception -> 0x010c }
                if (r1 == 0) goto L_0x005f
                java.lang.String r1 = "Https url"
                com.paytm.pgsdk.PaytmUtility.debugLog(r1)     // Catch:{ Exception -> 0x010c }
                java.lang.String r1 = "Setting SSLSocketFactory to connection..."
                com.paytm.pgsdk.PaytmUtility.debugLog(r1)     // Catch:{ Exception -> 0x010c }
                r1 = r7
                javax.net.ssl.HttpsURLConnection r1 = (javax.net.ssl.HttpsURLConnection) r1     // Catch:{ Exception -> 0x010c }
                com.paytm.pgsdk.PaytmSSLSocketFactory r2 = new com.paytm.pgsdk.PaytmSSLSocketFactory     // Catch:{ Exception -> 0x010c }
                com.paytm.pgsdk.PaytmPGActivity r3 = com.paytm.pgsdk.PaytmPGActivity.this     // Catch:{ Exception -> 0x010c }
                com.paytm.pgsdk.PaytmPGService r4 = com.paytm.pgsdk.PaytmPGService.getService()     // Catch:{ Exception -> 0x010c }
                com.paytm.pgsdk.PaytmClientCertificate r4 = r4.mCertificate     // Catch:{ Exception -> 0x010c }
                r2.<init>(r3, r4)     // Catch:{ Exception -> 0x010c }
                r1.setSSLSocketFactory(r2)     // Catch:{ Exception -> 0x010c }
                java.lang.String r1 = "SSLSocketFactory is set to connection."
                com.paytm.pgsdk.PaytmUtility.debugLog(r1)     // Catch:{ Exception -> 0x010c }
            L_0x005f:
                r1 = 1
                r7.setDoOutput(r1)     // Catch:{ Exception -> 0x010c }
                r1 = r7
                java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x010c }
                java.lang.String r2 = "POST"
                r1.setRequestMethod(r2)     // Catch:{ Exception -> 0x010c }
                com.paytm.pgsdk.PaytmPGActivity r1 = com.paytm.pgsdk.PaytmPGActivity.this     // Catch:{ Exception -> 0x010c }
                android.os.Bundle r1 = r1.mParams     // Catch:{ Exception -> 0x010c }
                java.lang.String r1 = com.paytm.pgsdk.PaytmUtility.getStringFromBundle(r1)     // Catch:{ Exception -> 0x010c }
                if (r1 == 0) goto L_0x011a
                int r2 = r1.length()     // Catch:{ Exception -> 0x010c }
                if (r2 <= 0) goto L_0x011a
                java.lang.String r2 = "Getting the output stream to post"
                com.paytm.pgsdk.PaytmUtility.debugLog(r2)     // Catch:{ Exception -> 0x010c }
                java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ Exception -> 0x010c }
                java.io.OutputStream r3 = r7.getOutputStream()     // Catch:{ Exception -> 0x010c }
                r2.<init>(r3)     // Catch:{ Exception -> 0x010c }
                java.lang.String r3 = "posting......"
                com.paytm.pgsdk.PaytmUtility.debugLog(r3)     // Catch:{ Exception -> 0x010c }
                r2.print(r1)     // Catch:{ Exception -> 0x010c }
                r2.close()     // Catch:{ Exception -> 0x010c }
                java.lang.String r1 = "posted parameters and closing output stream"
                com.paytm.pgsdk.PaytmUtility.debugLog(r1)     // Catch:{ Exception -> 0x010c }
                r1 = r7
                java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x010c }
                int r1 = r1.getResponseCode()     // Catch:{ Exception -> 0x010c }
                r2 = r7
                java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Exception -> 0x010c }
                java.lang.String r2 = r2.getResponseMessage()     // Catch:{ Exception -> 0x010c }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010c }
                r3.<init>()     // Catch:{ Exception -> 0x010c }
                java.lang.String r4 = "Response code is "
                r3.append(r4)     // Catch:{ Exception -> 0x010c }
                r3.append(r1)     // Catch:{ Exception -> 0x010c }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x010c }
                com.paytm.pgsdk.PaytmUtility.debugLog(r3)     // Catch:{ Exception -> 0x010c }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010c }
                r3.<init>()     // Catch:{ Exception -> 0x010c }
                java.lang.String r4 = "Response Message is "
                r3.append(r4)     // Catch:{ Exception -> 0x010c }
                r3.append(r2)     // Catch:{ Exception -> 0x010c }
                java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x010c }
                com.paytm.pgsdk.PaytmUtility.debugLog(r2)     // Catch:{ Exception -> 0x010c }
                r2 = 200(0xc8, float:2.8E-43)
                if (r1 != r2) goto L_0x011a
                java.lang.String r1 = "Getting the input stream to read response"
                com.paytm.pgsdk.PaytmUtility.debugLog(r1)     // Catch:{ Exception -> 0x010c }
                java.util.Scanner r1 = new java.util.Scanner     // Catch:{ Exception -> 0x010c }
                java.io.InputStream r2 = r7.getInputStream()     // Catch:{ Exception -> 0x010c }
                r1.<init>(r2)     // Catch:{ Exception -> 0x010c }
                java.lang.String r2 = "reading......"
                com.paytm.pgsdk.PaytmUtility.debugLog(r2)     // Catch:{ Exception -> 0x010c }
            L_0x00e8:
                boolean r2 = r1.hasNextLine()     // Catch:{ Exception -> 0x010c }
                if (r2 == 0) goto L_0x0103
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010c }
                r2.<init>()     // Catch:{ Exception -> 0x010c }
                r2.append(r0)     // Catch:{ Exception -> 0x010c }
                java.lang.String r3 = r1.nextLine()     // Catch:{ Exception -> 0x010c }
                r2.append(r3)     // Catch:{ Exception -> 0x010c }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x010c }
                r0 = r2
                goto L_0x00e8
            L_0x0103:
                r1.close()     // Catch:{ Exception -> 0x010c }
                java.lang.String r1 = "read response and closing input stream"
                com.paytm.pgsdk.PaytmUtility.debugLog(r1)     // Catch:{ Exception -> 0x010c }
                goto L_0x011a
            L_0x010c:
                r1 = move-exception
                goto L_0x0112
            L_0x010e:
                r7 = move-exception
                r5 = r1
                r1 = r7
                r7 = r5
            L_0x0112:
                java.lang.String r2 = "Some exception occurred while making client authentication."
                com.paytm.pgsdk.PaytmUtility.debugLog(r2)     // Catch:{ all -> 0x012f }
                com.paytm.pgsdk.PaytmUtility.printStackTrace(r1)     // Catch:{ all -> 0x012f }
            L_0x011a:
                if (r7 == 0) goto L_0x0124
                java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ Exception -> 0x0122 }
                r7.disconnect()     // Catch:{ Exception -> 0x0122 }
                goto L_0x0124
            L_0x0122:
                r7 = move-exception
                goto L_0x012a
            L_0x0124:
                java.lang.String r7 = "connection is disconnected"
                com.paytm.pgsdk.PaytmUtility.debugLog(r7)     // Catch:{ Exception -> 0x0122 }
                goto L_0x012d
            L_0x012a:
                com.paytm.pgsdk.PaytmUtility.printStackTrace(r7)     // Catch:{ all -> 0x012f }
            L_0x012d:
                monitor-exit(r6)
                return r0
            L_0x012f:
                r7 = move-exception
                monitor-exit(r6)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.paytm.pgsdk.PaytmPGActivity.AuthenticatorTask.doInBackground(java.lang.String[]):java.lang.String");
        }

        /* access modifiers changed from: protected */
        public synchronized void onPostExecute(String str) {
            if (str != null) {
                try {
                    if (!str.equalsIgnoreCase("")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Response is ");
                        sb.append(str);
                        PaytmUtility.debugLog(sb.toString());
                        if (PaytmPGActivity.this.extractJSON(str)) {
                            PaytmPGActivity.this.mbChecksumGenerated = true;
                            PaytmPGActivity.this.mWV.setVisibility(0);
                            PaytmPGActivity.this.mWV.postUrl(PaytmPGService.getService().mPGURL, PaytmUtility.getURLEncodedStringFromBundle(PaytmPGActivity.this.mParams).getBytes());
                            PaytmPGActivity.this.mWV.requestFocus(130);
                        } else {
                            PaytmPGActivity.this.finish();
                            PaytmPaymentTransactionCallback paytmPaymentTransactionCallback = PaytmPGService.getService().getmPaymentTransactionCallback();
                            if (paytmPaymentTransactionCallback != null) {
                                paytmPaymentTransactionCallback.clientAuthenticationFailed("Client authentication failed. Please try again later.");
                            }
                        }
                    }
                } catch (Exception e) {
                    PaytmPGActivity.this.finish();
                    PaytmPaymentTransactionCallback paytmPaymentTransactionCallback2 = PaytmPGService.getService().getmPaymentTransactionCallback();
                    if (paytmPaymentTransactionCallback2 != null) {
                        paytmPaymentTransactionCallback2.someUIErrorOccurred("Some UI error occured in WebView of Payment Gateway Activity");
                    }
                    PaytmUtility.debugLog("Some exception occurred while posting data to PG Server.");
                    PaytmUtility.printStackTrace(e);
                } finally {
                }
            }
            PaytmPGActivity.this.finish();
            PaytmPaymentTransactionCallback paytmPaymentTransactionCallback3 = PaytmPGService.getService().getmPaymentTransactionCallback();
            if (paytmPaymentTransactionCallback3 != null) {
                paytmPaymentTransactionCallback3.clientAuthenticationFailed("Client authentication failed due to server error. Please try again later.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRestore(Bundle bundle) {
        if (SaveReferences.getInstance().isProduction()) {
            PaytmPGService.getProductionService();
        } else {
            PaytmPGService.getStagingService();
        }
        PaytmUtility.debugLog("Came in onRestoreInstanceState");
        this.mbHideHeader = bundle.getBoolean("HIDE_HEADER");
        this.mbSendAllChecksumResponseParametersToPGServer = bundle.getBoolean("SEND_ALL_CHECKSUM_RESPONSE_PARAMETERS_TO_PG_SERVER");
        this.mParams = bundle.getBundle("Parameters");
        this.paramsString = bundle.getString("Parameters_String");
        this.urlString = bundle.getString(PaytmConstants.URL_STRING);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PaytmUtility.debugLog("Came in onSaveInstanceState");
        bundle.putBoolean("HIDE_HEADER", this.mbHideHeader);
        bundle.putBoolean("SEND_ALL_CHECKSUM_RESPONSE_PARAMETERS_TO_PG_SERVER", this.mbSendAllChecksumResponseParametersToPGServer);
        bundle.putBundle("Parameters", this.mParams);
        bundle.putString("Parameters_String", this.paramsString);
        bundle.putString(PaytmConstants.URL_STRING, this.urlString);
    }

    /* access modifiers changed from: protected */
    public synchronized void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            onRestore(bundle);
        }
        if (initUI()) {
            startTransaction();
        } else {
            finish();
            PaytmPaymentTransactionCallback paytmPaymentTransactionCallback = PaytmPGService.getService().getmPaymentTransactionCallback();
            if (paytmPaymentTransactionCallback != null) {
                paytmPaymentTransactionCallback.someUIErrorOccurred("Some error occured while initializing UI of Payment Gateway Activity");
            }
        }
    }

    private synchronized boolean initUI() {
        try {
            if (getIntent() != null) {
                this.mbHideHeader = getIntent().getBooleanExtra("HIDE_HEADER", false);
                this.mbSendAllChecksumResponseParametersToPGServer = getIntent().getBooleanExtra("SEND_ALL_CHECKSUM_RESPONSE_PARAMETERS_TO_PG_SERVER", false);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Hide Header ");
            sb.append(this.mbHideHeader);
            PaytmUtility.debugLog(sb.toString());
            PaytmUtility.debugLog("Initializing the UI of Transaction Page...");
            RelativeLayout relativeLayout = new RelativeLayout(this);
            RelativeLayout relativeLayout2 = new RelativeLayout(this);
            relativeLayout2.setLayoutParams(new LayoutParams(-1, -2));
            relativeLayout2.setId(1);
            relativeLayout2.setBackgroundColor(Color.parseColor("#bdbdbd"));
            Button button = new Button(this, null, 16842825);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(15);
            layoutParams.leftMargin = (int) (getResources().getDisplayMetrics().density * 5.0f);
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PaytmUtility.debugLog("User pressed back button which is present in Header Bar.");
                    PaytmPGActivity.this.cancelTransaction();
                }
            });
            button.setLayoutParams(layoutParams);
            button.setText("Cancel");
            TextView textView = new TextView(this);
            LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            layoutParams2.addRule(13);
            textView.setLayoutParams(layoutParams2);
            textView.setTextColor(-16777216);
            textView.setText("Paytm Payments");
            relativeLayout2.addView(button);
            relativeLayout2.addView(textView);
            RelativeLayout relativeLayout3 = new RelativeLayout(this);
            LayoutParams layoutParams3 = new LayoutParams(-1, -1);
            layoutParams3.addRule(3, relativeLayout2.getId());
            relativeLayout3.setLayoutParams(layoutParams3);
            this.mWV = new PaytmWebView(this, this.mParams);
            this.mWV.setVisibility(8);
            this.mWV.setLayoutParams(new LayoutParams(-1, -1));
            this.mProgress = new ProgressBar(this, null, 16842873);
            LayoutParams layoutParams4 = new LayoutParams(-2, -2);
            layoutParams4.addRule(13);
            this.mProgress.setLayoutParams(layoutParams4);
            relativeLayout3.addView(this.mWV);
            relativeLayout3.addView(this.mProgress);
            relativeLayout.addView(relativeLayout2);
            relativeLayout.addView(relativeLayout3);
            if (this.mbHideHeader) {
                relativeLayout2.setVisibility(8);
            }
            requestWindowFeature(1);
            setContentView(relativeLayout);
            PaytmUtility.debugLog("Initialized UI of Transaction Page.");
        } catch (Exception e) {
            PaytmUtility.debugLog("Some exception occurred while initializing UI.");
            PaytmUtility.printStackTrace(e);
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public synchronized void onDestroy() {
        super.onDestroy();
        try {
            if (this.mAuthenticator != null) {
                this.mAuthenticator.cancel(true);
            }
            PaytmPGService.getService().stopService();
        } catch (Exception e) {
            PaytmPGService.getService().stopService();
            PaytmUtility.debugLog("Some exception occurred while destroying the PaytmPGActivity.");
            PaytmUtility.printStackTrace(e);
        }
        return;
    }

    /* access modifiers changed from: private */
    public synchronized boolean extractJSON(String str) {
        boolean z;
        z = false;
        try {
            PaytmUtility.debugLog("Parsing JSON");
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            PaytmUtility.debugLog("Appending Key Value pairs");
            StringBuilder sb = new StringBuilder();
            sb.append("Send All Checksum Response Parameters to PG ");
            sb.append(this.mbSendAllChecksumResponseParametersToPGServer);
            PaytmUtility.debugLog(sb.toString());
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                String string = jSONObject.getString(str2);
                String trim = str2.trim();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(trim);
                sb2.append(" = ");
                sb2.append(string);
                PaytmUtility.debugLog(sb2.toString());
                if (trim.equals("CHECKSUMHASH")) {
                    this.mParams.putString(trim, string);
                } else if (this.mbSendAllChecksumResponseParametersToPGServer) {
                    this.mParams.putString(trim, string);
                }
                if (trim.equals("payt_STATUS") && string.equals("1")) {
                    z = true;
                }
            }
        } catch (Exception e) {
            PaytmUtility.debugLog("Some exception occurred while extracting the checksum from CAS Response.");
            PaytmUtility.printStackTrace(e);
        }
        return z;
    }

    private synchronized void startTransaction() {
        PaytmUtility.debugLog("Starting the Process...");
        if (!(getIntent() == null || getIntent().getBundleExtra("Parameters") == null)) {
            this.mParams = getIntent().getBundleExtra("Parameters");
            if (this.mParams != null && this.mParams.size() > 0) {
                PaytmUtility.debugLog("Starting the Client Authentication...");
                this.mAuthenticator = new AuthenticatorTask();
                if (PaytmPGService.getService() != null) {
                    this.mWV.setVisibility(0);
                    this.mWV.postUrl(PaytmPGService.getService().mPGURL, PaytmUtility.getURLEncodedStringFromBundle(this.mParams).getBytes());
                    this.mWV.requestFocus(130);
                    if (PaytmPGService.getService().mOrder.getRequestParamMap().get("prenotificationurl") != null) {
                        Intent intent = new Intent(getApplicationContext(), IntentServicePreNotification.class);
                        intent.putExtra("url", (String) PaytmPGService.getService().mOrder.getRequestParamMap().get("prenotificationurl"));
                        getApplicationContext().startService(intent);
                    }
                }
            }
        }
    }

    public synchronized boolean onKeyDown(int i, KeyEvent keyEvent) {
        StringBuilder sb = new StringBuilder();
        sb.append("User pressed key and key code is ");
        sb.append(i);
        PaytmUtility.debugLog(sb.toString());
        if (i == 4) {
            PaytmUtility.debugLog("User pressed hard key back button");
            cancelTransaction();
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* access modifiers changed from: private */
    public synchronized void cancelTransaction() {
        if (!this.mbIsCancellingRequest) {
            PaytmUtility.debugLog("Displaying Confirmation Dialog");
            Builder builder = new Builder(this);
            builder.setTitle("Cancel Transaction");
            builder.setMessage("Are you sure you want to cancel transaction");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    PaytmPGActivity.this.onBackPressed();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    PaytmPGActivity.this.mDlg.dismiss();
                }
            });
            this.mDlg = builder.create();
            this.mDlg.show();
        }
    }

    public void onBackPressed() {
        PaytmPGService.getService().getmPaymentTransactionCallback().onBackPressedCancelTransaction();
        super.onBackPressed();
    }
}
