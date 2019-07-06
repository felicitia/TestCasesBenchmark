package com.google.android.gms.tasks;

final class q implements Runnable {
    private final /* synthetic */ f a;
    private final /* synthetic */ p b;

    q(p pVar, f fVar) {
        this.b = pVar;
        this.a = fVar;
    }

    public final void run() {
        synchronized (this.b.b) {
            if (this.b.c != null) {
                this.b.c.onComplete(this.a);
            }
        }
    }
}
