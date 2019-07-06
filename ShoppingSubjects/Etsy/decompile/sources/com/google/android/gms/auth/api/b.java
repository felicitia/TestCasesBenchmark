package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.a.C0133a;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;

final class b extends AbstractClientBuilder<com.google.android.gms.internal.auth.b, C0133a> {
    b() {
    }

    public final /* synthetic */ Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        com.google.android.gms.internal.auth.b bVar = new com.google.android.gms.internal.auth.b(context, looper, clientSettings, (C0133a) obj, connectionCallbacks, onConnectionFailedListener);
        return bVar;
    }
}
