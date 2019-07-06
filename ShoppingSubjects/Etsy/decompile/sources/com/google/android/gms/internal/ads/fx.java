package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

@bu
public final class fx implements fz {
    public final kt<Info> a(Context context) {
        le leVar = new le();
        ajh.a();
        if (jp.f(context)) {
            hb.a((Runnable) new fy(this, context, leVar));
        }
        return leVar;
    }

    public final kt<String> a(String str, PackageInfo packageInfo) {
        return ki.a(str);
    }
}
