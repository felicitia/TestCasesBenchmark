package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class zzix implements Runnable {
    private final /* synthetic */ boolean zzadv;
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzik zzaqv;
    private final /* synthetic */ AtomicReference zzaqw;

    zzix(zzik zzik, AtomicReference atomicReference, zzeb zzeb, boolean z) {
        this.zzaqv = zzik;
        this.zzaqw = atomicReference;
        this.zzapd = zzeb;
        this.zzadv = z;
    }

    public final void run() {
        AtomicReference atomicReference;
        synchronized (this.zzaqw) {
            try {
                zzfa zzd = this.zzaqv.zzaqp;
                if (zzd == null) {
                    this.zzaqv.zzgi().zziv().log("Failed to get user properties");
                    this.zzaqw.notify();
                    return;
                }
                this.zzaqw.set(zzd.zza(this.zzapd, this.zzadv));
                this.zzaqv.zzcu();
                atomicReference = this.zzaqw;
                atomicReference.notify();
            } catch (RemoteException e) {
                try {
                    this.zzaqv.zzgi().zziv().zzg("Failed to get user properties", e);
                    atomicReference = this.zzaqw;
                } catch (Throwable th) {
                    this.zzaqw.notify();
                    throw th;
                }
            }
        }
    }
}
