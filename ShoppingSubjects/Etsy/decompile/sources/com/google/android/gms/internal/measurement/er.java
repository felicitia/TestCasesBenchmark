package com.google.android.gms.internal.measurement;

final class er implements Runnable {
    private final /* synthetic */ long a;
    private final /* synthetic */ eo b;

    er(eo eoVar, long j) {
        this.b = eoVar;
        this.a = j;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
