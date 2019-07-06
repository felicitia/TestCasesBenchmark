package com.paytm.pgsdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import java.util.Map.Entry;

public class PaytmPGService {
    private static volatile PaytmPGService mService;
    protected volatile String mCancelTransactionURL;
    public volatile PaytmClientCertificate mCertificate;
    public volatile PaytmOrder mOrder;
    protected volatile String mPGURL;
    private volatile PaytmPaymentTransactionCallback mPaymentTransactionCallback;
    private volatile String mStatusQueryURL;
    private volatile boolean mbServiceRunning;

    protected static synchronized PaytmPGService getService() {
        PaytmPGService paytmPGService;
        synchronized (PaytmPGService.class) {
            try {
                if (mService == null) {
                    PaytmUtility.debugLog("Creating an instance of Paytm PG Service...");
                    mService = new PaytmPGService();
                    PaytmUtility.debugLog("Created a new instance of Paytm PG Service.");
                }
            } catch (Exception e) {
                PaytmUtility.printStackTrace(e);
            }
            paytmPGService = mService;
        }
        return paytmPGService;
    }

    public static synchronized PaytmPGService getStagingService() {
        PaytmPGService service;
        synchronized (PaytmPGService.class) {
            service = getService();
            service.mStatusQueryURL = "https://pguat.paytm.com/oltp/HANDLER_INTERNAL/TXNSTATUS";
            service.mCancelTransactionURL = "https://pguat.paytm.com/oltp/HANDLER_INTERNAL/CANCEL_TXN";
            service.mPGURL = "https://pguat.paytm.com/oltp-web/processTransaction";
            SaveReferences.getInstance().setProduction(false);
        }
        return service;
    }

    public static synchronized PaytmPGService getProductionService() {
        PaytmPGService service;
        synchronized (PaytmPGService.class) {
            service = getService();
            service.mStatusQueryURL = "https://secure.paytm.in/oltp/HANDLER_INTERNAL/TXNSTATUS";
            service.mCancelTransactionURL = "https://secure.paytm.in/oltp/HANDLER_INTERNAL/CANCEL_TXN";
            service.mPGURL = "https://secure.paytm.in/oltp-web/processTransaction";
            SaveReferences.getInstance().setProduction(true);
        }
        return service;
    }

    public synchronized void initialize(PaytmOrder paytmOrder, PaytmClientCertificate paytmClientCertificate) {
        this.mOrder = paytmOrder;
        this.mCertificate = paytmClientCertificate;
    }

    public void enableLog(Context context) {
        ApplicationInfo applicationinfo = getApplicationinfo(context);
        boolean z = false;
        if (applicationinfo != null) {
            int i = applicationinfo.flags & 2;
            applicationinfo.flags = i;
            if (i != 0) {
                z = true;
            }
            Log.setEnableDebugLog(z);
            return;
        }
        Log.setEnableDebugLog(false);
    }

    public synchronized void startPaymentTransaction(Context context, boolean z, boolean z2, PaytmPaymentTransactionCallback paytmPaymentTransactionCallback) {
        try {
            enableLog(context);
            if (!PaytmUtility.isNetworkAvailable(context)) {
                stopService();
                paytmPaymentTransactionCallback.networkNotAvailable();
            } else if (!this.mbServiceRunning) {
                Bundle bundle = new Bundle();
                if (this.mOrder != null) {
                    for (Entry entry : this.mOrder.getRequestParamMap().entrySet()) {
                        String str = (String) entry.getKey();
                        String str2 = (String) entry.getValue();
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append(" = ");
                        sb.append(str2);
                        PaytmUtility.debugLog(sb.toString());
                        bundle.putString((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                PaytmUtility.debugLog("Starting the Service...");
                Intent intent = new Intent(context, PaytmPGActivity.class);
                intent.putExtra("Parameters", bundle);
                intent.putExtra("HIDE_HEADER", z);
                intent.putExtra("SEND_ALL_CHECKSUM_RESPONSE_PARAMETERS_TO_PG_SERVER", z2);
                this.mbServiceRunning = true;
                this.mPaymentTransactionCallback = paytmPaymentTransactionCallback;
                SaveReferences.getInstance().setPaytmPaymentTransactionCallback(paytmPaymentTransactionCallback);
                ((Activity) context).startActivity(intent);
                PaytmUtility.debugLog("Service Started.");
            } else {
                PaytmUtility.debugLog("Service is already running.");
            }
        } catch (Exception e) {
            stopService();
            PaytmUtility.printStackTrace(e);
        }
        return;
    }

    /* access modifiers changed from: protected */
    public synchronized void stopService() {
        mService = null;
        PaytmUtility.debugLog("Service Stopped.");
    }

    private ApplicationInfo getApplicationinfo(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PaytmPaymentTransactionCallback getmPaymentTransactionCallback() {
        if (this.mPaymentTransactionCallback == null) {
            return SaveReferences.getInstance().getPaytmPaymentTransactionCallback();
        }
        return this.mPaymentTransactionCallback;
    }
}
