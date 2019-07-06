package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class ea implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ zzeb e;
    private final /* synthetic */ dq f;

    ea(dq dqVar, AtomicReference atomicReference, String str, String str2, String str3, zzeb zzeb) {
        this.f = dqVar;
        this.a = atomicReference;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = zzeb;
    }

    public final void run() {
        AtomicReference atomicReference;
        AtomicReference atomicReference2;
        List zze;
        synchronized (this.a) {
            try {
                zzfa d2 = this.f.b;
                if (d2 == null) {
                    this.f.r().h_().a("Failed to get conditional properties", aq.a(this.b), this.c, this.d);
                    this.a.set(Collections.emptyList());
                    this.a.notify();
                    return;
                }
                if (TextUtils.isEmpty(this.b)) {
                    atomicReference2 = this.a;
                    zze = d2.zza(this.c, this.d, this.e);
                } else {
                    atomicReference2 = this.a;
                    zze = d2.zze(this.b, this.c, this.d);
                }
                atomicReference2.set(zze);
                this.f.J();
                atomicReference = this.a;
                atomicReference.notify();
            } catch (RemoteException e2) {
                try {
                    this.f.r().h_().a("Failed to get conditional properties", aq.a(this.b), this.c, e2);
                    this.a.set(Collections.emptyList());
                    atomicReference = this.a;
                } catch (Throwable th) {
                    this.a.notify();
                    throw th;
                }
            }
        }
    }
}
