package com.paytm.pgsdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import java.net.URLEncoder;

public class PaytmUtility {
    protected static synchronized String getStringFromBundle(Bundle bundle) {
        String stringBuffer;
        synchronized (PaytmUtility.class) {
            try {
                debugLog("Extracting Strings from Bundle...");
                boolean z = true;
                StringBuffer stringBuffer2 = new StringBuffer();
                for (String str : bundle.keySet()) {
                    if (z) {
                        z = false;
                    } else {
                        stringBuffer2.append("&");
                    }
                    stringBuffer2.append(str);
                    stringBuffer2.append("=");
                    stringBuffer2.append(bundle.getString(str));
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Extracted String is ");
                sb.append(stringBuffer2.toString());
                debugLog(sb.toString());
                stringBuffer = stringBuffer2.toString();
            } catch (Exception e) {
                printStackTrace(e);
                return null;
            }
        }
        return stringBuffer;
    }

    protected static synchronized String getURLEncodedStringFromBundle(Bundle bundle) {
        String stringBuffer;
        synchronized (PaytmUtility.class) {
            try {
                debugLog("Extracting Strings from Bundle...");
                boolean z = true;
                StringBuffer stringBuffer2 = new StringBuffer();
                for (String str : bundle.keySet()) {
                    if (z) {
                        z = false;
                    } else {
                        stringBuffer2.append("&");
                    }
                    stringBuffer2.append(URLEncoder.encode(str, "UTF-8"));
                    stringBuffer2.append("=");
                    stringBuffer2.append(URLEncoder.encode(bundle.getString(str), "UTF-8"));
                }
                StringBuilder sb = new StringBuilder();
                sb.append("URL encoded String is ");
                sb.append(stringBuffer2.toString());
                debugLog(sb.toString());
                stringBuffer = stringBuffer2.toString();
            } catch (Exception e) {
                printStackTrace(e);
                return null;
            }
        }
        return stringBuffer;
    }

    protected static synchronized void debugLog(String str) {
        synchronized (PaytmUtility.class) {
            Log.d("PGSDK", str);
        }
    }

    protected static synchronized void printStackTrace(Exception exc) {
        synchronized (PaytmUtility.class) {
            exc.printStackTrace();
        }
    }

    protected static synchronized boolean isNetworkAvailable(Context context) {
        synchronized (PaytmUtility.class) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            boolean isConnected = activeNetworkInfo.isConnected();
            return isConnected;
        }
    }
}
