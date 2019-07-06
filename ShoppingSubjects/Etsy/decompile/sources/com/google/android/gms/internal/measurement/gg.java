package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class gg extends ContentObserver {
    private final /* synthetic */ gf a;

    gg(gf gfVar, Handler handler) {
        this.a = gfVar;
        super(null);
    }

    public final void onChange(boolean z) {
        this.a.b();
        this.a.d();
    }
}
