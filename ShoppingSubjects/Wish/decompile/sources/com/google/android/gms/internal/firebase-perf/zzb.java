package com.google.android.gms.internal.firebase-perf;

import android.database.ContentObserver;
import android.os.Handler;

final class zzb extends ContentObserver {
    zzb(Handler handler) {
        super(null);
    }

    public final void onChange(boolean z) {
        zza.zzd.set(true);
    }
}
