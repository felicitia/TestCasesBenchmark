package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzap extends zzav<CredentialRequestResult> {
    private final /* synthetic */ CredentialRequest zzdr;

    zzap(zzao zzao, GoogleApiClient googleApiClient, CredentialRequest credentialRequest) {
        this.zzdr = credentialRequest;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return zzan.zzf(status);
    }

    /* access modifiers changed from: protected */
    public final void zzd(Context context, zzbc zzbc) throws RemoteException {
        zzbc.zzd((zzba) new zzaq(this), this.zzdr);
    }
}
