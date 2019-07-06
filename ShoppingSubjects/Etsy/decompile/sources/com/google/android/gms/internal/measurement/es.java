package com.google.android.gms.internal.measurement;

final class es implements Runnable {
    private final /* synthetic */ long a;
    private final /* synthetic */ eo b;

    es(eo eoVar, long j) {
        this.b = eoVar;
        this.a = j;
    }

    public final void run() {
        this.b.b(this.a);
    }
}
