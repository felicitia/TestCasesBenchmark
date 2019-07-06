package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzar extends zzav<Status> {
    private final /* synthetic */ Credential zzdt;

    zzar(zzao zzao, GoogleApiClient googleApiClient, Credential credential) {
        this.zzdt = credential;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    /* access modifiers changed from: protected */
    public final void zzd(Context context, zzbc zzbc) throws RemoteException {
        zzbc.zzd((zzba) new zzau(this), new zzbe(this.zzdt));
    }
}
