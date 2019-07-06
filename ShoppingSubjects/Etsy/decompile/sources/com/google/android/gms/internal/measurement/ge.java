package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class ge extends ContentObserver {
    ge(Handler handler) {
        super(null);
    }

    public final void onChange(boolean z) {
        gd.e.set(true);
    }
}
