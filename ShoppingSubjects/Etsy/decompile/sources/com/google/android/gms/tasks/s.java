package com.google.android.gms.tasks;

final class s implements Runnable {
    private final /* synthetic */ f a;
    private final /* synthetic */ r b;

    s(r rVar, f fVar) {
        this.b = rVar;
        this.a = fVar;
    }

    public final void run() {
        synchronized (this.b.b) {
            if (this.b.c != null) {
                this.b.c.a(this.a.e());
            }
        }
    }
}
