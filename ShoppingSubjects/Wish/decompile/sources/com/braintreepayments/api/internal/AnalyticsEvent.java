package com.braintreepayments.api.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.braintreepayments.api.Venmo;
import com.paypal.android.sdk.onetouch.core.PayPalOneTouchCore;
import org.json.JSONException;
import org.json.JSONObject;

public class AnalyticsEvent {
    String event;
    int id;
    JSONObject metadata;
    long timestamp;

    public AnalyticsEvent(Context context, String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append("android.");
        sb.append(str2);
        sb.append(".");
        sb.append(str3);
        this.event = sb.toString();
        this.timestamp = System.currentTimeMillis() / 1000;
        this.metadata = new JSONObject();
        try {
            this.metadata.put("sessionId", str).put("deviceNetworkType", getNetworkType(context)).put("userInterfaceOrientation", getUserOrientation(context)).put("merchantAppVersion", getAppVersion(context)).put("paypalInstalled", isPayPalInstalled(context)).put("venmoInstalled", Venmo.isVenmoInstalled(context));
        } catch (JSONException unused) {
        }
    }

    public AnalyticsEvent() {
        this.metadata = new JSONObject();
    }

    public String getIntegrationType() {
        String[] split = this.event.split("\\.");
        return split.length > 1 ? split[1] : "";
    }

    private String getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        String typeName = activeNetworkInfo != null ? activeNetworkInfo.getTypeName() : null;
        return typeName == null ? "none" : typeName;
    }

    private String getUserOrientation(Context context) {
        switch (context.getResources().getConfiguration().orientation) {
            case 1:
                return "Portrait";
            case 2:
                return "Landscape";
            default:
                return "Unknown";
        }
    }

    private String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException unused) {
            return "VersionUnknown";
        }
    }

    private boolean isPayPalInstalled(Context context) {
        try {
            Class.forName(PayPalOneTouchCore.class.getName());
            return PayPalOneTouchCore.isWalletAppInstalled(context);
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }
}
