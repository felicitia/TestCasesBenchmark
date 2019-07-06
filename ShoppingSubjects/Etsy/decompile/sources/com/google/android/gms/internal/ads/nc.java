package com.google.android.gms.internal.ads;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;
import java.util.Map;

final class nc implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ mz e;

    nc(mz mzVar, String str, String str2, String str3, String str4) {
        this.e = mzVar;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public final void run() {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "precacheCanceled");
        hashMap.put("src", this.a);
        if (!TextUtils.isEmpty(this.b)) {
            hashMap.put("cachedSrc", this.b);
        }
        hashMap.put("type", mz.b(this.c));
        hashMap.put(ResponseConstants.REASON, this.c);
        if (!TextUtils.isEmpty(this.d)) {
            hashMap.put("message", this.d);
        }
        this.e.a("onPrecacheEvent", (Map<String, String>) hashMap);
    }
}
