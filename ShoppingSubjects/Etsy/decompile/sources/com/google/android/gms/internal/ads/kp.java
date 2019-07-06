package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

final /* synthetic */ class kp implements Runnable {
    private final le a;
    private final kt b;

    kp(le leVar, kt ktVar) {
        this.a = leVar;
        this.b = ktVar;
    }

    public final void run() {
        Throwable e;
        le leVar = this.a;
        try {
            leVar.b(this.b.get());
        } catch (ExecutionException e2) {
            e = e2.getCause();
            leVar.a(e);
        } catch (InterruptedException e3) {
            e = e3;
            Thread.currentThread().interrupt();
            leVar.a(e);
        } catch (Exception e4) {
            leVar.a(e4);
        }
    }
}
