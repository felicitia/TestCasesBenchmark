package com.crittercism.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.SparseArray;
import java.text.ParseException;

public enum a {
    MOBILE(0),
    WIFI(1),
    UNKNOWN(2),
    NOT_CONNECTED(3);
    
    private static SparseArray<a> f;
    int e;

    static {
        SparseArray<a> sparseArray = new SparseArray<>();
        f = sparseArray;
        sparseArray.put(0, MOBILE);
        f.put(1, WIFI);
    }

    private a(int i) {
        this.e = i;
    }

    public static a a(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            return UNKNOWN;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return NOT_CONNECTED;
        }
        a aVar = (a) f.get(activeNetworkInfo.getType());
        if (aVar == null) {
            aVar = UNKNOWN;
        }
        return aVar;
    }

    public static a a(int i) {
        a[] values;
        for (a aVar : values()) {
            if (aVar.e == i) {
                return aVar;
            }
        }
        StringBuilder sb = new StringBuilder("Unknown status code: ");
        sb.append(Integer.toString(i));
        throw new ParseException(sb.toString(), 0);
    }
}
