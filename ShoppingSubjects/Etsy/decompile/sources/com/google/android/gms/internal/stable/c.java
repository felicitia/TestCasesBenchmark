package com.google.android.gms.internal.stable;

import android.database.ContentObserver;
import android.os.Handler;

final class c extends ContentObserver {
    private final /* synthetic */ d a;

    c(Handler handler, d dVar) {
        this.a = dVar;
        super(null);
    }

    public final void onChange(boolean z) {
        this.a.c.set(true);
    }
}
