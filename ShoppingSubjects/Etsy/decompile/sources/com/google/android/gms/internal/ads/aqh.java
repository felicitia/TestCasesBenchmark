package com.google.android.gms.internal.ads;

import android.content.Context;

@bu
public final class aqh {
    private final Object a = new Object();
    private aqo b;

    public final aqo a(Context context, zzang zzang) {
        aqo aqo;
        synchronized (this.a) {
            if (this.b == null) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
                this.b = new aqo(context, zzang, (String) ajh.f().a(akl.a));
            }
            aqo = this.b;
        }
        return aqo;
    }
}
