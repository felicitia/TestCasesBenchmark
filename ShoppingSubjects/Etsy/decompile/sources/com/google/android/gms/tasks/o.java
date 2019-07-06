package com.google.android.gms.tasks;

final class o implements Runnable {
    private final /* synthetic */ n a;

    o(n nVar) {
        this.a = nVar;
    }

    public final void run() {
        synchronized (this.a.b) {
            if (this.a.c != null) {
                this.a.c.a();
            }
        }
    }
}
