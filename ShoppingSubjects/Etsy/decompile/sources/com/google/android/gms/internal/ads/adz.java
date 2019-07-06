package com.google.android.gms.internal.ads;

final class adz implements Runnable {
    private final /* synthetic */ amf a;
    private final /* synthetic */ acz b;

    adz(acz acz, amf amf) {
        this.b = acz;
        this.a = amf;
    }

    public final void run() {
        try {
            this.b.c.put(this.a);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }
}
