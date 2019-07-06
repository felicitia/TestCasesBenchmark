package com.google.android.gms.internal.ads;

import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;

final class nb implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ int c;
    private final /* synthetic */ mz d;

    nb(mz mzVar, String str, String str2, int i) {
        this.d = mzVar;
        this.a = str;
        this.b = str2;
        this.c = i;
    }

    public final void run() {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "precacheComplete");
        hashMap.put("src", this.a);
        hashMap.put("cachedSrc", this.b);
        hashMap.put("totalBytes", Integer.toString(this.c));
        this.d.a("onPrecacheEvent", (Map<String, String>) hashMap);
    }
}
