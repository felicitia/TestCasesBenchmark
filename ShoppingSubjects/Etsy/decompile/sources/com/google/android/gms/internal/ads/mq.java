package com.google.android.gms.internal.ads;

@bu
final class mq implements Runnable {
    private zzapi a;
    private boolean b = false;

    mq(zzapi zzapi) {
        this.a = zzapi;
    }

    private final void c() {
        hd.a.removeCallbacks(this);
        hd.a.postDelayed(this, 250);
    }

    public final void a() {
        this.b = true;
    }

    public final void b() {
        this.b = false;
        c();
    }

    public final void run() {
        if (!this.b) {
            this.a.zzte();
            c();
        }
    }
}
