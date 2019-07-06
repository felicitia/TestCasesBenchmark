package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

public final class zzbfx extends CustomTabsServiceConnection {
    private WeakReference<abg> zzedz;

    public zzbfx(abg abg) {
        this.zzedz = new WeakReference<>(abg);
    }

    public final void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        abg abg = (abg) this.zzedz.get();
        if (abg != null) {
            abg.a(customTabsClient);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        abg abg = (abg) this.zzedz.get();
        if (abg != null) {
            abg.a();
        }
    }
}
