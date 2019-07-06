package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.h.b;

public final class zzse extends zzrp {
    private final b zzblk;

    public zzse(b bVar) {
        this.zzblk = bVar;
    }

    public final void onUnconfirmedClickCancelled() {
        this.zzblk.a();
    }

    public final void onUnconfirmedClickReceived(String str) {
        this.zzblk.a(str);
    }
}
