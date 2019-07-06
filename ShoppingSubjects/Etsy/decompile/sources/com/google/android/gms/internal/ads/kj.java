package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

final /* synthetic */ class kj implements Runnable {
    private final kf a;
    private final kt b;

    kj(kf kfVar, kt ktVar) {
        this.a = kfVar;
        this.b = ktVar;
    }

    public final void run() {
        Throwable e;
        kf kfVar = this.a;
        try {
            kfVar.a(this.b.get());
        } catch (ExecutionException e2) {
            e = e2.getCause();
            kfVar.a(e);
        } catch (InterruptedException e3) {
            e = e3;
            Thread.currentThread().interrupt();
            kfVar.a(e);
        } catch (Exception e4) {
            e = e4;
            kfVar.a(e);
        }
    }
}
