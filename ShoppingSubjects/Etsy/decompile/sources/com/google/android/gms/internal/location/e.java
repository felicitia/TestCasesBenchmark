package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.location.zzal;

final class e extends f {
    private final /* synthetic */ zzal a;

    e(c cVar, GoogleApiClient googleApiClient, zzal zzal) {
        this.a = zzal;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((n) anyClient).a(this.a, (ResultHolder<Status>) this);
    }
}
