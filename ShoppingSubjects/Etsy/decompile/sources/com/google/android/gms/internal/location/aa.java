package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.g;

final class aa extends a {
    private final /* synthetic */ g a;

    aa(x xVar, GoogleApiClient googleApiClient, g gVar) {
        this.a = gVar;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((n) anyClient).a(ListenerHolders.createListenerKey(this.a, g.class.getSimpleName()), (zzaj) new b(this));
    }
}
