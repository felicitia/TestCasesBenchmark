package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class zziu implements Runnable {
    private final /* synthetic */ String zzadn;
    private final /* synthetic */ String zzadu;
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ String zzapg;
    private final /* synthetic */ zzik zzaqv;
    private final /* synthetic */ AtomicReference zzaqw;

    zziu(zzik zzik, AtomicReference atomicReference, String str, String str2, String str3, zzeb zzeb) {
        this.zzaqv = zzik;
        this.zzaqw = atomicReference;
        this.zzapg = str;
        this.zzadn = str2;
        this.zzadu = str3;
        this.zzapd = zzeb;
    }

    public final void run() {
        AtomicReference atomicReference;
        AtomicReference atomicReference2;
        List zze;
        synchronized (this.zzaqw) {
            try {
                zzfa zzd = this.zzaqv.zzaqp;
                if (zzd == null) {
                    this.zzaqv.zzgi().zziv().zzd("Failed to get conditional properties", zzfi.zzbp(this.zzapg), this.zzadn, this.zzadu);
                    this.zzaqw.set(Collections.emptyList());
                    this.zzaqw.notify();
                    return;
                }
                if (TextUtils.isEmpty(this.zzapg)) {
                    atomicReference2 = this.zzaqw;
                    zze = zzd.zza(this.zzadn, this.zzadu, this.zzapd);
                } else {
                    atomicReference2 = this.zzaqw;
                    zze = zzd.zze(this.zzapg, this.zzadn, this.zzadu);
                }
                atomicReference2.set(zze);
                this.zzaqv.zzcu();
                atomicReference = this.zzaqw;
                atomicReference.notify();
            } catch (RemoteException e) {
                try {
                    this.zzaqv.zzgi().zziv().zzd("Failed to get conditional properties", zzfi.zzbp(this.zzapg), this.zzadn, e);
                    this.zzaqw.set(Collections.emptyList());
                    atomicReference = this.zzaqw;
                } catch (Throwable th) {
                    this.zzaqw.notify();
                    throw th;
                }
            }
        }
    }
}
