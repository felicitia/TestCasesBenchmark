package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class h extends zzd {
    private final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public final void zzg(Status status) throws RemoteException {
        this.a.setResult(status);
    }
}
