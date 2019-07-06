package com.google.android.gms.wallet;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.wallet.c;
import com.google.android.gms.wallet.d.a;

final class j extends AbstractClientBuilder<c, a> {
    j() {
    }

    public final /* synthetic */ Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        a aVar = (a) obj;
        if (aVar == null) {
            aVar = new a((j) null);
        }
        c cVar = new c(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener, aVar.a, aVar.b, aVar.c);
        return cVar;
    }
}
