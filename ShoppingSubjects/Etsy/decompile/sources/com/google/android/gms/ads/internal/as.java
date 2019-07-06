package com.google.android.gms.ads.internal;

import android.webkit.CookieManager;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import java.util.concurrent.Callable;

final class as implements Callable<String> {
    private final /* synthetic */ zzd a;

    as(zzd zzd) {
        this.a = zzd;
    }

    public final /* synthetic */ Object call() throws Exception {
        String str = "";
        if (!((Boolean) ajh.f().a(akl.cC)).booleanValue()) {
            return str;
        }
        CookieManager c = ao.g().c(this.a.zzvw.zzrt);
        return c != null ? c.getCookie("googleads.g.doubleclick.net") : str;
    }
}
