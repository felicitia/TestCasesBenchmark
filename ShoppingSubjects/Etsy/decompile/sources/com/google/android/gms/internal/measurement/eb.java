package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class eb implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ boolean e;
    private final /* synthetic */ zzeb f;
    private final /* synthetic */ dq g;

    eb(dq dqVar, AtomicReference atomicReference, String str, String str2, String str3, boolean z, zzeb zzeb) {
        this.g = dqVar;
        this.a = atomicReference;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = z;
        this.f = zzeb;
    }

    public final void run() {
        AtomicReference atomicReference;
        AtomicReference atomicReference2;
        List zza;
        synchronized (this.a) {
            try {
                zzfa d2 = this.g.b;
                if (d2 == null) {
                    this.g.r().h_().a("Failed to get user properties", aq.a(this.b), this.c, this.d);
                    this.a.set(Collections.emptyList());
                    this.a.notify();
                    return;
                }
                if (TextUtils.isEmpty(this.b)) {
                    atomicReference2 = this.a;
                    zza = d2.zza(this.c, this.d, this.e, this.f);
                } else {
                    atomicReference2 = this.a;
                    zza = d2.zza(this.b, this.c, this.d, this.e);
                }
                atomicReference2.set(zza);
                this.g.J();
                atomicReference = this.a;
                atomicReference.notify();
            } catch (RemoteException e2) {
                try {
                    this.g.r().h_().a("Failed to get user properties", aq.a(this.b), this.c, e2);
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
