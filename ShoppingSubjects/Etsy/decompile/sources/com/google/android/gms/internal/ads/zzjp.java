package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.a;

@bu
public final class zzjp extends zzlb {
    private final a zzvo;

    public zzjp(a aVar) {
        this.zzvo = aVar;
    }

    public final a getAppEventListener() {
        return this.zzvo;
    }

    public final void onAppEvent(String str, String str2) {
        this.zzvo.a(str, str2);
    }
}
