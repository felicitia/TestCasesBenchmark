package com.google.android.gms.internal.ads;

public final class apt extends lk<aqd> {
    private final Object a = new Object();
    /* access modifiers changed from: private */
    public final apx b;
    private boolean c;

    public apt(apx apx) {
        this.b = apx;
    }

    public final void c() {
        synchronized (this.a) {
            if (!this.c) {
                this.c = true;
                a(new apu(this), new li());
                a(new apv(this), new apw(this));
            }
        }
    }
}
