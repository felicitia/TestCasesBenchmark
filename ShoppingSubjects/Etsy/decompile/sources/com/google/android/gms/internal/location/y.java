package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.g;

final class y extends a {
    private final /* synthetic */ LocationRequest a;
    private final /* synthetic */ g b;

    y(x xVar, GoogleApiClient googleApiClient, LocationRequest locationRequest, g gVar) {
        this.a = locationRequest;
        this.b = gVar;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((n) anyClient).a(this.a, ListenerHolders.createListenerHolder(this.b, s.a(), g.class.getSimpleName()), (zzaj) new b(this));
    }
}
