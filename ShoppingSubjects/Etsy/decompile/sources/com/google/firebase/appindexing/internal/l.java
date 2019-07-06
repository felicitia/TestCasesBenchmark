package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.icing.zzab;
import com.google.android.gms.internal.icing.zzai.zzd;

final class l extends n {
    private final /* synthetic */ zza[] a;

    l(k kVar, zza[] zzaArr) {
        this.a = zzaArr;
        super(null);
    }

    /* access modifiers changed from: protected */
    public final void a(zzab zzab) throws RemoteException {
        zzab.zza(new zzd(this), this.a);
    }
}
