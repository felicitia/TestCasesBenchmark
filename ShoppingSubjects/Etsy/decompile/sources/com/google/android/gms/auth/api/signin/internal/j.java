package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class j extends zzd {
    private final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public final void zzh(Status status) throws RemoteException {
        this.a.setResult(status);
    }
}
