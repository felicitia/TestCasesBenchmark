package com.google.android.gms.internal.stable;

import android.database.ContentObserver;
import android.os.Handler;

final class f extends ContentObserver {
    f(Handler handler) {
        super(null);
    }

    public final void onChange(boolean z) {
        e.e.set(true);
    }
}
