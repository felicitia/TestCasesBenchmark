package com.google.android.gms.internal.ads;

import android.database.ContentObserver;
import android.os.Handler;

final class aeq extends ContentObserver {
    private final /* synthetic */ zzet a;

    public aeq(zzet zzet, Handler handler) {
        this.a = zzet;
        super(handler);
    }

    public final void onChange(boolean z) {
        super.onChange(z);
        this.a.zzgb();
    }
}
