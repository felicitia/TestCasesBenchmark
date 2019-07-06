package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class dt implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ zzeb b;
    private final /* synthetic */ dq c;

    dt(dq dqVar, AtomicReference atomicReference, zzeb zzeb) {
        this.c = dqVar;
        this.a = atomicReference;
        this.b = zzeb;
    }

    public final void run() {
        AtomicReference atomicReference;
        synchronized (this.a) {
            try {
                zzfa d = this.c.b;
                if (d == null) {
                    this.c.r().h_().a("Failed to get app instance id");
                    this.a.notify();
                    return;
                }
                this.a.set(d.zzc(this.b));
                String str = (String) this.a.get();
                if (str != null) {
                    this.c.f().a(str);
                    this.c.s().j.a(str);
                }
                this.c.J();
                atomicReference = this.a;
                atomicReference.notify();
            } catch (RemoteException e) {
                try {
                    this.c.r().h_().a("Failed to get app instance id", e);
                    atomicReference = this.a;
                } catch (Throwable th) {
                    this.a.notify();
                    throw th;
                }
            }
        }
    }
}
