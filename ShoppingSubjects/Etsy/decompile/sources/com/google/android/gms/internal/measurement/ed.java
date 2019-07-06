package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class ed implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ zzeb b;
    private final /* synthetic */ boolean c;
    private final /* synthetic */ dq d;

    ed(dq dqVar, AtomicReference atomicReference, zzeb zzeb, boolean z) {
        this.d = dqVar;
        this.a = atomicReference;
        this.b = zzeb;
        this.c = z;
    }

    public final void run() {
        AtomicReference atomicReference;
        synchronized (this.a) {
            try {
                zzfa d2 = this.d.b;
                if (d2 == null) {
                    this.d.r().h_().a("Failed to get user properties");
                    this.a.notify();
                    return;
                }
                this.a.set(d2.zza(this.b, this.c));
                this.d.J();
                atomicReference = this.a;
                atomicReference.notify();
            } catch (RemoteException e) {
                try {
                    this.d.r().h_().a("Failed to get user properties", e);
                    atomicReference = this.a;
                } catch (Throwable th) {
                    this.a.notify();
                    throw th;
                }
            }
        }
    }
}
