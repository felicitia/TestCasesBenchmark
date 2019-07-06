package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

final class z extends a {
    private final /* synthetic */ LocationRequest a;
    private final /* synthetic */ PendingIntent b;

    z(x xVar, GoogleApiClient googleApiClient, LocationRequest locationRequest, PendingIntent pendingIntent) {
        this.a = locationRequest;
        this.b = pendingIntent;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((n) anyClient).a(this.a, this.b, (zzaj) new b(this));
    }
}
