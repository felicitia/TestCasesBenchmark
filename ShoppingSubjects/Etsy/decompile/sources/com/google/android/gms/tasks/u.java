package com.google.android.gms.tasks;

final class u implements Runnable {
    private final /* synthetic */ f a;
    private final /* synthetic */ t b;

    u(t tVar, f fVar) {
        this.b = tVar;
        this.a = fVar;
    }

    public final void run() {
        synchronized (this.b.b) {
            if (this.b.c != null) {
                this.b.c.a(this.a.d());
            }
        }
    }
}
