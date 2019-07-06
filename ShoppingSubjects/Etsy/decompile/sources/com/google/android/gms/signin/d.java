package com.google.android.gms.signin;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.internal.a;

final class d extends AbstractClientBuilder<a, c> {
    d() {
    }

    public final /* synthetic */ Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        c cVar = (c) obj;
        if (cVar == null) {
            cVar = c.a;
        }
        a aVar = new a(context, looper, true, clientSettings, cVar, connectionCallbacks, onConnectionFailedListener);
        return aVar;
    }
}
