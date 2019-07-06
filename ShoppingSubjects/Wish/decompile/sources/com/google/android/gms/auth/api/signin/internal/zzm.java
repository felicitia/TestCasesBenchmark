package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzm extends zzd {
    private final /* synthetic */ zzl zzey;

    zzm(zzl zzl) {
        this.zzey = zzl;
    }

    public final void zzg(Status status) throws RemoteException {
        this.zzey.setResult(status);
    }
}
