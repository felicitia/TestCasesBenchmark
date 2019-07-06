package com.crittercism.internal;

import android.content.Context;
import android.net.ConnectivityManager;

public final class c {
    public ConnectivityManager a;

    public c(Context context) {
        if (context == null) {
            cm.a("Given a null Context.");
            return;
        }
        if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) == 0) {
            this.a = (ConnectivityManager) context.getSystemService("connectivity");
        } else {
            cm.a("Add android.permission.ACCESS_NETWORK_STATE to AndroidManifest.xml to get more detailed OPTMZ data");
        }
    }
}
