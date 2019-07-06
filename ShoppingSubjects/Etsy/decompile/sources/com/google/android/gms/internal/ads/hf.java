package com.google.android.gms.internal.ads;

import android.content.Context;

final class hf implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ hd b;

    hf(hd hdVar, Context context) {
        this.b = hdVar;
        this.a = context;
    }

    public final void run() {
        synchronized (this.b.b) {
            this.b.d = hd.d(this.a);
            this.b.b.notifyAll();
        }
    }
}
