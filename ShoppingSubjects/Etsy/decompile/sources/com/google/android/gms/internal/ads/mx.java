package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;

final class mx implements Runnable {
    private final /* synthetic */ mw a;

    mx(mw mwVar) {
        this.a = mwVar;
    }

    public final void run() {
        ao.z().b(this.a);
    }
}
