package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

abstract class zzav<R extends Result> extends ApiMethodImpl<R, zzax> {
    zzav(GoogleApiClient googleApiClient) {
        super(Auth.CREDENTIALS_API, googleApiClient);
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzax zzax = (zzax) anyClient;
        zzd(zzax.getContext(), (zzbc) zzax.getService());
    }

    /* access modifiers changed from: protected */
    public abstract void zzd(Context context, zzbc zzbc) throws DeadObjectException, RemoteException;
}
