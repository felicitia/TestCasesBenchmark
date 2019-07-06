package com.google.android.gms.internal.ads;

final class ark implements Runnable {
    private final /* synthetic */ kt a;
    private final /* synthetic */ ari b;

    ark(ari ari, kt ktVar) {
        this.b = ari;
        this.a = ktVar;
    }

    public final void run() {
        for (kt ktVar : this.b.k.keySet()) {
            if (ktVar != this.a) {
                ((arc) this.b.k.get(ktVar)).a();
            }
        }
    }
}
