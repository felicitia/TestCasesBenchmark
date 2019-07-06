package com.google.android.gms.internal.ads;

import android.os.RemoteException;

public final class ahn {
    private final byte[] a;
    private int b;
    private int c;
    private final /* synthetic */ ahl d;

    private ahn(ahl ahl, byte[] bArr) {
        this.d = ahl;
        this.a = bArr;
    }

    public final ahn a(int i) {
        this.b = i;
        return this;
    }

    public final synchronized void a() {
        try {
            if (this.d.b) {
                this.d.a.zzc(this.a);
                this.d.a.zzg(this.b);
                this.d.a.zzh(this.c);
                this.d.a.zza(null);
                this.d.a.zzbd();
            }
        } catch (RemoteException e) {
            ka.a("Clearcut log failed", e);
        }
    }

    public final ahn b(int i) {
        this.c = i;
        return this;
    }
}
